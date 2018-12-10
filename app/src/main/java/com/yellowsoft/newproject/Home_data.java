package com.yellowsoft.newproject;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 4/26/2018.
 */

public class Home_data {


    String images,id,type;


    // Context context;
    public Home_data(String images) {
        this.images = images;


    }

    public Home_data(JSONObject jsonObject){
        try {
            this.images = jsonObject.getString("image");
            this.id = jsonObject.getString("type_id");
            this.type = jsonObject.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}

