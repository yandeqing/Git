package com.password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumericActivity extends Activity{


	private EditText textPsw, textPswConfirm;
	private TextView textError, textTittle;
	private Button buttonContinue, buttonCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numeric);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

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
		
		this.textPswConfirm = (EditText) findViewById(R.id.pswConfirm);
		this.textPswConfirm.requestFocus();

		
	   buttonCancel.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
            	
            	setResult(RESULT_CANCELED);
            	finish();
            }
        });
		

		buttonContinue.setOnClickListener(new View.OnClickListener() {
			 
            public void onClick(View arg0) {
                //Validate
            	String psw1 = textPsw.getText().toString();
			    String psw2 = textPswConfirm.getText().toString();
			      
            	if (psw1.length() < 4)
            	{
            		textError.setText("Debe ingresar 4 d’gitos");
            	}
            	
            	else if (psw1.length() == 4 && psw2.length() > 1)
            	{
		      
				      if (!psw1.equals(psw2)) 				         
				    	  textError.setText("La secuencia no coincide");
				      else
				      {
				    	  Intent i = new Intent();
				    	  i.putExtra("password", psw1);
				    	  setResult(RESULT_OK, i);
				    	  finish();
				      }
            	
            	}
            	else 
            	{
            		
            		textTittle.setText("Confirmar el PIN");
            		textPsw.setVisibility(EditText.INVISIBLE);
            		textPswConfirm.setVisibility(EditText.VISIBLE);
            		buttonContinue.setText("ACEPTAR");
            		
            		
            	}
            }
        });
		
		textPswConfirm.addTextChangedListener(new TextWatcher() {
			  
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
				      String strPass1 = textPsw.getText().toString();
				      String strPass2 = textPswConfirm.getText().toString();
				      if (strPass1.equals(strPass2)) {
				         textError.setText("Las contrase–as coinciden");
				      } else {
				    	  textError.setText("No coinciden");
				      }
				 
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			
			 });
		
		
		
	}
	
	@Override
	public void onPause(){
	    super.onPause();
	    System.out.println("on pause");
	}

}



