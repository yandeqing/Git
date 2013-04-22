package com.example.password;

import android.app.Activity;
import com.password.R;
import android.os.Bundle;
import android.view.Menu;

public class Example extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example);
		//getFragmentManager().beginTransaction().replace(android.R.id.content, new ConsultFragment()).commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_example, menu);
		return true;
	}
}