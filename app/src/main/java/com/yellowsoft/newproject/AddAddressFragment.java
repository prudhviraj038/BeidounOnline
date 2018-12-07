package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddAddressFragment extends Fragment {
	TextView page_title;
	TextView btn_edit,state;
	LinearLayout prdcheckout_btn;
	LinearLayout menu_btn,back_btn,submit_btn,proceedtopay_ll_btn;
	LinearLayout popup_checkout;



	StatesAdapter statesAdapter;

	EditText firstname,lastname,address,email,phone,city,et_country_checkout;
	EditText et_pincode_checkout;
	ListView states_lv;

	LinearLayout nextstep_ll;
	String member,name;
	TextView title_shipping_tv;

    AddressChechout_Data addressChechout_data;

	ImageView back;
	public static AddAddressFragment newInstance(int someInt) {
		AddAddressFragment myFragment = new AddAddressFragment();

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
		et_pincode_checkout = (EditText) view.findViewById(R.id.et_pincode_checkout);
		et_country_checkout = (EditText)view.findViewById(R.id.et_country_checkout);

		state = (EditText)view. findViewById(R.id.tv_state_checkout);

		title_shipping_tv = (TextView)view.findViewById(R.id.title_shipping_tv);
		title_shipping_tv.setText("Add Address");





		nextstep_ll = (LinearLayout)view.findViewById(R.id.nextstep_ll);
		nextstep_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (firstname.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter firstname", Snackbar.LENGTH_SHORT).show();
				} else if (lastname.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter lastname", Snackbar.LENGTH_SHORT).show();
				} else if (address.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter address", Snackbar.LENGTH_SHORT).show();
				} else if (email.getText().toString().equals("") || !email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
					Snackbar.make(firstname, "please enter valid email", Snackbar.LENGTH_SHORT).show();
				} else if (phone.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter phonenumber", Snackbar.LENGTH_SHORT).show();
				} else if (city.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter city", Snackbar.LENGTH_SHORT).show();
				} else if (et_country_checkout.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter countryname", Snackbar.LENGTH_SHORT).show();
				} else if (state.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter state", Snackbar.LENGTH_SHORT).show();
				} else {


					//sendShippingAddress();
					addAddressId("add",addressChechout_data);
				}

			}
		});





		return view;
	}


	public void addAddressId(String id,AddressChechout_Data data) {


		if (id.equals("add")) {
			sendShippingAddress("add");
		} else if (id.equals("checkout")) {
			sendShippingAddress("checkout");
		} else {

			if (!data.equals(null)){


				firstname.setText(data.fname);
				lastname.setText(data.lname);
				email.setText(data.email);
				address.setText(data.address);
				phone.setText(data.phone);
			}
			else if (city.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter city", Snackbar.LENGTH_SHORT).show();
			} else if (et_country_checkout.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter countryname", Snackbar.LENGTH_SHORT).show();
			} else if (state.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter state", Snackbar.LENGTH_SHORT).show();
			} else {
				sendShippingAddress(id);
				Log.e("addressIdAdd",id);
			}




		}
	}


	public void sendShippingAddress(final String type){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/add-address.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resAddaddress",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONObject jsonObject=new JSONObject(response);
					String reply=jsonObject.getString("status");
					Log.e("status",""+reply);

					if(reply.equals("Success")) {

						Toast.makeText(getContext(),"Address added successfully",Toast.LENGTH_SHORT).show();


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
				if (type.equals("add")){
					parameters.put("fname",firstname.getText().toString());
					parameters.put("lname",lastname.getText().toString());
					parameters.put("phone",phone.getText().toString());
					parameters.put("address",address.getText().toString());
					parameters.put("email",email.getText().toString());
					parameters.put("country",et_country_checkout.getText().toString());
					parameters.put("state",state.getText().toString());
					parameters.put("pincode",et_pincode_checkout.getText().toString());
					parameters.put("city",city.getText().toString());
					parameters.put("member_id",Session.getUserid(getActivity()));

				}
				else if (type.equals("checkout")){

					parameters.put("fname",firstname.getText().toString());
					parameters.put("lname",lastname.getText().toString());
					parameters.put("phone",phone.getText().toString());
					parameters.put("address",address.getText().toString());
					parameters.put("email",email.getText().toString());
					parameters.put("country",et_country_checkout.getText().toString());
					parameters.put("state",state.getText().toString());
					parameters.put("pincode",et_pincode_checkout.getText().toString());
					parameters.put("city",city.getText().toString());
					parameters.put("member_id",Session.getUserid(getActivity()));
				}
				else {
					parameters.put("address_id",type);
					parameters.put("fname",firstname.getText().toString());
					parameters.put("lname",lastname.getText().toString());
					parameters.put("phone",phone.getText().toString());
					parameters.put("address",address.getText().toString());
					parameters.put("email",email.getText().toString());
					parameters.put("country",et_country_checkout.getText().toString());
					parameters.put("state",state.getText().toString());
					parameters.put("pincode",et_pincode_checkout.getText().toString());
					parameters.put("city",city.getText().toString());
					parameters.put("member_id",Session.getUserid(getActivity()));
				}


				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}
}
