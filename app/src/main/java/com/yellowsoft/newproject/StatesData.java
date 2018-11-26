package com.yellowsoft.newproject;

import org.json.JSONException;
import org.json.JSONObject;

public class StatesData {
	String states_;
	public StatesData(String states_){

		this.states_=states_;

	}
	public StatesData(JSONObject jsonObject){
		try {
			this.states_ = jsonObject.getString("title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
