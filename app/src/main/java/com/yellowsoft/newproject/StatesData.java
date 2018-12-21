package com.yellowsoft.newproject;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class StatesData {
	String id,title,title_ar;
	public StatesData(String title,String id,String title_ar){

		this.title=title;

	}
	public StatesData(JSONObject jsonObject){
		try {
			this.title = jsonObject.getString("title");
			this.id = jsonObject.getString("id");
			this.title_ar = jsonObject.getString("title_ar");
			Log.e("states","states :"+title+" id:"+id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
