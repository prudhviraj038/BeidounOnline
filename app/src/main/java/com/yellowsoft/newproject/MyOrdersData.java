package com.yellowsoft.newproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sriven on 4/26/2018.
 */

public class MyOrdersData implements Serializable{
    String images = "";
    String price, order_id, quantity, address, tittle, status, date, tracking_link,payment_method;
    String delivery_charges,total_price,discount_amount;
    public ArrayList<ProductsData_OrderDetails> productsData_orderDetails = new ArrayList<ProductsData_OrderDetails>();

    // Context context;
    public MyOrdersData(String images, String price, String order_id, String quantity, String address) {
        this.images = images;
        this.price = price;
        this.order_id = order_id;
        this.quantity = quantity;
        this.address = address;
        // this.context=context;
    }


    public MyOrdersData(JSONObject jsonObject) {


        try {
            this.price = jsonObject.getString("total_price");
            this.order_id = jsonObject.getString("id");


            this.address = jsonObject.getString("address") + "," +
                    jsonObject.getString("city") + ", " +
                    jsonObject.getString("state")+"\n"+
                    jsonObject.getString("phone")+"\n"+
                    jsonObject.getString("email");


            this.date = jsonObject.getString("date");


            this.payment_method = jsonObject.getString("payment_method");
            this.status = jsonObject.getString("delivery_status");

            this.delivery_charges = jsonObject.getString("delivery_charges");
            this.discount_amount = jsonObject.getString("discount_amount");

            this.date = getTimeStamp(this.date);

            if (jsonObject.getJSONArray("products").length() > 0) {

                for (int i = 0;i<jsonObject.getJSONArray("products").length();i++){

                    String title,quantity,price,id;

                 title = jsonObject.getJSONArray("products").getJSONObject(i).getString("title");
                quantity = jsonObject.getJSONArray("products").getJSONObject(i).getString("quantity");
                price = jsonObject.getJSONArray("products").getJSONObject(i).getString("price");
                id = jsonObject.getJSONArray("products").getJSONObject(i).getString("id");

                productsData_orderDetails.add(new ProductsData_OrderDetails(title,quantity,price));

                }
            }

/*
            if (jsonObject.getJSONArray("history").length() > 0) {

//                this.status = jsonObject.getJSONArray("history").getJSONObject(0).getString("message");
                for (int i = 0;i<jsonObject.getJSONArray("history").length();i++){
//                    this.status = jsonObject.getJSONArray("history").getJSONObject(i).getString("message");

                    String message = jsonObject.getJSONArray("history").getJSONObject(i).getString("message");
                    String messageDate = jsonObject.getJSONArray("history").getJSONObject(i).getString("time");

                }
            }*/


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public String getTimeStamp(String str_time_date) {


        try {
            SimpleDateFormat fromFormater = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH);
            Date fromDate = fromFormater.parse(str_time_date);

            SimpleDateFormat curFormater = new SimpleDateFormat(" dd MMM hh:mm a", Locale.ENGLISH);
            String date = curFormater.format(fromDate);

            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return str_time_date;

    }
}

