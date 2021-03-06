package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ShippingAddressFragment extends Fragment {



	Cart_Adapter cart_adapter;
	ArrayList<Cart_Data> cart_data = new ArrayList<>();

	EditText firstname,lastname,address,email,phone,city,et_country_checkout,state;
	LinearLayout nextstep_ll;
	TextView title_shipping_tv;


	RecyclerView cart_rv;

	public static ShippingAddressFragment newInstance(int someInt) {
		ShippingAddressFragment myFragment = new ShippingAddressFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shipping, container, false);


		firstname = (EditText)view.findViewById(R.id.et_firstname_checkout);
		lastname = (EditText)view.findViewById(R.id.et_lastname_checkout);
		address = (EditText)view.findViewById(R.id.et_address_checkout);
		email = (EditText)view.findViewById(R.id.et_email_checkout);
		phone = (EditText)view.findViewById(R.id.et_phone_checkout);
		city = (EditText)view.findViewById(R.id.et_city_checkout);
		et_country_checkout = (EditText)view.findViewById(R.id.et_country_checkout);

		state = (EditText)view. findViewById(R.id.tv_state_checkout);

		title_shipping_tv = (TextView)view.findViewById(R.id.title_shipping_tv);





		nextstep_ll = (LinearLayout)view.findViewById(R.id.nextstep_ll);
		nextstep_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (firstname.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter firstname",Snackbar.LENGTH_SHORT).show();
				}
				else if (lastname.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter lastname",Snackbar.LENGTH_SHORT).show();
				}
				else if(address.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter address",Snackbar.LENGTH_SHORT).show();
				}
				else if(email.getText().toString().equals("")||!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
					Snackbar.make(firstname,"please enter valid email",Snackbar.LENGTH_SHORT).show();
				}
				else if(phone.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter phonenumber",Snackbar.LENGTH_SHORT).show();
				}
				else if(city.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter city",Snackbar.LENGTH_SHORT).show();
				}

				else if(et_country_checkout.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter countryname",Snackbar.LENGTH_SHORT).show();
				}
				else if(state.getText().toString().equals("")){
					Snackbar.make(firstname,"please enter state",Snackbar.LENGTH_SHORT).show();
				}
				else {


					sendShippingAddress();
				}

				//CallAddAddressService(member,name,address.getText().toString(),city.getText().toString(),state.getText().toString(),country.getText().toString(),pincode.getText().toString(),phone.getText().toString());
			}
		});

		return  view;

	}

public void sendShippingAddress(){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/coupon-check.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resShippingAddress",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONObject jsonObject=new JSONObject(response);
					String reply=jsonObject.getString("status");
					Log.e("status",""+reply);

					if(reply.equals("Success")) {

						Toast.makeText(getContext(),"Successfully order placeed",Toast.LENGTH_SHORT).show();


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
						//Toast.makeText(hs.this,"Internet error",Toast.LENGTH_SHORT).show();
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				/*parameters.put("coupon",coupen);
				parameters.put("cart_total",total);*/
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

}
