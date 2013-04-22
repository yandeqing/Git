package com.example.password;

import com.password.R;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class CategoryList extends PreferenceFragment{
	DBPassword odb;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

	    ListPreference lp = (ListPreference)findPreference("id_cat_t");
	    odb = new DBPassword(this.getActivity(), "DBPassword", null, 1);
		
		SQLiteDatabase db=odb.getWritableDatabase();
		Cursor c=db.rawQuery("SELECT count(name_cat) FROM Category", null);
		int cont = 0;
		if(c.moveToFirst()){
			do {
				cont = Integer.parseInt(c.getString(0));
			} while(c.moveToNext());
		}
		String[] idCat = new String [cont];
		String[] nameCat = new String [cont];
		int cont1 = 0;
		SQLiteDatabase db2=odb.getWritableDatabase();
		Cursor c2=db2.rawQuery("SELECT id_cat, name_cat FROM Category", null);
		if(c2.moveToFirst()){
			do {
				idCat [cont1] = c2.getString(0);
				nameCat[cont1] = c2.getString(1);
				cont1++;
			
			} while(c2.moveToNext());
		}
		c2.close();
		lp.setEntryValues(idCat);
		lp.setEntries(nameCat);
	}
}
