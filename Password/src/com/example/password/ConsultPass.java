package com.example.password;

import android.app.Activity;
import com.password.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class ConsultPass extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consult_pass);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_consult_pass, menu);
		return true;
	}
}
