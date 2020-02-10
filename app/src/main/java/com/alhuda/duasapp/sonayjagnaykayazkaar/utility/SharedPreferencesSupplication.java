package com.alhuda.duasapp.sonayjagnaykayazkaar.utility;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesSupplication {
	
	public String read(String valueKey, String valueDefault) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		return prefs.getString(valueKey, valueDefault);
	}
	
	public int read(String valueKey, int valueDefault) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		return prefs.getInt(valueKey, valueDefault);
	}
	
	public boolean read(String valueKey, boolean valueDefault) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		return prefs.getBoolean(valueKey, valueDefault);
	}
	
	public long read(String valueKey, long valueDefault) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		return prefs.getLong(valueKey, valueDefault);
	}
	
	
	
	
	
	
	public void save(String valueKey, String value) {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putString(valueKey, value);
		edit.commit();
	}
	
	public void save(String valueKey, int value) {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt(valueKey, value);
		edit.commit();
	}
	
	public void save(String valueKey, boolean value) {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putBoolean(valueKey, value);
		edit.commit();
	}
	
	public void save(String valueKey, long value) {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SingletonClass.applicationContext);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putLong(valueKey, value);
		edit.commit();
	}
}
