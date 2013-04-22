package com.androidhive.googleplacesandmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.password.R;
import com.androidhive.googleplacesandmaps.MainActivity.LoadPlaces;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PlacesMapActivity extends MapActivity {
	// flag for Internet connection status
		Boolean isInternetPresent = false;

		// Connection detector class
		ConnectionDetector cd;
		
		// Alert Dialog Manager
		AlertDialogManager alert = new AlertDialogManager();

		// Google Places
		GooglePlaces googlePlaces;

		// Places List
		//PlacesList nearPlaces;

		// GPS Location
		GPSTracker gps;

		// Button
		Button btnShowOnMap;

		// Progress dialog
		ProgressDialog pDialog;
		
		// Places Listview
		ListView lv;
		
		// ListItems data
		ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String,String>>();
		
		
		// KEY Strings
		public static String KEY_REFERENCE = "reference"; // id of the place
		public static String KEY_NAME = "name"; // name of the place
		public static String KEY_VICINITY = "vicinity"; // Place area name
		public static String keyword = null;
	
	
	
	// Nearest places
	PlacesList nearPlaces;

	// Map view
	MapView mapView;

	// Map overlay items
	List<Overlay> mapOverlays;

	AddItemizedOverlay itemizedOverlay;

	GeoPoint geoPoint;
	// Map controllers
	MapController mc;
	
	double latitude;
	double longitude;
	OverlayItem overlayitem;
	String user_latitude,user_longitude;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_places);

		// Getting intent data
		Intent i = getIntent();
		
		// Users current geo location
		 //user_latitude = i.getStringExtra("user_latitude");
		 //user_longitude = i.getStringExtra("user_longitude");
		keyword = i.getStringExtra("place");
        System.out.println("Lugar: " +  keyword);
        
        cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		isInternetPresent = cd.isConnectingToInternet();
		if (!isInternetPresent) {
			// Internet Connection is not present
			alert.showAlertDialog(this, "Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// creating GPS Class object
		gps = new GPSTracker(this);

		// check if GPS location can get
		if (gps.canGetLocation()) {
			Log.d("Your Location", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
			user_latitude = String.valueOf(gps.getLatitude());
			user_longitude = String.valueOf( gps.getLongitude());
		} else {
			// Can't get user's current location
			alert.showAlertDialog(this, "GPS Status",
					"Couldn't get location information. Please enable GPS",
					false);
			// stop executing code by return
			return;
		}

		// Getting listview
		lv = (ListView) findViewById(R.id.list);
		
		// button show on map
		btnShowOnMap = (Button) findViewById(R.id.btn_show_map);

		// calling background Async task to load Google Places
		// After getting places from Google all the data is shown in listview
		new LoadPlaces().execute();
        
		
		
		

		

	}
	
	
	/**
	 * Background Async Task to Load Google places
	 * */
	class LoadPlaces extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PlacesMapActivity.this);
			pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Places..."));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Places JSON
		 * */
		protected String doInBackground(String... args) {
			// creating Places class object
			googlePlaces = new GooglePlaces();
			
			try {
				// Separeate your place types by PIPE symbol "|"
				// If you want all types places make it as null
				// Check list of types supported by google
				// 
				String types = null; //"bank|cafe"; // Listing places only cafes, restaurants
				
				//String keyword = "Bancomer|Starbucks";
				
				// Radius in meters - increase this value if you don't find any places
				double radius =20000; // 1000 meters 
				
				// get nearest places
				nearPlaces = googlePlaces.search(gps.getLatitude(),
						gps.getLongitude(), radius, types, keyword);
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * and show the data in UI
		 * Always use runOnUiThread(new Runnable()) to update UI from background
		 * thread, otherwise you will get error
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed Places into LISTVIEW
					 * */
					// Get json response status
					String status = nearPlaces.status;
					
					// Check for all possible status
					if(status.equals("OK")){
						// Successfully got places details
						if (nearPlaces.results != null) {
							mapView = (MapView) findViewById(R.id.mapView);
							mapView.setBuiltInZoomControls(true);

							mapOverlays = mapView.getOverlays();
							
							// Geopoint to place on map
							geoPoint = new GeoPoint((int) (Double.parseDouble(user_latitude) * 1E6),
									(int) (Double.parseDouble(user_longitude) * 1E6));
							
							// Drawable marker icon
							Drawable drawable_user =  PlacesMapActivity.this.getResources()
									.getDrawable(R.drawable.mark_red);
							
							itemizedOverlay = new AddItemizedOverlay(drawable_user,PlacesMapActivity.this);
							
							// Map overlay item
							overlayitem = new OverlayItem(geoPoint, "Your Location",
									"That is you!");

							itemizedOverlay.addOverlay(overlayitem);
							
							mapOverlays.add(itemizedOverlay);
							itemizedOverlay.populateNow();
							
							// Drawable marker icon
							Drawable drawable = PlacesMapActivity.this.getResources()
									.getDrawable(R.drawable.mark_blue);
							
							itemizedOverlay = new AddItemizedOverlay(drawable, PlacesMapActivity.this);

							mc = mapView.getController();

							// These values are used to get map boundary area
							// The area where you can see all the markers on screen
							int minLat = Integer.MAX_VALUE;
							int minLong = Integer.MAX_VALUE;
							int maxLat = Integer.MIN_VALUE;
							int maxLong = Integer.MIN_VALUE;

							// check for null in case it is null
							if (nearPlaces.results != null) {
								// loop through all the places
								for (Place place : nearPlaces.results) {
									latitude = place.geometry.location.lat; // latitude
									longitude = place.geometry.location.lng; // longitude
									
									// Geopoint to place on map
									geoPoint = new GeoPoint((int) (latitude * 1E6),
											(int) (longitude * 1E6));
									
									// Map overlay item
									overlayitem = new OverlayItem(geoPoint, place.name,
											place.vicinity);

									itemizedOverlay.addOverlay(overlayitem);
									
									
									// calculating map boundary area
									minLat  = (int) Math.min( geoPoint.getLatitudeE6(), minLat );
								    minLong = (int) Math.min( geoPoint.getLongitudeE6(), minLong);
								    maxLat  = (int) Math.max( geoPoint.getLatitudeE6(), maxLat );
								    maxLong = (int) Math.max( geoPoint.getLongitudeE6(), maxLong );
								}
								mapOverlays.add(itemizedOverlay);
								
								// showing all overlay items
								itemizedOverlay.populateNow();
							}
							
							// Adjusting the zoom level so that you can see all the markers on map
							mapView.getController().zoomToSpan(Math.abs( minLat - maxLat ), Math.abs( minLong - maxLong ));
							
							// Showing the center of the map
							mc.animateTo(new GeoPoint((maxLat + minLat)/2, (maxLong + minLong)/2 ));
							mapView.postInvalidate();
						}
					}
					else if(status.equals("ZERO_RESULTS")){
						// Zero results found
						alert.showAlertDialog(PlacesMapActivity.this, "Near Places",
								"Sorry no places found. Try to change the types of places",
								false);
					}
					else if(status.equals("UNKNOWN_ERROR"))
					{
						alert.showAlertDialog(PlacesMapActivity.this, "Places Error",
								"Sorry unknown error occured.",
								false);
					}
					else if(status.equals("OVER_QUERY_LIMIT"))
					{
						alert.showAlertDialog(PlacesMapActivity.this, "Places Error",
								"Sorry query limit to google places is reached",
								false);
					}
					else if(status.equals("REQUEST_DENIED"))
					{
						alert.showAlertDialog(PlacesMapActivity.this, "Places Error",
								"Sorry error occured. Request is denied",
								false);
					}
					else if(status.equals("INVALID_REQUEST"))
					{
						alert.showAlertDialog(PlacesMapActivity.this, "Places Error",
								"Sorry error occured. Invalid Request",
								false);
					}
					else
					{
						alert.showAlertDialog(PlacesMapActivity.this, "Places Error",
								"Sorry error occured.",
								false);
					}
				}
			});

		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
