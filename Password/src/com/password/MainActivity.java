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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.accessHandler = new AccessHandler(sharedPreferences);        
        Utils.printPreferences(sharedPreferences);
        
       
        
       
        Intent i = new Intent(this,Preferences.class);
        startActivity(i);
        
        
    }// onCreate()

   
}
