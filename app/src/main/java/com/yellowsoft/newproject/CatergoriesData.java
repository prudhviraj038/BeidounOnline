package com.yellowsoft.newproject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 4/26/2018.
 */

public class CatergoriesData {


    String images,id,title,title_ar,subcat_cnt;


    // Context context;
    public CatergoriesData(String image) {
        this.images = image;


    }

    public CatergoriesData(JSONObject jsonObject){
        try {
            this.images = jsonObject.getString("image");
            this.id = jsonObject.getString("id");
            this.title = jsonObject.getString("title");
            this.title_ar = jsonObject.getString("title_ar");
            this.subcat_cnt = jsonObject.getString("subcat_cnt");
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}

