package com.yellowsoft.newproject;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sriven on 5/7/2018.
 */

public class ApplicationController extends Application {
    private RequestQueue mRequestQueue;
    public static final String TAG = "VolleyPatterns";
    JSONObject settings;
    String fbId;
    String fbEmail;
    String keywords;

    ArrayList<Object> cartProducts;

    private static ApplicationController sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        settings = new JSONObject();
        // initialize the singleton
        cartProducts = new ArrayList<Object>();
        getCart();
        sInstance = this;

    }

    /**
     * @return co.pixelmatter.meme.ApplicationController singleton instance
     */
    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }

    //
   /* public void setCharges(final String shippingcharges,final String schemeamt){
        Log.e("charges",""+shippingcharges+""+schemeamt);
    }*/


   public void addToCart(CartData productsData){

       cartProducts.add(productsData);

   }

   public void saveCart(){


       TinyDB tinydb = new TinyDB(this);
       tinydb.putListObject("cartProducts", cartProducts);



   }


   public void getCart(){
       TinyDB tinydb = new TinyDB(this);
       cartProducts =    tinydb.getListObject("cartProducts",CartData.class);

   }

   public String formatNumber(float f){

       return String.format("%.02f",f);

   }


}
