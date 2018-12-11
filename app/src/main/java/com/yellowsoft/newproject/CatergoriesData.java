package com.yellowsoft.newproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sriven on 4/26/2018.
 */

public class CatergoriesData  implements Serializable {


    String images,id,title,title_ar,subcat_cnt;

    ArrayList<SubCategoriesData> subcategory = new ArrayList<>();


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


            subcategory = new ArrayList();

            if (jsonObject.getJSONArray("subcategory").length()>0){
                for (int i=0;i<jsonObject.getJSONArray("subcategory").length();i++){

                    JSONArray jsonArray = jsonObject.getJSONArray("subcategory");
                    String id = jsonArray.getJSONObject(i).getString("id");
                    String image = jsonArray.getJSONObject(i).getString("image");
                    String title = jsonArray.getJSONObject(i).getString("title");
                    String subcat_cnt = jsonArray.getJSONObject(i).getString("subcat_cnt");
                    String image_ar = jsonArray.getJSONObject(i).getString("image_ar");
                    String title_ar = jsonArray.getJSONObject(i).getString("title_ar");

                    subcategory.add(new SubCategoriesData(id,image,image_ar,title,subcat_cnt,title_ar));

                }
            }
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}

