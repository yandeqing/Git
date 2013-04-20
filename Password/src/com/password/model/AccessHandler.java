package com.password.model;

import com.password.constant.LockType;


import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class AccessHandler {
	
	private SharedPreferences preferences;
	
	public AccessHandler(SharedPreferences sharedPreferences){
		
		this.preferences = sharedPreferences;	
	
	}
	
	
	public void updatePreference(String key,Boolean value){
		System.out.println("putting "+ key + " to value "+ value);
		SharedPreferences.Editor editor = this.preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	public void updatePreference(String key,String value){
		System.out.println("putting "+ key + " to value "+ value);
		SharedPreferences.Editor editor = this.preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public boolean existKey(String key){
		return preferences.contains(key);
	}
	
	
	
	public void registerPreferencesListener(OnSharedPreferenceChangeListener listener){
		this.preferences.registerOnSharedPreferenceChangeListener(listener);
	}
}
