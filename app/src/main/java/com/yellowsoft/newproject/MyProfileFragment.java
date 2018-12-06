package com.yellowsoft.newproject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyProfileFragment extends Fragment {
	TextView page_title,tv_username_myprofile;
	TextView dojoining_tv,referedby_tv;
	TextView referredbyname_tv_myprofile;
	LinearLayout back_btn,menu_btn,changepassword_btn;
	ImageView back;
	CardView edit_btn;

	String member;
	Dialog myDialog;

	EditText ed_fname,ed_lname,ed_phone,ed_email,ed_acc_name,ed_acc_number,ed_ifsc_code,ed_acc_email;
	EditText ed_bankaccnumber_confirm;

	EditText old_password,password,new_password;
	LinearLayout submit_cp;



	LinearLayout update_ll;
	public static MyProfileFragment newInstance(int someInt) {
		MyProfileFragment myFragment = new MyProfileFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_myprofile, container, false);

		myDialog = new Dialog(getActivity());
		myDialog.setContentView(R.layout.activity_changepassword);

		//callAddressListService(member,"3");




		ed_fname = (EditText)view. findViewById(R.id.ed_usrname_myprofile);
		ed_lname = (EditText)view. findViewById(R.id.ed_vechilename_myprofile);
		ed_phone = (EditText)view. findViewById(R.id.ed_phone_myprofile);

		ed_email = (EditText)view. findViewById(R.id.ed_email_myprofile);







		changepassword_btn = (LinearLayout)view.findViewById(R.id.changepassword_ll);

		changepassword_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				/*Intent intent = new Intent(getContext(),ChangePasswordActivity.class);
				startActivity(intent);*/
				myDialog.show();


			}
		});
		update_ll = (LinearLayout)view. findViewById(R.id.update_ll);



		update_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if(ed_fname.getText().toString().equals("")){

					Snackbar.make(update_ll,"Enter First Name",Snackbar.LENGTH_SHORT).show();

				}else if(ed_lname.getText().toString().equals("")){

					Snackbar.make(update_ll,"Enter Last Name",Snackbar.LENGTH_SHORT).show();

				}else if(ed_phone.getText().toString().length()!=10){

					Snackbar.make(update_ll,"Enter Phone Number",Snackbar.LENGTH_SHORT).show();

				}else if(ed_email.getText().toString().equals("")||!ed_email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){

					Snackbar.make(update_ll,"Enter Valid Email Address",Snackbar.LENGTH_SHORT).show();

				}

				else{


					//setProfileDetails();


				}

			}
		});




		old_password = (EditText)myDialog.findViewById(R.id.et_opassword);
		new_password = (EditText)myDialog.findViewById(R.id.et_newpassword);
		password = (EditText)myDialog.findViewById(R.id.et_cpassword);

		submit_cp = (LinearLayout)myDialog.findViewById(R.id.ll_submit_changepassword);
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


					callChangePassword();

				}
			}
		});


		return view;
	}





	public void getProfileDetails (){


		final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/members.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resMyprofile",response);

				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONArray jsonArray = new JSONArray(response);

					JSONObject jsonObject= jsonArray.getJSONObject(0);




						ed_fname.setText(jsonObject.getString("fname"));
						ed_lname.setText(jsonObject.getString("lname"));
						Session.setUserid(getActivity(),Session.getUserid(getActivity()),jsonObject.getString("fname")+jsonObject.getString("lname"));

						if (jsonObject.getString("phone").equals("")){

						}
						else {
							ed_phone.setText(jsonObject.getString("phone"));
							ed_phone.setEnabled(false);
						}



						ed_email.setText(jsonObject.getString("email"));

						dojoining_tv.setText(jsonObject.getString("scheme_amount_date"));
						referedby_tv.setText(jsonObject.getString("referred_code"));
					referredbyname_tv_myprofile.setText(jsonObject.getString("referred_name"));
						//+"-"+jsonObject.getString("referred_name")

						ed_acc_name.setText(jsonObject.getString("bank"));
						ed_acc_number.setText(jsonObject.getString("acno"));
					ed_bankaccnumber_confirm.setText(jsonObject.getString("acno"));
						ed_ifsc_code.setText(jsonObject.getString("ifsc"));
						ed_acc_email.setText(jsonObject.getString("upid"));








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
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("member_id",Session.getUserid(getContext()));
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

	public void setProfileDetails (){


		final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);

		String URL = Session.BASE_URL+"api/edit-member.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resMyprofile",response);

				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					//JSONArray jsonArray = new JSONArray(response);
					JSONObject jsonObject= new JSONObject(response);
					String reply  = jsonObject.getString("status");

					if(reply.equals("Success")) {

						Toast.makeText(getActivity(), "You have successfully updated your account", Toast.LENGTH_SHORT).show();//You have successfully updated your account
					}
					else {
						Toast.makeText(getActivity(),""+jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
					}


				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getActivity(),""+e.getMessage().toString(),Toast.LENGTH_SHORT).show();


				}
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(progressDialog!=null)
							progressDialog.dismiss();
						Toast.makeText(getActivity(),"Something went wrong, try after sometime ",Toast.LENGTH_SHORT).show();

						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("member_id",Session.getUserid(getActivity()));
				parameters.put("fname",ed_fname.getText().toString());
				parameters.put("lname",ed_lname.getText().toString());
				parameters.put("phone",ed_phone.getText().toString());
				parameters.put("email",ed_email.getText().toString());

				/*if(ed_acc_email.getText().toString().equals("")) {
					parameters.put("type", "1");
					parameters.put("bank",ed_acc_name.getText().toString());
					parameters.put("acno",ed_acc_number.getText().toString());
					parameters.put("ifsc",ed_ifsc_code.getText().toString());


				}else{
					parameters.put("type","2");
					parameters.put("upid",ed_acc_email.getText().toString());

				}*/





				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

	public void callChangePassword(){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
						Toast.makeText(getActivity(),""+reply.toString(),Toast.LENGTH_SHORT).show();

						myDialog.dismiss();

					}

					else {

						String msg = jsonObject.getString("message");
						Log.e("message",""+msg);
						Toast.makeText(getActivity(),""+msg.toString(),Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getActivity(),""+error.toString(),Toast.LENGTH_SHORT).show();
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}

				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("cpassword",password.getText().toString());
				parameters.put("password",new_password.getText().toString());
				parameters.put("opassword",old_password.getText().toString());
				parameters.put("member_id",Session.getUserid(getActivity()));

				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}
}
