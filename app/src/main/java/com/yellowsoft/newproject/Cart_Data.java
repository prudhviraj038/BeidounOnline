package com.yellowsoft.newproject;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart_Data implements Serializable{
	String price,discountprice,product_images,product_title,description,product_id;
	public  ArrayList<RequestImages> images;
	public  Integer cartquantity;

	public Cart_Data(String price,  String product_images, String product_title, String description, String product_id){

		this.price=price;
		this.product_images=product_images;
		this.description=description;
		this.product_id=product_id;
		this.product_title=product_title;
	}

	public Cart_Data(JSONObject jsonObject){
		try {
			this.product_id=jsonObject.getString("id");
			this.product_title=jsonObject.getString("title");
			this.price=jsonObject.getString("price");
			this.description=jsonObject.getString("description");
			//this.product_images = jsonObject.getString("images");

			images = new ArrayList<>();


				for (int i=0;i<jsonObject.getJSONArray("images").length();i++){
					String image  = jsonObject.getJSONArray("images").getString(i);
				//	Log.e("images",""+image);
					images.add(new RequestImages(image));
				}



		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	}
