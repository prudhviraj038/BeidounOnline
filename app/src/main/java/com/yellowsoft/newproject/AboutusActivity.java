package com.yellowsoft.newproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import org.json.JSONException;

public class AboutusActivity extends AppCompatActivity {

    LinearLayout privacy_aboutus_ll,terms_aboutus_ll;
    LinearLayout btn_back_container;

    String msg;

    ImageView search_img_title;

    Toolbar toobar_aboutus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        terms_aboutus_ll = (LinearLayout)findViewById(R.id.terms_aboutus_ll);
        privacy_aboutus_ll = (LinearLayout)findViewById(R.id.privacy_aboutus_ll);

        terms_aboutus_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    msg = ApplicationController.getInstance().settings.getString("about");
                    showAlert(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        privacy_aboutus_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    msg = ApplicationController.getInstance().settings.getString("returns");
                    showAlert(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar_aboutus);
        setSupportActionBar(toolbar);
        setupActionBar();
        setupHeader();
    }

    private void setupActionBar() {


        //set action bar
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.action_bar_login,null);




        search_img_title = (ImageView)v.findViewById(R.id.search_img_title);
        search_img_title.setVisibility(View.GONE);

        btn_back_container = (LinearLayout)v.findViewById(R.id.btn_back_container);
        btn_back_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


/*


		back = (ImageView)v.findViewById(R.id.btn_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});




		search_img_title = (ImageView)v.findViewById(R.id.search_img_title);

		countries_img = (ImageView)v.findViewById(R.id.countries_img);
		countries_img.setVisibility(View.GONE);
*/












        getSupportActionBar().setCustomView(v, layoutParams);
        Toolbar parent = (Toolbar) v.getParent();

        parent.setContentInsetsAbsolute(0, 0);


    }




    private void setupHeader(){

    }

    public void showAlert(String msg){
        new AlertDialog.Builder(AboutusActivity.this)
                .setMessage(Html.fromHtml(msg))
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Whatever...
                        dialog.dismiss();

                        //CheckoutActivty.this.onBackPressed();
                    }
                }).show();
    }
}
