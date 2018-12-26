package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SignupFragment  extends Fragment {
	TextView page_title,btn_edit,logout,login_btn;
	LinearLayout menu_btn,back_btn,submit_btn,signin_signup_ll;
	ImageView back;

	EditText et_fname,et_lname,et_phnumber,et_password,et_email,et_confirm_password;

	ProgressBar progressBar;

	TextView creatingacct_title_signup,tv_submit_signup,tv_haveacct_signup;
	TextView tv_signin_signup,agree_signup,terms_tv_signup;

	String confirm_pass,enter_password,enter_fname,enter_lname,enter_valid_email;
	String enter_mobilenumb,enter_valid_mobilenumb,enter_same_pass;


	public static SignupFragment newInstance(int someInt) {
		SignupFragment myFragment = new SignupFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_signup, container, false);


		et_fname = (EditText)view.findViewById(R.id.et_fname);
		et_lname = (EditText)view.findViewById(R.id.et_lastname);
		et_phnumber = (EditText)view.findViewById(R.id.et_phonenumber);
		et_password = (EditText)view.findViewById(R.id.et_password);
		et_email = (EditText)view.findViewById(R.id.et_emailid);
		et_confirm_password = (EditText)view.findViewById(R.id.et_confirm_pd);


		creatingacct_title_signup = (TextView)view.findViewById(R.id.creatingacct_title_signup);
		tv_submit_signup = (TextView)view.findViewById(R.id.tv_submit_signup);
		tv_haveacct_signup = (TextView)view.findViewById(R.id.tv_haveacct_signup);
		tv_signin_signup = (TextView)view.findViewById(R.id.tv_signin_signup);
		agree_signup = (TextView)view.findViewById(R.id.agree_signup);
		terms_tv_signup = (TextView)view.findViewById(R.id.terms_tv_signup);

		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;
		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;

		try {


			if (Session.getLanguage(getContext()).equals("0")){
				creatingacct_title_signup.setText(jsonObjectEN.getString("Create An Account"));
				//tv_submit_signup.setText(jsonObjectEN.getString("Registered Customer"));
				tv_haveacct_signup.setText(jsonObjectEN.getString("Have an account? Sign In"));
				tv_signin_signup.setText(jsonObjectEN.getString("Sign In"));
				agree_signup.setText(jsonObjectEN.getString("By creating your account, you agree to our"));

				et_fname.setHint(jsonObjectEN.getString("First Name"));
				et_lname.setHint(jsonObjectEN.getString("Last Name"));
				et_phnumber.setHint(jsonObjectEN.getString("Mobile Number"));
				et_email.setHint(jsonObjectEN.getString("Email Address"));
				et_password.setHint(jsonObjectEN.getString("Password"));
				et_confirm_password.setHint(jsonObjectEN.getString("Confirm Password"));


				terms_tv_signup.setText(jsonObjectEN.getString("Terms And Conditions"));


				confirm_pass = jsonObjectEN.getString("Please confirm your password");
				enter_password = jsonObjectEN.getString("Please Enter Password");
				enter_fname = jsonObjectEN.getString("Please Enter First Name");
				enter_lname = jsonObjectEN.getString("Please enter last name");
				enter_valid_email = jsonObjectEN.getString("Please enter valid email");
				enter_mobilenumb = jsonObjectEN.getString("Please Enter Mobile Number");
				enter_valid_mobilenumb = jsonObjectEN.getString("Please enter  Valid Phone No");
				enter_same_pass= jsonObjectEN.getString("The new passwords entered do not match");
			}
			else
			{
				creatingacct_title_signup.setText(jsonObjectAR.getString("Create An Account"));
				//tv_submit_signup.setText(jsonObjectEN.getString("Registered Customer"));
				tv_haveacct_signup.setText(jsonObjectAR.getString("Have an account? Sign In"));
				tv_signin_signup.setText(jsonObjectAR.getString("Sign In"));
				agree_signup.setText(jsonObjectAR.getString("By creating your account, you agree to our"));

				et_fname.setHint(jsonObjectAR.getString("First Name"));
				et_lname.setHint(jsonObjectAR.getString("Last Name"));
				et_phnumber.setHint(jsonObjectAR.getString("Mobile Number"));
				et_email.setHint(jsonObjectAR.getString("Email Address"));
				et_password.setHint(jsonObjectAR.getString("Password"));
				et_confirm_password.setHint(jsonObjectAR.getString("Confirm Password"));


				terms_tv_signup.setText(jsonObjectAR.getString("Terms And Conditions"));


				confirm_pass = jsonObjectAR.getString("Please confirm your password");
				enter_password = jsonObjectAR.getString("Please Enter Password");
				enter_fname = jsonObjectAR.getString("Please Enter First Name");
				enter_lname = jsonObjectAR.getString("Please enter last name");
				enter_valid_email = jsonObjectAR.getString("Please enter valid email");
				enter_mobilenumb = jsonObjectAR.getString("Please Enter Mobile Number");
				enter_valid_mobilenumb = jsonObjectAR.getString("Please enter  Valid Phone No");
				enter_same_pass= jsonObjectAR.getString("The new passwords entered do not match");
			}






		}
		catch (JSONException j){j.printStackTrace();}



		submit_btn = (LinearLayout)view.findViewById(R.id.ll_join_signup);
		submit_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(et_fname.getText().toString().length()==0){
					Snackbar.make(et_fname,""+enter_fname,Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter firstname");
				}
				else if(et_lname.getText().toString().length()==0){

					Snackbar.make(et_fname,""+enter_lname,Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter lastname");

				}
				else if(et_phnumber.getText().toString().length()!=10){
					Snackbar.make(et_fname,""+enter_valid_mobilenumb,Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter mobile");

				}
				else if(et_email.getText().toString().length()==0||!et_email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
					Snackbar.make(et_fname,""+enter_valid_email,Snackbar.LENGTH_SHORT).show();
					//showAlert("Error","Please enter email");

				}

				else if(et_password.getText().toString().length()!=6){
					Snackbar.make(et_fname,""+enter_password,Snackbar.LENGTH_SHORT).show();
				//	showAlert("Error","Please enter password");

				}
				else if(et_confirm_password.getText().toString().length()==0){
					Snackbar.make(et_fname,""+confirm_pass,Snackbar.LENGTH_SHORT).show();
				//	showAlert("Error","Please enter confirm password");

				}
				else if(!et_confirm_password.getText().toString().equals(et_password.getText().toString())){
					Snackbar.make(et_fname,""+enter_same_pass,Snackbar.LENGTH_SHORT).show();
				//	showAlert("Error","Please enter same password");
				}else {

					CallSignUpService();

				}
				/*Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();*/
			}
		});
		signin_signup_ll = (LinearLayout)view.findViewById(R.id.signin_signup_ll);
		signin_signup_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				((HomeActivity)getActivity()).knowSignedIn();
			}
		});

	return view;
	}

	public void CallSignUpService(){

		final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/member.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null)
					progressDialog.dismiss();
				try {
					JSONObject jsonObject=new JSONObject(response);
					String reply=jsonObject.getString("status");
					if(reply.equals("Failed")) {
						String msg = jsonObject.getString("message");
						Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
					} else {
						String msg = jsonObject.getString("message");
						Snackbar.make(et_fname, msg, Snackbar.LENGTH_SHORT).show();
						//((HomeActivity)getActivity()).accountFragment();
						//Intent intent = new Intent(SignupFragment.this,LoginActivity.class);
						//startActivity(intent);
						String memberid = jsonObject.getString("member_id");
						Session.setUserid(getContext(),""+memberid,""+jsonObject.getString("name"));
						Log.e("memberid",""+memberid);
						//finish();
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
						Snackbar.make(et_fname, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("fname",et_fname.getText().toString());
				parameters.put("lname",et_lname.getText().toString());
				parameters.put("email",et_email.getText().toString());
				parameters.put("phone",et_phnumber.getText().toString());
				parameters.put("password",et_password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);





//		RequestQueue requestQueue = Volley.newRequestQueue(this);
//		String URL = Constants.BASE_URL+"api/member.php";
//		JSONObject parameters = new JSONObject();
//
//		try{
//			parameters.put("fname",et_fname.getText().toString());
//			parameters.put("lname",et_lname.getText().toString());
//			parameters.put("email",et_email.getText().toString());
//			parameters.put("password",et_password.getText().toString());
//			Log.e("parameters",""+parameters.toString());
//		}catch (JSONException e){
//			e.printStackTrace();
//		}
//
//
//		final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL,parameters, new Response.Listener<JSONObject>() {
//			@Override
//			public void onResponse(JSONObject response) {
//				//progressBar.setVisibility(View.INVISIBLE);
//
//			//	Toast.makeText(getApplicationContext(),""+response.toString(),Toast.LENGTH_LONG).show();
//				Log.e("response",""+response.toString());
//				Snackbar.make(et_fname,""+response.toString(),Snackbar.LENGTH_SHORT).show();
//
//
//			}
//		}, new Response.ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				Log.e("error",error.toString());
//				//Toast.makeText(getApplicationContext(),""+error.toString()	,Toast.LENGTH_SHORT).show();
//			}
//		});
//		requestQueue.add(jsonObjectRequest);
	}



	private  void showAlert(final String tittle, String message) {



	}
}
