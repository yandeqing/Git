package com.example.password;

import com.password.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class AddCategory extends PreferenceFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences3);
	}
}