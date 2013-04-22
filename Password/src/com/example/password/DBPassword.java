package com.example.password;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBPassword extends SQLiteOpenHelper {
	String sqlCreate = "create table Count (id_cat TEXT, name_count TEXT, user TEXT, password TEXT, notes TEXT, stablishment TEXT)";
	String sqlCreate2 = "create table Category (id_cat TEXT, name_cat TEXT)";
	
	public DBPassword(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {   
	      db.execSQL(sqlCreate);
	      db.execSQL(sqlCreate2);
	      Log.i("-------password-------", "Cree la base de datos");
	    }    
	    catch (Exception e){   
	      Log.i("-------password-------", "Error al abrir o crear la base de datos" + e);   
	    }   
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Count");
		db.execSQL(sqlCreate);
		db.execSQL(sqlCreate2);
	}
	
	public int insertIntoCount(String id_cat_, String name_, String user_, String password_, String notes_, boolean stablishment_){
		SQLiteDatabase db = this.getWritableDatabase();
		if(db != null){
			db.execSQL("INSERT INTO Count ( id_cat, name_count, user, password, notes, stablishment ) " +
					"VALUES ('" +id_cat_+ "', '" +
					name_ +"','" +
					user_ +"','" +
					password_ +"','" +
					notes_ +"','" +
					stablishment_ +"' " +
					")");
			db.close();
		}
		else{
			return 1;
		}
		return 0;
	}
	
	public int insertIntoCategory(String name_cat_){
		int id_cat_ = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		
		if(db != null){
			
			Cursor c=db.rawQuery("SELECT count(name_cat) FROM Category", null);
			
			if(c.moveToFirst()){
				do {
					id_cat_ = Integer.parseInt(c.getString(0));
				} while(c.moveToNext());
			}
			
			db.execSQL("INSERT INTO Category ( id_cat, name_cat) " +
					"VALUES ('" +id_cat_+ "', '" +
					name_cat_ +"' " +
					")");
			c.close();
			db.close();
		}
		else{
			return 1;
		}
		return 0;
	}
	
	public int insertIntoCatDefault(){
		SQLiteDatabase db = this.getWritableDatabase();
		if(db != null){
			db.execSQL("INSERT INTO Category ( id_cat, name_cat) VALUES ('0', 'Cuenta de correo')");
			db.execSQL("INSERT INTO Category ( id_cat, name_cat) VALUES ('1', 'Banco')");
			db.execSQL("INSERT INTO Category ( id_cat, name_cat) VALUES ('2', 'Tienda departamental')");
			db.execSQL("INSERT INTO Category ( id_cat, name_cat) VALUES ('3', 'Restaurant')");
			db.close();
		}
		else{
			return 1;
		}
		return 0;
	}

}
