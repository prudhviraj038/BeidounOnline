package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationsActivity extends AppCompatActivity {

	ListView notif_lv;
	Notifications_Adapter notifications_adapter;
	ImageView back;

	TextView page_title;
	ArrayList<NotifyData> notifyData = new ArrayList<NotifyData>();
	LinearLayout back_btn;
	LinearLayout no_notifications_ll;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);

		getNotifications();

		notif_lv = (ListView)findViewById(R.id.notification_lv);
		notifications_adapter = new Notifications_Adapter(NotificationsActivity.this,notifyData);

		notif_lv.setAdapter(notifications_adapter);

		no_notifications_ll = (LinearLayout)findViewById(R.id.no_notifications_ll);


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notifiy);

		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();

	}



	public void getNotifications(){

		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/notifications.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONArray jsonArray = new JSONArray(response);

					String reply;
					if (jsonArray.length()<1){
						no_notifications_ll.setVisibility(View.VISIBLE);
						notif_lv.setVisibility(View.GONE);
					}
					else {
						notif_lv.setVisibility(View.VISIBLE);
						no_notifications_ll.setVisibility(View.GONE);
					}
					for (int i =0;i<jsonArray.length();i++){
						JSONObject jsonObject=jsonArray.getJSONObject(i);
						reply=jsonObject.getString("message");
						Log.e("message",""+reply);
						NotifyData temp = new NotifyData(reply);
						notifyData.add(temp);
					}

					notif_lv.setAdapter(notifications_adapter);
					notifications_adapter.notifyDataSetChanged();



				} catch (JSONException e) {
					e.printStackTrace();
					//Toast.makeText(NotificationsActivity.this,""+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
				}
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(progressDialog!=null)
							progressDialog.dismiss();
						Toast.makeText(NotificationsActivity.this,"No Internet",Toast.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("member_id",Session.getUserid(NotificationsActivity.this));
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

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



		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}
	private void setupHeader(){
		page_title.setText("My Notifications");
		//btn_edit.setVisibility(View.VISIBLE);
		//btn_edit.setText("Search");
		//page_title.setText("Home");
	}
}
