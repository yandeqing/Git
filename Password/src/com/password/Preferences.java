package com.password;

import group.pals.android.lib.ui.lockpattern.LockPatternActivity;

import com.password.constant.LockType;
import com.password.constant.LockTypeKey;
import com.password.util.Utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.view.Menu;

public class Preferences extends 	PreferenceActivity implements OnSharedPreferenceChangeListener{
	private final short createPatternLock = 1;
	private final short createAlfaNumericLock = 2;
	private final short createNumericLock = 3;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setPreferenceScreen(createPreferenceHierarchy());
        
        
        MainActivity.accessHandler.registerPreferencesListener(this);
        
    }
 
    private PreferenceScreen createPreferenceHierarchy() {
        // Root
        PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);
        
        
        PreferenceCategory securityPreferenceCategory = new PreferenceCategory(this);
        securityPreferenceCategory.setTitle(R.string.title_security_preference_category);
        root.addPreference(securityPreferenceCategory);
        
  
       
        // Pattern block mode
        CheckBoxPreference nextScreenCheckBoxPref = new CheckBoxPreference(this);
        nextScreenCheckBoxPref.setKey(LockType.PATTERN.toString());
        nextScreenCheckBoxPref.setTitle(R.string.toggle_enable_block_pattern_mode);
        nextScreenCheckBoxPref.setSummary(R.string.summary_enable_block_pattern_mode);
        nextScreenCheckBoxPref.setIcon(android.R.drawable.ic_menu_manage);
        securityPreferenceCategory.addPreference(nextScreenCheckBoxPref);
        
  
        
        //Numeric block mode
        CheckBoxPreference numericMode = new CheckBoxPreference(this);
        numericMode.setKey(LockType.NUMERIC.toString());
        numericMode.setTitle(R.string.toggle_enable_numeric_mode);
        numericMode.setSummary(R.string.summary_enable_numeric_mode);
        numericMode.setIcon(android.R.drawable.ic_dialog_dialer);
        securityPreferenceCategory.addPreference(numericMode);
        
      
        
        //Gesture block mode
        CheckBoxPreference gestureMode = new CheckBoxPreference(this);
        gestureMode.setKey(LockType.GESTURE.toString());
        gestureMode.setTitle(R.string.toggle_enable_gesture_mode);
        gestureMode.setSummary(R.string.summary_enable_gesture_mode);
        securityPreferenceCategory.addPreference(gestureMode);
        
       
        
      //Alfanumerico block mode
        CheckBoxPreference alfaNumericMode = new CheckBoxPreference(this);
        alfaNumericMode.setKey(LockType.ALFA_NUMERIC.toString());
        alfaNumericMode.setTitle(R.string.toggle_enable_alfa_numeric_mode);
        alfaNumericMode.setSummary(R.string.summary_enable_alfa_numeric_mode);
        alfaNumericMode.setIcon(android.R.drawable.ic_menu_preferences);
        securityPreferenceCategory.addPreference(alfaNumericMode);
        
       
        
      /*
        // Inline preferences
        PreferenceCategory inlinePrefCat = new PreferenceCategory(this);
        inlinePrefCat.setTitle(R.string.inline_preferences);
        root.addPreference(inlinePrefCat);
       
        // Toggle preference pattern
        CheckBoxPreference togglePref = new CheckBoxPreference(this);
        togglePref.setKey("toggle_preference_pattern");
        togglePref.setTitle(R.string.pattern_toggle_pattern);
        togglePref.setSummary(R.string.summary_toggle_pattern);
        inlinePrefCat.addPreference(togglePref);
        
        
        
               
        // Dialog based preferences
        PreferenceCategory dialogBasedPrefCat = new PreferenceCategory(this);
        dialogBasedPrefCat.setTitle(R.string.dialog_based_preferences);
        root.addPreference(dialogBasedPrefCat);
 
        // Edit text preference
        EditTextPreference editTextPref = new EditTextPreference(this);
        editTextPref.setDialogTitle(R.string.dialog_title_edittext_preference);
        editTextPref.setKey("edittext_preference");
        editTextPref.setTitle(R.string.title_edittext_preference);
        editTextPref.setSummary(R.string.summary_edittext_preference);
        dialogBasedPrefCat.addPreference(editTextPref);
       
        // List preference
        ListPreference listPref = new ListPreference(this);
        listPref.setEntries(R.array.entries_list_preference);
        listPref.setEntryValues(R.array.entryvalues_list_preference);
        listPref.setDialogTitle(R.string.dialog_title_list_preference);
        listPref.setKey("list_preference");
        listPref.setTitle(R.string.title_list_preference);
        listPref.setSummary(R.string.summary_list_preference);
        dialogBasedPrefCat.addPreference(listPref);
       
        // Launch preferences
        PreferenceCategory launchPrefCat = new PreferenceCategory(this);
        launchPrefCat.setTitle(R.string.launch_preferences);
        root.addPreference(launchPrefCat);
 
        /
         * The Preferences screenPref serves as a screen break (similar to page
         * break in word processing). Like for other preference types, we assign
         * a key here so that it is able to save and restore its instance state.
         *
        // Screen preference
        PreferenceScreen screenPref = getPreferenceManager().createPreferenceScreen(this);
        screenPref.setKey("screen_preference");
        screenPref.setTitle(R.string.title_screen_preference);
        screenPref.setSummary(R.string.summary_screen_preference);
        launchPrefCat.addPreference(screenPref);
       
        *
         * You can add more preferences to screenPref that will be shown on the
         * next screen.
         *
       
        // Example of next screen toggle preference
        CheckBoxPreference nextScreenCheckBoxPref = new CheckBoxPreference(this);
        nextScreenCheckBoxPref.setKey("next_screen_toggle_preference");
        nextScreenCheckBoxPref.setTitle(R.string.title_next_screen_toggle_preference);
        nextScreenCheckBoxPref.setSummary(R.string.summary_next_screen_toggle_preference);
        screenPref.addPreference(nextScreenCheckBoxPref);
       
        // Intent preference
        PreferenceScreen intentPref = getPreferenceManager().createPreferenceScreen(this);
        intentPref.setIntent(new Intent().setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("http://www.android.com")));
        intentPref.setTitle(R.string.title_intent_preference);
        intentPref.setSummary(R.string.summary_intent_preference);
        launchPrefCat.addPreference(intentPref);
       
        // Preference attributes
        PreferenceCategory prefAttrsCat = new PreferenceCategory(this);
        prefAttrsCat.setTitle(R.string.preference_attributes);
        root.addPreference(prefAttrsCat);
       
        // Visual parent toggle preference
        CheckBoxPreference parentCheckBoxPref = new CheckBoxPreference(this);
        parentCheckBoxPref.setTitle(R.string.title_parent_preference);
        parentCheckBoxPref.setSummary(R.string.summary_parent_preference);
        prefAttrsCat.addPreference(parentCheckBoxPref);
       */
  
        return root;
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		System.out.println("here");
		System.out.println(key);
		CheckBoxPreference cbp = null;
		boolean value = false;
		if(LockType.PATTERN.toString() ==key){
			cbp =(CheckBoxPreference)getPreferenceScreen().findPreference(key);
			value = sharedPreferences.getBoolean(key, false);
			cbp.setChecked(value);
			if(value==true){
				 Intent i = new Intent(LockPatternActivity._ActionCreatePattern,null,
						 Preferences.this, LockPatternActivity.class);
		         i.putExtra(LockPatternActivity._Theme,R.style.Alp_Theme_Dark);		        
		         //i.putExtra(LockPatternActivity._EncrypterClass, LPEncrypter.class);
		         //i.putExtra(LockPatternActivity._AutoSave, true);
		         startActivityForResult(i, createPatternLock);
			}
			
		}
		else if(LockType.GESTURE.toString() ==key){
			cbp =(CheckBoxPreference)getPreferenceScreen().findPreference(key);
			cbp.setChecked(sharedPreferences.getBoolean(key, false));
		}
		else if(LockType.NUMERIC.toString() ==key){
			cbp =(CheckBoxPreference)getPreferenceScreen().findPreference(key);
			value = sharedPreferences.getBoolean(key, false);
			cbp.setChecked(value);
			if(value==true){
				Intent i = new Intent(Preferences.this, NumericActivity.class);		 
		        startActivityForResult(i, createNumericLock);
			}
		}
		else if(LockType.ALFA_NUMERIC.toString() ==key){
			cbp =(CheckBoxPreference)getPreferenceScreen().findPreference(key);
		    value = sharedPreferences.getBoolean(key, false);
			cbp.setChecked(value);
			if(value==true){
				Intent i = new Intent(Preferences.this, AlfanumericActivity.class);		 
		        startActivityForResult(i, createAlfaNumericLock);
			}
			
		}
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case createPatternLock:
        	System.out.println(resultCode  + " "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
                String pattern = data.getStringExtra(LockPatternActivity._Pattern);
                MainActivity.accessHandler.updatePreference(LockTypeKey.PATTERN_KEY.toString(), pattern);
            }
            else
                MainActivity.accessHandler.updatePreference(LockType.PATTERN.toString(), false);
            break;// _ReqCreateLockPattern
        case createAlfaNumericLock:
        	System.out.println(resultCode  + " "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
                String pattern = data.getStringExtra("password");
                MainActivity.accessHandler.updatePreference(LockTypeKey.ALFA_NUMERIC_KEY.toString(), pattern);
            }
            else
                MainActivity.accessHandler.updatePreference(LockType.ALFA_NUMERIC.toString(), false);
        	break;
        case createNumericLock:
        	System.out.println(resultCode  + " "+ RESULT_OK );
            if (resultCode == RESULT_OK){               
                String pattern = data.getStringExtra("password");
                MainActivity.accessHandler.updatePreference(LockTypeKey.NUMERIC_KEY.toString(), pattern);
            }
            else
                MainActivity.accessHandler.updatePreference(LockType.NUMERIC.toString(), false);
        	break;

        }
        
        
    }// onActivityResult()

}
