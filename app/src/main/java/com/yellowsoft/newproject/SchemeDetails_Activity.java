package com.yellowsoft.newproject;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

public class SchemeDetails_Activity extends AppCompatActivity {

	ImageView back,scheme_btn,faq_btn,terms_btn,close_btn;

	TextView page_title,scheme_content_activity_tv;
	TextView popup_title,popup_content;

	LinearLayout popup,back_btn,menu_btn;
	RelativeLayout joinnow;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scheme);

		scheme_btn = (ImageView)findViewById(R.id.scheme_img_scheme);
		faq_btn = (ImageView)findViewById(R.id.faq_img_scheme);
		terms_btn = (ImageView)findViewById(R.id.terms_img_scheme);

		popup = (LinearLayout)findViewById(R.id.popup_ll_scheme);
		popup.setVisibility(View.GONE);

		close_btn = (ImageView)findViewById(R.id.close_img_scheme);


		popup_title = (TextView)findViewById(R.id.popup_title_tv_scheme);
		popup_content = (TextView)findViewById(R.id.popup_content_tv_scheme);
		scheme_content_activity_tv= (TextView)findViewById(R.id.scheme_content_activity);

		try {
			scheme_content_activity_tv.setText(Html.fromHtml(ApplicationController.getInstance().settings.getString("scheme_content")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//scheme_content_activity_tv.setText("asdfdffffffffffffffffffffffffffffffffffff");


		close_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ClosePop();
			}
		});


		scheme_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try {
					String scheme = ApplicationController.getInstance().settings.getString("content_scheme_details");
					ShowpopupWindow("Scheme details",scheme);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});

		faq_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String faq =ApplicationController.getInstance().settings.getString("faq");
					Log.e("faq",""+faq);
					ShowpopupWindow("FAQ's",faq);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});

		terms_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String terms = ApplicationController.getInstance().settings.getString("terms");
					ShowpopupWindow("Terms",terms);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});



		Toolbar toolbar = (Toolbar)findViewById(R.id.scheme_toolbar);
		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();
	}


	public void ShowpopupWindow(String title,String content){
		popup_title.setText(title);
		popup.setVisibility(View.VISIBLE);
		popup_content.setText(Html.fromHtml("<p>"+content+"</p>"));
		popup_content.setMovementMethod(LinkMovementMethod.getInstance());
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

		page_title = (TextView) v.findViewById(R.id.page_title);
		back_btn = (LinearLayout)v.findViewById(R.id.btn_back_container);


		back = (ImageView)v.findViewById(R.id.btn_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				finish();
			}
		});

		menu_btn = (LinearLayout) v.findViewById(R.id.btn_menu_container);

		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}
	private void setupHeader(){
		page_title.setText("REFERRAL SCHEME");
		//btn_edit.setVisibility(View.VISIBLE);
		//btn_edit.setText("Search");
		//page_title.setText("Home");
	}

	public void ClosePop(){
		popup.setVisibility(View.GONE);
	}
}
