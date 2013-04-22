package com.password;

import com.password.constant.LockType;
import com.password.model.AccessHandler;
import com.password.util.Utils;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;


public class MainActivity extends Activity {

	public static AccessHandler accessHandler;
	//private boolean paused;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.accessHandler = new AccessHandler(sharedPreferences);        
        Utils.printPreferences(sharedPreferences);
        
       
        
       
        Intent i = new Intent(this,LoginValidatorActivity.class);
        startActivityForResult(i, 1);
        
        
    }// onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	System.out.println("on start");
    }
    
    @Override
    protected void onRestart(){
    	super.onRestart();
    	System.out.println("on restart");
    }

    @Override
    protected void onResume(){
    	super.onResume();
    	System.out.println("on resume");
    	
    }

    @Override
    protected void onPause(){
    	super.onPause();
    	System.out.println("on pause");
    	this.paused = true;
    }

    @Override
    protected void onStop(){
    	super.onStop();
    	System.out.println("on stopt");
    }

    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	System.out.println("on destroy");
    }
   
}
