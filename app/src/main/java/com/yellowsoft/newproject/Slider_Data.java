package com.yellowsoft.newproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sriven on 4/26/2018.
 */

public class Slider_Data implements Serializable {

    String image;
    String title,subtitle,price,strikePrice,discount;
    public ArrayList<RequestImages> product_images;

    // Context context;
    public Slider_Data(String image,String title,String subtitle,String strikePrice,String discount,String price) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.strikePrice = strikePrice;
        this.discount = discount;


    }

    public Slider_Data(JSONObject jsonObject){
        try {
            this.title = jsonObject.getString("title");
            this.subtitle = jsonObject.getString("title_ar");
            this.strikePrice = jsonObject.getString("old_price");
            this.price = jsonObject.getString("price");



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

