package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {
	EditText old_password,password,new_password;
	LinearLayout submit_cp,back_btn;
	TextView page_title;
	ImageView back;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);

		old_password = (EditText)findViewById(R.id.et_opassword);
		new_password = (EditText)findViewById(R.id.et_newpassword);
		password = (EditText)findViewById(R.id.et_cpassword);

		submit_cp = (LinearLayout)findViewById(R.id.ll_submit_changepassword);
		submit_cp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(old_password.getText().toString().equals("")){
					Snackbar.make(new_password,"Please enter old password",Snackbar.LENGTH_SHORT).show();
					//	showAlert("Error","Please enter password");

				}
				else if(new_password.getText().toString().equals("")){
					Snackbar.make(new_password,"Please enter new password",Snackbar.LENGTH_SHORT).show();
					//	showAlert("Error","Please enter password");

				}

				else if(password.getText().toString().equals("")){
					Snackbar.make(new_password,"Please enter confirm password",Snackbar.LENGTH_SHORT).show();
					//	showAlert("Error","Please enter confirm password");

				}
				else if(!new_password.getText().toString().equals(password.getText().toString())){
					Snackbar.make(new_password,"Please enter same password",Snackbar.LENGTH_SHORT).show();
					//	showAlert("Error","Please enter same password");
				}
				else {


					callChangePassword(new_password.getText().toString(), password.getText().toString(), old_password.getText().toString());

				}
			}
		});


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cpassword);
		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();

	}


	public void callChangePassword(final String cpassword,final String password,final String opassword){

		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/change-password.php";

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
						Toast.makeText(ChangePasswordActivity.this,""+reply.toString(),Toast.LENGTH_SHORT).show();
						finish();

					}

					else {

						String msg = jsonObject.getString("message");
						Log.e("message",""+msg);
						Toast.makeText(ChangePasswordActivity.this,""+msg.toString(),Toast.LENGTH_SHORT).show();
						//Snackbar.make(gmail_btn, msg, Snackbar.LENGTH_SHORT).show();
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
						Toast.makeText(ChangePasswordActivity.this,""+error.toString(),Toast.LENGTH_SHORT).show();
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}

				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("cpassword",cpassword);
				parameters.put("password",password);
				parameters.put("opassword",opassword);
				parameters.put("member_id",Session.getUserid(ChangePasswordActivity.this));

				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}

	//set action bar
	private void setupActionBar() {

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
		page_title.setText("Change Password");
	}

}
