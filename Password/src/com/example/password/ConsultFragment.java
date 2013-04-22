package com.example.password;

import android.app.ListFragment;
import com.password.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ConsultFragment extends ListFragment{
	DBPassword odb;
	String[] category;
	String[] names;
	View mContentView = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setArrayCategories();
		this.setListAdapter(new CategoryListAdapter(getActivity(), R.layout.name_list_row, category));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.activity_consult_fragment, null);
		return mContentView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		SharedPreferences sharedPref = this.getActivity().getSharedPreferences("first",0);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("position", position);
		editor.commit();
		
		Intent intent = new Intent(getActivity(), Example.class);
		startActivity(intent);
	}
	
	public void setArrayCategories(){
		odb = new DBPassword(this.getActivity(), "DBPassword", null, 1);
		
		SQLiteDatabase db=odb.getWritableDatabase();
		Cursor c=db.rawQuery("SELECT count(name_cat) FROM Category", null);
		int cont = 0;
		
		if(c.moveToFirst()){
			do {
				cont = Integer.parseInt(c.getString(0));
			} while(c.moveToNext());
		}
		category = new String [cont];
		int cont1 = 0;
		SQLiteDatabase db2=odb.getWritableDatabase();
		Cursor c2=db2.rawQuery("SELECT name_cat FROM Category", null);
		if(c2.moveToFirst()){
			do {
				category [cont1] = c2.getString(0);
				cont1++;
			} while(c2.moveToNext());
		}
	}
	
public void setArrayNames(int message){
		odb = new DBPassword(this.getActivity(), "DBPassword", null, 1);
		
		SQLiteDatabase db=odb.getWritableDatabase();
		Cursor c=db.rawQuery("SELECT count(name_count) FROM Count WHERE id_cat = '"+message+"'", null);
		int cont = 0;
		
		if(c.moveToFirst()){
			do {
				cont = Integer.parseInt(c.getString(0));
			} while(c.moveToNext());
		}
		names = new String [cont];
		int cont1 = 0;
		SQLiteDatabase db2=odb.getWritableDatabase();
		Cursor c2=db2.rawQuery("SELECT name_count FROM Count WHERE id_cat ='"+message+"'", null);
		if(c2.moveToFirst()){
			do {
				names [cont1] = c2.getString(0);
				cont1++;
			
			} while(c2.moveToNext());
		}
	}
}
