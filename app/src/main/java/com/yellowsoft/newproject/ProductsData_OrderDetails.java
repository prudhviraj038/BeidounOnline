package com.yellowsoft.newproject;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by mac on 7/16/18.
 */

public class ProductsData_OrderDetails implements Serializable {

   public String title;
    public String quantity,price;


    ProductsData_OrderDetails(String title, String quantity,String price)  {

        this.title = title;
        this.quantity = quantity;
        this.price = price;


    }



}
