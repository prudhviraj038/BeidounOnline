package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mac on 7/2/18.
 */

public class Session {

    static String SESSION_ID="session_id";
    public static final String BASE_URL = "https://beidounonline.com/";
    static String DEVICE_ID="device_id";
    static String USER_ID="user_id";
    static String USER_mobile="user_mobile";
    public static final String LIVE_PAYMENT_URL = "/api/process_payment.php?";
    public static final String USERNAME = "MYCOP USER";
    static String USER_IMAGE="user_image";


    public static void setSessionId(Context context, String session_id) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SESSION_ID, session_id);
        editor.apply();
    }



    public static String getSessionId(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SESSION_ID, "0");
    }



    //member code (or) myreferal code
    public static void setMemberCode(Context context, String membercode) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("membercode", membercode);
        editor.apply();
    }



    public static String getMemberCode(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("membercode", "");
    }

    public static void setPrice(Context context, String price) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Price", price);
        editor.apply();
    }



    public static String getPrice(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("Price", "5000");
    }

    //shipping charges
    public static void setShippingCharge(Context context, String shippingcharge) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ShippingCharges", shippingcharge);
        editor.apply();
    }



    public static String getShippingCharge(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("ShippingCharges", "0");
    }

    //scheme amount
    public static void setSchemeAmt(Context context, String scheme_amt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SchemeAmount", scheme_amt);
        editor.apply();
    }



    public static String getSchemeAmt(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("SchemeAmount", "2200");
    }


    //set total price
    public static void setTotalPrice(Context context, String totalPrice) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("totalPrice", totalPrice);
        editor.apply();
    }



    public static String getTotalPrice(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("totalPrice", "5000");
    }

    //set user id (or) memberid

    public static void setUserid(Context context, String member_id, String name) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, member_id);
        editor.putString(USERNAME, name);
        editor.apply();

    }
    public static String getUserid(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_ID, "0");

    }
    public static String getUserName(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USERNAME, "MYCOP USER");
    }



    public static String getUSER_mobile(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_mobile, "Mobile");
    }



    public static void setUSER_mobile(Context context, String session_id) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_mobile, session_id);
        editor.apply();
    }



    public static String getUserImage(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_IMAGE, "Mobile");
    }



    public static void setUserImage(Context context, String session_id) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_IMAGE, session_id);
        editor.apply();
    }

    public static void logoutUser(Context context){
        // Clearing all data from Shared Preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sharedPreferences.getString(USER_ID, "-1");
        sharedPreferences.getString(USERNAME, "user name");
        editor.clear();
        editor.commit();


    }


    public static void saveCart(){



    }





    public static String getCurrencyCode(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("code", "KWD");
    }

    public static String getCurrencyRate(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("rate", "1");
    }

    public static String getCurrencyImage(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("currencyImg", "https://beidounonline.com//uploads//images//11507620715.png");
    }



    public static void setQuantity(Context context, String quantity) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("quantity", quantity);
        editor.apply();
    }

    public static String getQuantity(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("quantity", "1");
    }


    public static void setCurrency(Context context, String code,String rate,String img) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("code", code);
        editor.putString("rate",rate);
        editor.putString("currencyImg",img);
        editor.apply();
    }






    //language

    public static void setLanguage(Context context, String id) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", id);
        editor.apply();
    }



    public static String getLanguage(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("lang", "0");
    }


}
