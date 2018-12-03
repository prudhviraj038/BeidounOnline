package com.yellowsoft.newproject;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/6/2016.
 */
public class CountryData implements Serializable {
    String name,image,rate,shipping,code,id;
    int icon;

   public CountryData(String name, String rate, String id,String image,String code,String shipping){
        this.name=name;
        this.rate=rate;
        this.id = id;
        this.image = image;
        this.code= code;
    }


    public CountryData(JSONObject jsonObject) {
        try {
            this.id=jsonObject.getString("id");
            this.image=jsonObject.getString("image");
            this.rate=jsonObject.getString("rate");
            this.name=jsonObject.getString("title");
            this.code=jsonObject.getString("code");
            this.shipping=jsonObject.getString("shipping");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
