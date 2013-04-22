package com.example.password;

import com.password.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class AddPass extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_pass);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new CategoryList()).commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_pass, menu);
		return true;
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Intent refresh = new Intent(this, MainActivity.class);
		startActivity(refresh);
	}
}
