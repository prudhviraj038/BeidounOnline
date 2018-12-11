package com.yellowsoft.newproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by mac on 7/16/18.
 */

public class SubCategoriesData implements Serializable {

   public String image_id;
    public String id,title,subcat_cnt,image,image_ar,title_ar;


    SubCategoriesData(String id,String image,String subcat_cnt,String image_ar,String title,String title_ar)  {

        this.id=id;
        this.title=title;
        this.title_ar=title_ar;
        this.image=image;
        this.image_ar=image_ar;
        this.subcat_cnt = subcat_cnt;


    }

    public SubCategoriesData(JSONObject jsonObject){

        try {
            jsonObject.getString("id");
            jsonObject.getString("title");
            jsonObject.getString("subcat_cnt");
            jsonObject.getString("image");
            jsonObject.getString("image_ar");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
