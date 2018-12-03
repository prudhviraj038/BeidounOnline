package com.yellowsoft.newproject;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 4/26/2018.
 */

public class Home_data {

    int image;
    String images;


    // Context context;
    public Home_data(int image) {
        this.image = image;


    }

    public Home_data(JSONObject jsonObject){
        try {
            this.images = jsonObject.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}

