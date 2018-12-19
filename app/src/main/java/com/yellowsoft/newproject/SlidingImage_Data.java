package com.yellowsoft.newproject;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class SlidingImage_Data {
	String name,description;
	String image_url;

	String id,subtitle,price,strikePrice,discount,type_id,type;
	public ArrayList<RequestImages> product_images;


	/*public SlidingImage_Data(int image){
		this.image=image;
	}*/
	public SlidingImage_Data(String  image_url){

		this.image_url=image_url;


		//this.id = jsonObject.getString("id");
		//	this.name = jsonObject.getString("name");
		//	this.description = jsonObject.getString("desc");
	}


	public SlidingImage_Data(JSONObject jsonObject){
		try {
			this.image_url = jsonObject.getString("image");
			this.type = jsonObject.getString("type");
			this.type_id = jsonObject.getString("type_id");
			this.id = jsonObject.getString("id");
			this.subtitle = jsonObject.getString("title_ar");



		/*	this.strikePrice = jsonObject.getString("old_price");
			this.price = jsonObject.getString("price");





			product_images = new ArrayList<>();

			if (jsonObject.getJSONArray("images").length()>0){

				for ( int i = 0;i<jsonObject.getJSONArray("images").length();i++) {

					String img = jsonObject.getJSONArray("images").getString(i);

					product_images.add(new RequestImages(img));
				}


			}*/


		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
