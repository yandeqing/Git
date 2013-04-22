package com.password;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ValidateAlfaNumericLock extends Activity implements OnClickListener{

	private String pwd;
	private int intent = 0;
	private final int MAX_INTENT = 3;
	private EditText textPsw, textPswConfirm;
	private TextView textError, textTittle;
	private Button buttonContinue, buttonCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_validate_alfa_numeric_lock);
		Intent i = getIntent();
		this.pwd = i.getStringExtra("pwd");
		
		this.buttonContinue = (Button) findViewById(R.id.buttonContinue);
		this.buttonContinue.requestFocus();
		
		this.buttonCancel = (Button) findViewById(R.id.buttonCancel);
		this.buttonCancel.requestFocus();
		
		this.textPsw = (EditText) findViewById(R.id.psw);
		this.textPsw.requestFocus();
		
		this.textError = (TextView) findViewById(R.id.textError);
		this.textError.requestFocus();
		
		this.textTittle = (TextView) findViewById(R.id.textTittle);
		this.textTittle.requestFocus();
		
		 buttonCancel.setOnClickListener(new View.OnClickListener() {
			 
	            public void onClick(View arg0) {
	            	setResult(RESULT_CANCELED);            	
	            	finish();		    	
	            }
	        });
		 buttonContinue.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_validate_alfa_numeric_lock,
				menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		String psw1 = textPsw.getText().toString();
		if(psw1.equals(this.pwd)){
			setResult(RESULT_OK);
			finish();
		}else if(intent < MAX_INTENT) {
			intent ++;
			textError.setText("El password no coincide. Vuelve a Ingresarlo.");
			textPsw.setText("");
		}else{
			setResult(RESULT_CANCELED);
			finish();
		}
		
	}

}
