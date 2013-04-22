package com.password;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ValidateGesture extends Activity implements OnGesturePerformedListener,OnClickListener {
    
	private GestureLibrary mLibrary;
    private Button mCancelButton, mOkButton;

    private Gesture mGesture;
    final String path = new File(Environment.getExternalStorageDirectory(),
            "gestures").getAbsolutePath();
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		
		
		 
		 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_gesture);
        
        mLibrary = GestureLibraries.fromFile(path);//PATH .fromRawResource(this, R.raw.spells);

        
        if (!mLibrary.load()) {
        	finish();
        }

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }
	
	public void onGesturePerformed(final GestureOverlayView overlay, final Gesture gesture) {
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

		// We want at least one prediction
		if (predictions.size() > 0) {
			Prediction prediction = predictions.get(0);
			

			// We want at least some confidence in the result
			if (prediction.score > 3.0 && prediction.name.equals("PRUEBA")) {
					
		        //Set<String> listG = mLibrary.getGestureEntries();
		        //Toast.makeText(this,"NAME" + listG , Toast.LENGTH_SHORT).show();
					mLibrary.removeEntry("PSW_GESTURE");
					mLibrary.removeEntry(prediction.name);
					
				 	mLibrary.addGesture("PSW_GESTURE", gesture);
			        mLibrary.save();
			        
		            setResult(RESULT_OK);
		            
		            
		           
			            //overlay = (GestureOverlayView) findViewById(R.id.gestures_overlay);
			            overlay.post(new Runnable() {
			                public void run() {
			                    overlay.setGesture(gesture);
			                }
			            });

			            //mDoneButton.setEnabled(true);
			       
		            
		            mCancelButton = (Button) findViewById(R.id.cancel);
		   		 	mOkButton = (Button) findViewById(R.id.done);
			        //mCancelButton.setEnabled(false);
			        mOkButton.setEnabled(true);
			        mOkButton.setOnClickListener(this);
			        //Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
					Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(this, "NO COINCIDE, INTENTA NUEVAMENTE.", Toast.LENGTH_SHORT).show();
		}
		
		else 
			Toast.makeText(this, "NO COINCIDE, INTENTA NUEVAMENTE", Toast.LENGTH_SHORT).show();
			
		
		
	}
	
	public void cancelGesture(View v) {
        setResult(RESULT_CANCELED);
        mLibrary = GestureLibraries.fromFile(path);
        mLibrary.removeEntry("PRUEBA");
        mLibrary.removeEntry("PSW_GESTURE");
		mLibrary.save();
        finish();
        
	}
	
	public void okGesture(View v) {
       
            setResult(RESULT_OK);
          //Intent MainA = new Intent(getApplicationContext(), ValidateGesture.class);
      	  	finish();
      	  	//startActivity(MainA);
            
        }


	 @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	        super.onRestoreInstanceState(savedInstanceState);
	        
	        mGesture = savedInstanceState.getParcelable("gesture");
	        if (mGesture != null) {
	            final GestureOverlayView overlay =
	                    (GestureOverlayView) findViewById(R.id.gestures_overlay);
	            overlay.post(new Runnable() {
	                public void run() {
	                    overlay.setGesture(mGesture);
	                }
	            });

	            //mDoneButton.setEnabled(true);
	        }
	    }

	@Override
	public void onClick(View arg0) {
		setResult(RESULT_OK);
		finish();
	}
        
 
	

}