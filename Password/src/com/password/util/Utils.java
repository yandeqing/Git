package com.password.util;

import com.password.constant.LockType;

import android.content.SharedPreferences;

public class Utils {
	public static void printPreferences(SharedPreferences preferences){
		System.out.println("listing preferences..");
		for(LockType lt:LockType.values()){
			System.out.println(lt.toString() + ":"+ preferences.getBoolean(lt.toString(), false));
			boolean exist = preferences.getBoolean(lt.toString(), false);
			if(exist){
				System.out.println(lt.toString()+ "_KEY:"+preferences.getString(lt.toString()+ "_KEY" , ""));
			}
			
		}
		System.out.println("----End---");
	}
}
