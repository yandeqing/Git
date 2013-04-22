package com.password;

import group.pals.android.lib.ui.lockpattern.LockPatternActivity;

import com.password.constant.LockType;
import com.password.constant.LockTypeKey;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class LoginValidatorActivity extends Activity {

	private boolean existConfiguredPatternLockKey;
	private boolean existConfiguredNumericLockKey;
	private boolean existConfiguredAlfaNumericLockKey;
	private boolean existConfiguredGestureLockKey;
	
	private boolean isConfiguredPatternLock;
	private boolean isConfiguredNumericLock;
	private boolean isConfiguredAlfaNumericLock;
	private boolean isConfiguredGestureLock;
	
	private boolean isLoggedPatternLock = false;
	private boolean isLoggedNumericLock = false;
	private boolean isLoggedAlfaNumericLock = false;
	private boolean isLoggedGestureLock = false;
	
	private final short validatePatternLock = 1;
	private final short validateNumericLock =2;
	private final short validateGestureLock=3;
	private final short validateAlfaNumericLock=4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_validator);
		
		existConfiguredPatternLockKey = MainActivity.accessHandler.existKey(LockType.PATTERN.toString());		
		if(existConfiguredPatternLockKey){
			isConfiguredPatternLock =  MainActivity.accessHandler.getBooleanValue((LockType.PATTERN.toString()));
		}
		
		existConfiguredNumericLockKey = MainActivity.accessHandler.existKey(LockType.NUMERIC.toString());		
		if(existConfiguredNumericLockKey){
			isConfiguredNumericLock =  MainActivity.accessHandler.getBooleanValue((LockType.NUMERIC.toString()));
		}
		
		existConfiguredGestureLockKey = MainActivity.accessHandler.existKey(LockType.GESTURE.toString());		
		if(existConfiguredGestureLockKey){
			isConfiguredGestureLock =  MainActivity.accessHandler.getBooleanValue((LockType.GESTURE.toString()));
		}
		
		existConfiguredAlfaNumericLockKey = MainActivity.accessHandler.existKey(LockType.ALFA_NUMERIC.toString());		
		if(existConfiguredAlfaNumericLockKey){
			isConfiguredAlfaNumericLock =  MainActivity.accessHandler.getBooleanValue((LockType.ALFA_NUMERIC.toString()));
		}
	
		executeLogin();
	}
	
	
	private void executeLogin(){
		System.out.println("executing :D");
		if(isConfiguredPatternLock && !isLoggedPatternLock){
			Intent i = new Intent(LockPatternActivity._ActionComparePattern,
                    null, LoginValidatorActivity.this, LockPatternActivity.class);
            i.putExtra(LockPatternActivity._Theme,R.style.Alp_Theme_Dark);
            i.putExtra(LockPatternActivity._Pattern, MainActivity.accessHandler.getStringValue(LockTypeKey.PATTERN_KEY.toString()));
            startActivityForResult(i, validatePatternLock);
		}else if(isConfiguredNumericLock && !isLoggedNumericLock){
			Intent i = new Intent(LoginValidatorActivity.this,ValidateNumericLock.class);
			i.putExtra("pwd",MainActivity.accessHandler.getStringValue(LockTypeKey.NUMERIC_KEY.toString()) );
			startActivityForResult(i, validateNumericLock);
		}else if(isConfiguredGestureLock && !isLoggedGestureLock){
			Intent i = new Intent(LoginValidatorActivity.this,ValidateGestureLock.class);
			startActivityForResult(i, validateGestureLock);
		}else if(isConfiguredAlfaNumericLock && !isLoggedAlfaNumericLock){
			Intent i = new Intent(LoginValidatorActivity.this,ValidateAlfaNumericLock.class);
			i.putExtra("pwd",MainActivity.accessHandler.getStringValue(LockTypeKey.ALFA_NUMERIC_KEY.toString()) );
			startActivityForResult(i, validateAlfaNumericLock);
		}else{
			finish();
		}
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case validatePatternLock:
        	
        	System.out.println(resultCode  + " validatePatternLock "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
                isLoggedPatternLock = true;
            }
            executeLogin();
                
            break;// _ReqCreateLockPattern
        case validateNumericLock:
        	System.out.println(resultCode  + " "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
                isLoggedNumericLock = true;
            }
            executeLogin();            	
        	break;
        case validateGestureLock:
        	System.out.println(resultCode  + " "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
                isLoggedGestureLock = true;
            }
            executeLogin();

            break;
        case validateAlfaNumericLock:
        	System.out.println(resultCode  + " "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
            	isLoggedAlfaNumericLock = true;
            }
            executeLogin();
        	break;

        }
        
        
    }// onActivityResult()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_preferences, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		System.out.println(item.getItemId());
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(LoginValidatorActivity.this,Preferences.class));
			return true;
		

		default:
			break;
			
		}
		return false;
	}

}
