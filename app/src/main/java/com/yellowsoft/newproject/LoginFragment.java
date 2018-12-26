package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class LoginFragment extends Fragment {

	ListView listView;
	TextView tv_signin_login,tv_forget;

	TextView register_title_login,create_title_login,regcustomer_title_login,email_title_login,password_title_login;


	LinearLayout createact_ll,ll_signin_login;

	EditText et_usrnmane_login,et_password_login;

	ImageView share_img;

	String enter_uname,enter_password;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);




		createact_ll = (LinearLayout)view.findViewById(R.id.createact_ll);
		createact_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).signupFragment();
			}
		});

		et_usrnmane_login = (EditText) view.findViewById(R.id.et_usrnmane_login);
		et_password_login = (EditText) view.findViewById(R.id.et_password_login);


		ll_signin_login = (LinearLayout)view.findViewById(R.id.ll_signin_login);
		ll_signin_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//((HomeActivity)getActivity()).accountFragment();
				if (et_usrnmane_login.getText().toString().equals("")){

					Snackbar.make(email_title_login,""+enter_uname,Snackbar.LENGTH_SHORT).show();
				}else if (et_password_login.getText().toString().equals("")){
					Snackbar.make(email_title_login,""+enter_password,Snackbar.LENGTH_SHORT).show();

				}
				else {
					CallLoginService();
				}

			}
		});



		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;
		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;



		register_title_login = (TextView)view.findViewById(R.id.register_title_login);
		create_title_login = (TextView)view.findViewById(R.id.create_title_login);
		regcustomer_title_login = (TextView)view.findViewById(R.id.regcustomer_title_login);
		email_title_login = (TextView)view.findViewById(R.id.email_title_login);
		password_title_login = (TextView)view.findViewById(R.id.password_title_login);

		tv_signin_login= (TextView)view.findViewById(R.id.tv_signin_login);
		tv_forget= (TextView)view.findViewById(R.id.tv_forget);


		try {

			if (Session.getLanguage(getContext()).equals("0")){
				regcustomer_title_login.setText(jsonObjectEN.getString("Registered Customer"));
				create_title_login.setText(jsonObjectEN.getString("Registered Customer"));
				regcustomer_title_login.setText(jsonObjectEN.getString("Registered Customer"));
				email_title_login.setText(jsonObjectEN.getString("Registered Customer"));
				password_title_login.setText(jsonObjectEN.getString("Registered Customer"));

				et_usrnmane_login.setHint(jsonObjectEN.getString("Email Address"));
				et_password_login.setHint(jsonObjectEN.getString("Password"));


				tv_signin_login.setText(jsonObjectEN.getString("Registered Customer"));
				tv_forget.setText(jsonObjectEN.getString("Registered Customer"));

				enter_uname = jsonObjectEN.getString("Please Enter email address");
				enter_password = jsonObjectEN.getString("Please Enter Password");
			}
			else
			{
				regcustomer_title_login.setText(jsonObjectAR.getString("Registered Customer"));
				create_title_login.setText(jsonObjectAR.getString("Registered Customer"));
				regcustomer_title_login.setText(jsonObjectAR.getString("Registered Customer"));
				email_title_login.setText(jsonObjectAR.getString("Registered Customer"));
				password_title_login.setText(jsonObjectAR.getString("Registered Customer"));

				et_usrnmane_login.setHint(jsonObjectAR.getString("Email Address"));
				et_password_login.setHint(jsonObjectAR.getString("Password"));

				tv_signin_login.setText(jsonObjectAR.getString("Registered Customer"));
				tv_forget.setText(jsonObjectAR.getString("Registered Customer"));

				enter_uname = jsonObjectAR.getString("Please Enter email address");
				enter_password = jsonObjectAR.getString("Please Enter Password");
			}






		}
		catch (JSONException j){j.printStackTrace();}



		return view;
	}
	public static LoginFragment newInstance(int someInt) {
		LoginFragment myFragment = new LoginFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}



	public void CallLoginService(){

		final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/login.php";

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


						((HomeActivity)getActivity()).accountFragment();
						String memberid = jsonObject.getString("member_id");


						String name = jsonObject.getString("name");

                        Session.setUserid(getContext(),memberid,name);


						Intent intent = new Intent(getContext(),HomeActivity.class);

						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

						getActivity().startActivity(intent);

					}

					else {

						String msg = jsonObject.getString("message");
						Log.e("message",""+msg);

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

					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("email",et_usrnmane_login.getText().toString());
				parameters.put("password",et_password_login.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}
    

}
