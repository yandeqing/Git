package com.example.password;

import android.app.ListFragment;
import com.password.R;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewCategories extends ListFragment {

	DBPassword odb;
	String[] category;
	String message="0";
	View mContentView = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setListAdapter(new ListAdapter(getActivity(), R.layout.name_list_row, category));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.activity_view_categories, null);
		return mContentView;
	}
	
	public void setArrayCategories(){
		odb = new DBPassword(this.getActivity(), "DBPassword", null, 1);
		
		SQLiteDatabase db=odb.getWritableDatabase();
		Cursor c=db.rawQuery("SELECT count(name_count) FROM Count WHERE id_cat = '"+message+"'", null);
		int cont = 0;
		
		if(c.moveToFirst()){
			do {
				cont = Integer.parseInt(c.getString(0));
			} while(c.moveToNext());
		}
		category = new String [cont];
		int cont1 = 0;
		SQLiteDatabase db2=odb.getWritableDatabase();
		Cursor c2=db2.rawQuery("SELECT name_count FROM Count WHERE id_cat ='"+message+"'", null);
		if(c2.moveToFirst()){
			do {
				category [cont1] = c2.getString(0);
				cont1++;
			
			} while(c2.moveToNext());
		}
	}

}
