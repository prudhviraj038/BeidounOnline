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

public class MyAccountFragment extends Fragment {

	ListView listView;
	TextView tv_my_ref_code,tv_username_myaccount;


	LinearLayout createact_ll,ll_signin_login;

	EditText et_usrnmane_login,et_password_login;

	ImageView share_img;

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
				((HomeActivity)getActivity()).accountFragment();
			}
		});






		return view;
	}
	public static MyAccountFragment newInstance(int someInt) {
		MyAccountFragment myFragment = new MyAccountFragment();

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
