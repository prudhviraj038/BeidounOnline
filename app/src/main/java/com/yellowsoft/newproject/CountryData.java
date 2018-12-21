package com.yellowsoft.newproject;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sriven on 5/6/2016.
 */
public class CountryData implements Serializable {
    String name,image,rate,shipping,code,id;

    public ArrayList<StatesData> states;
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

            states = new ArrayList<>();
            if (jsonObject.getJSONArray("states").length()>0){

                for (int i = 0 ;i<jsonObject.getJSONArray("states").length();i++){

                    StatesData temp = new StatesData(jsonObject.getJSONArray("states").getJSONObject(i));
                    states.add(temp);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
