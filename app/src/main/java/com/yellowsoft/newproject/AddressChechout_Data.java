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

public class AddressChechout_Data {

    String id,fname,lname,phone,email,address;


    // Context context;
    public AddressChechout_Data(String id, String fname, String lname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;

    }


    public AddressChechout_Data(JSONObject jsonObject) {


        try {
            this.id = jsonObject.getString("id");
            this.fname = jsonObject.getString("fname");
            this.lname = jsonObject.getString("lname");
            this.phone = jsonObject.getString("phone");
            this.email = jsonObject.getString("email");
            this.address = jsonObject.getString("address");


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

