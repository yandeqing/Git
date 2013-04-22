package com.example.password;

import java.security.InvalidKeyException;
import com.password.R;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.Toast;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class MainActivity extends Activity {
	DBPassword odb;
	private static SecretKey key;
	private static Cipher ecipher;
	private static Cipher dcipher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainpsw);
		
		odb = new DBPassword(this, "DBPassword", null, 1);
		
		SharedPreferences sharedPref = getSharedPreferences("first",MODE_PRIVATE);
		boolean firstTime = sharedPref.getBoolean("fTime", true);
		
		if(firstTime){
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putBoolean("fTime", false);
			editor.commit();
			
			encryptDecrypt();

			int ii = odb.insertIntoCatDefault();
		}
		SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
		String name_p = sharedPref2.getString("name_t", "");
		
		if(name_p != ""){
			String idcat_p = sharedPref2.getString("id_cat_t", "");
			String user_p = sharedPref2.getString("user_t", "");
			String password_p = sharedPref2.getString("password_t", "");
			
			String encrypted = encryptData(password_p);
			
			String notes_p = sharedPref2.getString("notes_t", "");
			boolean stablishment_p = sharedPref2.getBoolean("stablishment_t", false);
			
			SQLiteDatabase db=odb.getWritableDatabase();
			Cursor c=db.rawQuery("SELECT id_cat, name_count, user, password FROM Count ", null);
			boolean isRepeat = false;
			if(c.moveToFirst()){
				do {
					if(idcat_p.equals(c.getString(0))  && name_p.equals(c.getString(1)) && user_p.equals(c.getString(2))){
						isRepeat = true;
						break;
					}
				} while(c.moveToNext());
			}
			if(!isRepeat){
				int i = odb.insertIntoCount(idcat_p, name_p, user_p, encrypted, notes_p, stablishment_p);
			}	
		}
		SharedPreferences sharedPref22 = PreferenceManager.getDefaultSharedPreferences(this);
		String name_cat = sharedPref22.getString("new_category", "");
		
		if(name_cat != ""){
			SQLiteDatabase db=odb.getWritableDatabase();
			Cursor ccat=db.rawQuery("SELECT name_cat FROM Category", null);
			boolean isRepeat = false;
			if(ccat.moveToFirst()){
				do {
					if(name_cat.equals(ccat.getString(0))){
						isRepeat = true;
						break;
					}
				} while(ccat.moveToNext());
			}
			
			if(!isRepeat){
				int i = odb.insertIntoCategory(name_cat);
			}	
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);
			enc = BASE64EncoderStream.encode(enc);
			return new String(enc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String decrypt(String str) {
		try {
			byte[] dec = BASE64DecoderStream.decode(str.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void encryptDecrypt(){
		try {
			key = KeyGenerator.getInstance("DES").generateKey();

			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
		}
		catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		}
		catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		}
	}
	
	public String encryptData(String data){
		try {
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			String encrypted = encrypt(data);
			return encrypted;
		}
		catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return "fail";
		}
	}
	
	public String decryptData(String data){
		try {
			dcipher.init(Cipher.DECRYPT_MODE, key);
			String decrypted = decrypt(data);
			return decrypted;
		}
		catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return "fail";
		}
	}
}
