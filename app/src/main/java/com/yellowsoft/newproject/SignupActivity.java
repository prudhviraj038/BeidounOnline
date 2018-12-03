package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ProgressBar;
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

public class SignupActivity extends AppCompatActivity {
	TextView page_title,btn_edit,logout,login_btn;
	LinearLayout menu_btn,back_btn,submit_btn;
	ImageView back;

	EditText et_fname,et_lname,et_phnumber,et_password,et_email,et_confirm_password;

	ProgressBar progressBar;

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
		Log.e("BackKeypressed","backbutton");
		startActivity(intent);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_signup);

		et_fname = (EditText)findViewById(R.id.et_fname);
		et_lname = (EditText)findViewById(R.id.et_lastname);
		et_phnumber = (EditText)findViewById(R.id.et_phonenumber);
		et_password = (EditText)findViewById(R.id.et_password);
		et_email = (EditText)findViewById(R.id.et_emailid);
		et_confirm_password = (EditText)findViewById(R.id.et_confirm_pd);


		if (getIntent().hasExtra("name")){
			et_fname.setText(getIntent().getStringExtra("name"));
		}



		//submit_btn = (LinearLayout)findViewById(R.id.ll_submit_signup);
		submit_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(et_fname.getText().toString().length()==0){
					Snackbar.make(et_fname,"Please enter firstname",Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter firstname");
				}
				else if(et_lname.getText().toString().length()==0){

					Snackbar.make(et_fname,"Please enter lastname",Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter lastname");

				}
				else if(et_email.getText().toString().length()==0||!et_email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
					Snackbar.make(et_fname,"Please enter valid email",Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter email");

				}
				else if(et_phnumber.getText().toString().length()!=10){
					Snackbar.make(et_fname,"Please enter valid mobile number",Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter mobile");

				}
				else if(et_password.getText().toString().length()!=6){
					Snackbar.make(et_fname,"Please enter password of length 6 characters",Snackbar.LENGTH_SHORT).show();
				//	showAlert("Error","Please enter password");

				}
				else if(et_confirm_password.getText().toString().length()==0){
					Snackbar.make(et_fname,"Please enter confirm password",Snackbar.LENGTH_SHORT).show();
				//	showAlert("Error","Please enter confirm password");

				}
				else if(!et_confirm_password.getText().toString().equals(et_password.getText().toString())){
					Snackbar.make(et_fname,"Please enter same password",Snackbar.LENGTH_SHORT).show();
				//	showAlert("Error","Please enter same password");
				}else {



				}
				/*Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();*/
			}
		});
		login_btn = (TextView)findViewById(R.id.tv_login_signup);
		login_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});


	}






	private  void showAlert(final String tittle, String message) {



	}
}
