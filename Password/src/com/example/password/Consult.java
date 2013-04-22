package com.example.password;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.password.R;
import com.example.password.MainActivity;

public class Consult extends Activity {
	TextView t;
	DBPassword odb;
	ImageView i;
	MainActivity aux;

	public String place = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consult);
		
		t=new TextView(this); 

		SharedPreferences sharedPref = getSharedPreferences("first",0);
		String name_show = sharedPref.getString("name", "default");
		place = name_show;
		
	    t=(TextView)findViewById(R.id.name_c); 
	    t.setText(name_show);
	    
	    odb = new DBPassword(this, "DBPassword", null, 1);
		
		SQLiteDatabase db=odb.getWritableDatabase();
		Cursor c=db.rawQuery("SELECT * FROM Count WHERE name_count = '"+name_show+"'", null);
		
		if(c.moveToFirst()){
			do {
				t=(TextView)findViewById(R.id.user_c); 
			    t.setText(c.getString(2));
			    
			    t=(TextView)findViewById(R.id.password_c);
			    aux = new MainActivity();
			    String decrypted = aux.decryptData(c.getString(3));
			    t.setText(decrypted);
			    
			    t=(TextView)findViewById(R.id.notes_c); 
			    t.setText(c.getString(4));
			    
			    if(!c.getString(5).equals("true")){
			    	i = (ImageView)findViewById(R.id.imageViewMap);
			    	i.setVisibility(View.INVISIBLE);
			    }
			    
			} while(c.moveToNext());

			c.close();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_consult, menu);
		return true;
	}
	
	 
    //@SuppressWarnings({"UnusedDeclaration"})
    public void viewMap(View v) {
    	
  	  	
    	
  	  	Intent i = new Intent(getApplicationContext(), com.androidhive.googleplacesandmaps.PlacesMapActivity.class);
  	  	i.putExtra("place", place);
  	  	
        finish();
        startActivity(i);      
        
        
    }
	

}
