package com.yellowsoft.newproject;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sriven on 4/26/2018.
 */

public class WishlistData implements Serializable {

    public ArrayList<RequestImages> product_images;
    Context context;



    String title,subtitle,price,id,old_price,quantity,about,about_ar,category,brand,images,description,description_ar;
    String code;


    // Context context;
    public WishlistData(String title, String subtitle, String price) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;

    }

    public WishlistData(JSONObject jsonObject) {
        try {
            //this.image = jsonObject.getString("images");
            this.title = jsonObject.getString("title");
            this.id = jsonObject.getString("id");
            this.subtitle = jsonObject.getString("title_ar");
            this.price = jsonObject.getString("price");
            this.old_price = jsonObject.getString("old_price");
            this.quantity = jsonObject.getString("quantity");
            this.about = jsonObject.getString("about");
            this.about_ar = jsonObject.getString("about_ar");
            this.category = jsonObject.getString("category");
            this.brand = jsonObject.getString("brand");
            this.description = jsonObject.getString("description");
            this.description_ar = jsonObject.getString("description_ar");


            product_images = new ArrayList<>();

            if (jsonObject.getJSONArray("images").length()>0){

                for ( int i = 0;i<jsonObject.getJSONArray("images").length();i++) {

                    String img = jsonObject.getJSONArray("images").getString(i);

                    product_images.add(new RequestImages(img));
                }


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}

