package com.caretom.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveData 
{

	//==========CLASS VARIABLE ==================//

	private Context _context;
	private SharedPreferences _preferences;
	private Editor _editor;
	private String _sharedPrefName = "CareTom";

	//======================================//

	public SaveData(Context c)
	{
		_context = c;
		this._preferences = this._context.getSharedPreferences(this._sharedPrefName, Context.MODE_PRIVATE);
		this._editor = this._preferences.edit();
	}


	//====================== SAVE DATA =========================//

	public void save(String key, String value){

		this._editor.putString(key, value);
		this._editor.commit();
	}	

	public void save(String key, boolean value){
		this._editor.putBoolean(key, value);
		this._editor.commit();
	}	

	public void save(String key, int value){
		this._editor.putInt(key, value);
		this._editor.commit();
	}	
	
	
	public void saveUserKey(String uId, String key, String value){

		this._editor.putString(key+"_"+uId, value);
		this._editor.commit();
	}	

	public void saveLastPlay(String uId, String key, String value){
		this._editor.putString(key+"_"+uId, value);
		this._editor.commit();
	}

	
	//====================== GET DATA =========================//

	public String getString(String key)
	{
		return this._preferences.getString(key, "");
	}
	
	public boolean getboolean(String key)
	{
		return this._preferences.getBoolean(key, false);
	}
	
	public int getInt(String key)
	{
		return this._preferences.getInt(key, 0);
	}
	
	public String getUserKey(String key, String uId)
	{
		return this._preferences.getString(key+"_"+uId, key);
	}

	public String getLastPlay(String key, String uId)
	{
		return this._preferences.getString(key+"_"+uId, key);
	}
	
	
	//====================== CLEAR DATA =========================//

	public void clearData(String key)
	{
		this._editor.remove(key);
		this._editor.commit();
	}

	public void clearAllData()
	{
		this._editor.clear();
		this._editor.commit();
	}

}
