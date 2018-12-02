package com.yellowsoft.newproject;

import android.content.Context;

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
      //  if(Settings.get_user_language(context).equals("ar"))
        //    return title_ar;
        //else
          //  return  title;
    }




}
