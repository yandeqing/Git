package com.example.password;

import android.os.Bundle;
import com.password.R;
import android.app.Activity;
import android.view.Menu;

public class ShowSettings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_settings, menu);
		return true;
	}

}
