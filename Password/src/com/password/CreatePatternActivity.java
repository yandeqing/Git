package com.password;

import com.password.constant.LockType;
import com.password.constant.LockTypeKey;

import group.pals.android.lib.ui.lockpattern.LockPatternActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class CreatePatternActivity extends Activity {

	private final short createLockPattern =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_pattern);
		
		 Intent i = new Intent(LockPatternActivity._ActionCreatePattern,null,
				 CreatePatternActivity.this, LockPatternActivity.class);
         i.putExtra(LockPatternActivity._Theme,R.style.Alp_Theme_Dark);
        
         //i.putExtra(LockPatternActivity._EncrypterClass, LPEncrypter.class);
         //i.putExtra(LockPatternActivity._AutoSave, true);
         startActivityForResult(i, createLockPattern);
	        
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_pattern, menu);
		return true;
	}
	
	/*private static final int _ReqCreateLockPattern = 0;
    private static final int _ReqEnterLockPattern = 1;

    private CheckBox mChkDialog;
    private CheckBox mChkStealthMode;
    private Button mBtnCreateLockPattern;
    private Button mBtnEnterLockPattern;
    private String pattern = "";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_pattern);
		 mChkDialog = (CheckBox) findViewById(R.id.dialog);
	        mChkStealthMode = (CheckBox) findViewById(R.id.stealth_mode);
	        mBtnCreateLockPattern = (Button) findViewById(R.id.create_lockpattern);
	        mBtnEnterLockPattern = (Button) findViewById(R.id.enter_lockpattern);

	        mBtnCreateLockPattern
	                .setOnClickListener(mBtnCreateLockPatternOnClickListener);
	        mBtnEnterLockPattern
	                .setOnClickListener(mBtnEnterLockPatternOnClickListener);
	        
	        mBtnCreateLockPattern.performClick();
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_pattern, menu);
		return true;
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        switch (requestCode) {
	        case _ReqCreateLockPattern:
	            if (resultCode == RESULT_OK){
	                setTitle(data.getStringExtra(LockPatternActivity._Pattern));
	                this.pattern = data.getStringExtra(LockPatternActivity._Pattern);
	            }
	            else
	                setTitle(R.string.app_name);

	            break;// _ReqCreateLockPattern

	        case _ReqEnterLockPattern:
	            Toast toast = Toast.makeText(this,
	                    resultCode == RESULT_OK ? android.R.string.ok
	                            : android.R.string.cancel, Toast.LENGTH_SHORT);
	            toast.setDuration(Toast.LENGTH_SHORT);
	            toast.setGravity(Gravity.CENTER, 0, 0);
	            toast.show();

	            break;// _ReqEnterLockPattern
	        }
	    }// onActivityResult()

	    private final View.OnClickListener mBtnCreateLockPatternOnClickListener = new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            Intent i = new Intent(LockPatternActivity._ActionCreatePattern,
	                    null, CreatePatternActivity.this, LockPatternActivity.class);
	            i.putExtra(LockPatternActivity._Theme,
	                    mChkDialog.isChecked() ? R.style.Alp_Theme_Dialog_Dark
	                            : R.style.Alp_Theme_Dark);
	            i.putExtra(LockPatternActivity._StealthMode,
	                    mChkStealthMode.isChecked());
	            //i.putExtra(LockPatternActivity._EncrypterClass, LPEncrypter.class);
	            //i.putExtra(LockPatternActivity._AutoSave, true);
	            startActivityForResult(i, _ReqCreateLockPattern);
	        }
	    };// mBtnCreateLockPatternOnClickListener

	    private final View.OnClickListener mBtnEnterLockPatternOnClickListener = new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            Intent i = new Intent(LockPatternActivity._ActionComparePattern,
	                    null, CreatePatternActivity.this, LockPatternActivity.class);
	            i.putExtra(LockPatternActivity._Theme,
	                    mChkDialog.isChecked() ? R.style.Alp_Theme_Dialog_Dark
	                            : R.style.Alp_Theme_Dark);
	            i.putExtra(LockPatternActivity._StealthMode,
	                    mChkStealthMode.isChecked());
	            i.putExtra(LockPatternActivity._Pattern, CreatePatternActivity.this.pattern);
	            //i.putExtra(LockPatternActivity._EncrypterClass, LPEncrypter.class);
	            //i.putExtra(LockPatternActivity._AutoSave, true);
	            startActivityForResult(i, _ReqEnterLockPattern);
	        }
	    };// mBtnEnterLockPatternOnClickListener*/

}
