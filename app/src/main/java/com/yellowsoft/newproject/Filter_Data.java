package com.yellowsoft.newproject;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 4/26/2018.
 */

public class Filter_Data {


    String images,id,title,title_ar;
    String categoryid;


    // Context context;
    public Filter_Data(String image) {
        this.images = image;


    }

    public Filter_Data(JSONObject jsonObject){
        try {
            this.images = jsonObject.getString("image");
            this.id = jsonObject.getString("id");
            this.title = jsonObject.getString("title");
            this.title_ar = jsonObject.getString("title_ar");

            Log.e("title",""+title);

            this.categoryid = jsonObject.getString("price");




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}

