package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 7/3/18.
 */

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);



        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        callSettings();
      //  callWords();



    }

    public void callSettings(){

       /* final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
        progressDialog.setCancelable(false);*/
        String URL = Session.BASE_URL+"api/settings.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("resWords",response);

                callWords();

               /* if(progressDialog!=null) {
                    progressDialog.dismiss();

                }*/


                try {
                    JSONObject jsonObject=new JSONObject(response);

                    ApplicationController.getInstance().settings = jsonObject;







                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("errorOne",""+e.getMessage().toString());
                    Toast.makeText(SplashScreen.this,""+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    Intent j = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(j);

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorTwo",""+error.getMessage().toString());
                        Toast.makeText(SplashScreen.this,"Network issue",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                      /*  if(progressDialog!=null)
                            progressDialog.dismiss();*/
                        //  Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> parameters = new HashMap<String, String>();

                return parameters;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(stringRequest);

    }
    //call settings api
    public void callWords(){

       /* final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
        progressDialog.setCancelable(false);*/
        String URL = Session.BASE_URL+"api/words-json.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("resWords",response);

               /* if(progressDialog!=null) {
                    progressDialog.dismiss();

                }*/
                Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(i);
                finish();
                try {
                    JSONObject jsonObject=new JSONObject(response);


                    ApplicationController.getInstance().wordsEN = jsonObject.getJSONObject("en");
                    ApplicationController.getInstance().wordsAR = jsonObject.getJSONObject("ar");







                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("errorOne",""+e.getMessage().toString());
                    Toast.makeText(SplashScreen.this,""+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    Intent j = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(j);
                    finish();
                    }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorTwo",""+error.getMessage().toString());
                        Toast.makeText(SplashScreen.this,"Network issue",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                      /*  if(progressDialog!=null)
                            progressDialog.dismiss();*/
                      //  Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> parameters = new HashMap<String, String>();

                return parameters;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(stringRequest);

    }


}
