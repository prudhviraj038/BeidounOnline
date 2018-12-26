package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactusActivity extends AppCompatActivity{
	TextView page_title,name_contact,contactus_title;
	TextView email_contact,phone_contact,assistyou_contact;


	ImageView back,search_img_title;



	EditText et_phone_contactus,et_name_contactus,et_email_contactus,et_message_contactus;
	LinearLayout back_btn,menu_btn;
	LinearLayout submit_ll_contact;


	String enter_name,enter_email,enter_phone,enter_message;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactus);

		et_phone_contactus = (EditText)findViewById(R.id.et_phone_contactus);
		et_name_contactus = (EditText)findViewById(R.id.et_name_contactus);
		et_email_contactus = (EditText)findViewById(R.id.et_email_contactus);
		et_message_contactus = (EditText)findViewById(R.id.et_message_contactus);



		name_contact = (TextView)findViewById(R.id.name_contact) ;
		email_contact = (TextView)findViewById(R.id.email_contact) ;
		phone_contact = (TextView)findViewById(R.id.phone_contact) ;
		assistyou_contact = (TextView)findViewById(R.id.assistyou_contact) ;

		contactus_title = (TextView)findViewById(R.id.contactus_title);




		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;

		try {
			if (Session.getLanguage(ContactusActivity.this).equals("0")) {
				name_contact.setText(jsonObjectEN.getString("First Name"));
				email_contact.setText(jsonObjectEN.getString("Email Address"));
				phone_contact.setText(jsonObjectEN.getString("Mobile Number"));
				assistyou_contact.setText(jsonObjectEN.getString("How may we assist you?"));

				et_message_contactus.setHint(jsonObjectEN.getString("Please enter your message here"));
				contactus_title.setText(jsonObjectEN.getString("Contact Us"));

				enter_name = jsonObjectEN.getString("Please enter your first name.");
				enter_email = jsonObjectEN.getString("Please enter a valid email address.");
				enter_phone= jsonObjectEN.getString("Please enter a valid mobile number.");
				enter_message= jsonObjectEN.getString("Please enter your message here");

				et_name_contactus.setHint(jsonObjectEN.getString("First Name"));
				et_email_contactus.setHint(jsonObjectEN.getString("Email Address"));
				et_phone_contactus.setHint(jsonObjectEN.getString("Mobile Number"));

			} else {

				name_contact.setText(jsonObjectAR.getString("First Name"));
				email_contact.setText(jsonObjectAR.getString("Email Address"));
				phone_contact.setText(jsonObjectAR.getString("Mobile Number"));
				assistyou_contact.setText(jsonObjectAR.getString("How may we assist you?"));
				et_message_contactus.setHint(jsonObjectAR.getString("Please enter your message here"));
				contactus_title.setText(jsonObjectAR.getString("Contact Us"));

				enter_name = jsonObjectAR.getString("Please enter your first name.");
				enter_email = jsonObjectAR.getString("Please enter a valid email address.");
				enter_phone= jsonObjectAR.getString("Please enter a valid mobile number.");
				enter_message= jsonObjectAR.getString("Please enter your message here");

			}
		}
		catch (JSONException j){

		}



		//subit button
		submit_ll_contact  = (LinearLayout)findViewById(R.id.submit_ll_contact);
		submit_ll_contact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(et_name_contactus.getText().toString().equals("")){
					Snackbar.make(et_phone_contactus,""+enter_name,Snackbar.LENGTH_SHORT).show();

				}
				else if(et_email_contactus.getText().toString().equals("")){

					Snackbar.make(et_phone_contactus,""+enter_email,Snackbar.LENGTH_SHORT).show();


				}
				else if(et_phone_contactus.getText().toString().equals("")){
					Snackbar.make(et_phone_contactus,""+enter_phone,Snackbar.LENGTH_SHORT).show();


				}
				else if(et_message_contactus.getText().toString().equals("")){
					Snackbar.make(et_phone_contactus,""+enter_message,Snackbar.LENGTH_SHORT).show();


				}

				else {
					//submitForm(et_phone_contactus.getText().toString(),et_name_contactus.getText().toString(),et_email_contactus.getText().toString(),et_message_contactus.getText().toString(),topic_select_tv_dropdown.getText().toString());
				}

			}
		});


		Toolbar toolbar = (Toolbar)findViewById(R.id.contactus_toolbar);
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

		page_title = (TextView) v.findViewById(R.id.page_title);
		back_btn = (LinearLayout)v.findViewById(R.id.btn_back_container);



		search_img_title = (ImageView)v.findViewById(R.id.search_img_title);
		search_img_title.setVisibility(View.GONE);


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
		//btn_edit.setVisibility(View.VISIBLE);
		//btn_edit.setText("Search");
		//page_title.setText("Home");
	}
	public void submitForm(final String phone,final String name,final String email,final String message,final String topic){

		//Log.e("memberidreferalcode","id="+member_id+" , code = "+referal_code);
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/contact-us.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONObject jsonObject=new JSONObject(response);


					String reply=jsonObject.getString("status");
					Log.e("status",""+reply);


					if(reply.equals("Success")) {

						//Session.setMemberCode(ContactusActivity.this,jsonObject.getString("member_code"));

						String message = jsonObject.getString("message");
						Snackbar.make(et_email_contactus,"Submitted successfully",Snackbar.LENGTH_SHORT).show();





					}
					else
					{
						String errorMessage =jsonObject.getString("message");
					//	Snackbar.make(apply_ll_btn_referal,""+errorMessage,Snackbar.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(progressDialog!=null)
							progressDialog.dismiss();
						//  Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("name",name);
				parameters.put("email",email);

				parameters.put("phone",phone);
				if (topic.toString().equals("")){
					parameters.put("topic","General");
				}
				else {
					parameters.put("topic",topic);
					Log.e("topic",topic);
				}


				parameters.put("message",message);




				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}
}
