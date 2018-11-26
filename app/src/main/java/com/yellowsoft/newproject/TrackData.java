package com.yellowsoft.newproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sriven on 4/26/2018.
 */

public class TrackData {

    String uname,password,veh_id;


    // Context context;
    public TrackData(String uname,String password,String veh_id) {
        this.uname = uname;
        this.password = password;
        this.veh_id = veh_id;

    }


    public TrackData(JSONObject jsonObject) {


        try {
            this.uname = jsonObject.getString("username");
            this.password = jsonObject.getString("password");
            this.veh_id = jsonObject.getString("track_no");


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

