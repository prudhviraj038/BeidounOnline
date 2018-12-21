package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddAddressFragment extends Fragment {
	TextView page_title;
	TextView btn_edit;
	LinearLayout prdcheckout_btn;
	LinearLayout menu_btn,back_btn,submit_btn,proceedtopay_ll_btn;
	LinearLayout popup_checkout;





	EditText firstname,lastname,address,email,phone,city;
	EditText et_pincode_checkout;


	LinearLayout nextstep_ll,countries_popup_shipping;

	ListView countries_lv_shipping,states_lv_shipping;

	Countries_Adapter countries_adapter ;
	StatesAdapter statesAdapter;

	String member,name;
	TextView title_shipping_tv,nextstepbtn_tv;

    AddressChechout_Data addressChechout_data;

	ArrayList<CountryData> countriesdata = new ArrayList<>();
	ArrayList<StatesData> statesData = new ArrayList<>();
    String addressType,addressid;

	ImageView back;


	TextView et_country_checkout,state;


	public static AddAddressFragment newInstance(int someInt) {
		AddAddressFragment myFragment = new AddAddressFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shipping, container, false);

		countries_popup_shipping = (LinearLayout)view.findViewById(R.id.countries_popup_shipping);

		countries_lv_shipping = (ListView)view.findViewById(R.id.countries_lv_shipping);
		states_lv_shipping = (ListView)view.findViewById(R.id.states_lv_shipping);


		firstname = (EditText)view.findViewById(R.id.et_firstname_checkout);
		lastname = (EditText)view.findViewById(R.id.et_lastname_checkout);
		address = (EditText)view.findViewById(R.id.et_address_checkout);
		email = (EditText)view.findViewById(R.id.et_email_checkout);
		phone = (EditText)view.findViewById(R.id.et_phone_checkout);
		city = (EditText)view.findViewById(R.id.et_city_checkout);
		et_pincode_checkout = (EditText) view.findViewById(R.id.et_pincode_checkout);

		et_country_checkout = (TextView)view.findViewById(R.id.et_country_checkout);

		state = (TextView)view. findViewById(R.id.tv_state_checkout);

		nextstepbtn_tv = (TextView)view.findViewById(R.id.nextstepbtn_tv);
		title_shipping_tv = (TextView)view.findViewById(R.id.title_shipping_tv);
		title_shipping_tv.setText("Add Address");




		et_country_checkout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (countries_popup_shipping.getVisibility() == View.VISIBLE) {
					countries_popup_shipping.setVisibility(View.GONE);
					states_lv_shipping.setVisibility(View.GONE);

				} else {
					states_lv_shipping.setVisibility(View.GONE);
					countriesdata.clear();
					getCountriesList();
					countries_popup_shipping.setVisibility(View.VISIBLE);
					countries_lv_shipping.setVisibility(View.VISIBLE);
				}
			}
		});




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
					Snackbar.make(firstname, "please select city", Snackbar.LENGTH_SHORT).show();
				} else if (et_country_checkout.getText().toString().equals("")) {
					Snackbar.make(firstname, "please select country", Snackbar.LENGTH_SHORT).show();
				} else if (state.getText().toString().equals("")) {
					Snackbar.make(firstname, "please enter state", Snackbar.LENGTH_SHORT).show();
				} else {


					//sendShippingAddress();
					if (addressType.equals("edit")) {

						sendShippingAddress(addressid);
						Log.e("id", addressid);
					} else {
					addAddressId("add", "add", addressChechout_data);
				}
			}

			}
		});




		countries_lv_shipping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				countries_popup_shipping.setVisibility(View.GONE);

				String title = countriesdata.get(position).name;

				et_country_checkout.setText(title);

				statesData=countriesdata.get(position).states;
				Log.e("states",""+countriesdata.get(position).states.get(position).title);

				statesAdapter = new StatesAdapter(getActivity(),statesData);

			}
		});





		state.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (countries_popup_shipping.getVisibility() == View.VISIBLE) {
					countries_popup_shipping.setVisibility(View.GONE);
					countries_lv_shipping.setVisibility(View.GONE);
				} else {
					countriesdata.clear();
					///getCountriesList();
					//countries_lv_shipping.setAdapter(statesAdapter);
					states_lv_shipping.setAdapter(statesAdapter);
					countries_popup_shipping.setVisibility(View.VISIBLE);
					states_lv_shipping.setVisibility(View.VISIBLE);
					countries_lv_shipping.setVisibility(View.GONE);
				}

			}
		});

		states_lv_shipping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				state.setText(statesData.get(position).title);
				countries_popup_shipping.setVisibility(View.GONE);
			}
		});







		return view;
	}


	public void addAddressId(String type,String id,AddressChechout_Data data) {

		addressType = type;
		Log.e("type",type);
		addressid = id;

		addressChechout_data = data;

		if (type.equals("edit")) {
			Log.e("address","edit");
			title_shipping_tv.setText("Edit Address");
			nextstepbtn_tv.setText("Edit");

			if (!data.equals(null)){


				firstname.setText(data.fname);
				lastname.setText(data.lname);
				email.setText(data.email);
				address.setText(data.address);
				phone.setText(data.phone);
				Log.e("addressIdAdd",data.id);
				Log.e("addressId",id);
				city.setText(data.city);
				et_country_checkout.setText(data.country);
				et_pincode_checkout.setText(data.pincode);
				state.setText(data.state);

			}else if (firstname.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter firstname", Snackbar.LENGTH_SHORT).show();
			} else if (lastname.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter lastname", Snackbar.LENGTH_SHORT).show();
			} else if (address.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter address", Snackbar.LENGTH_SHORT).show();
			} else if (email.getText().toString().equals("") || !email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
				Snackbar.make(firstname, "please enter valid email", Snackbar.LENGTH_SHORT).show();
			} else if (phone.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter phonenumber", Snackbar.LENGTH_SHORT).show();
			}
			else if (city.getText().toString().equals("")) {
				Snackbar.make(firstname, "please select city", Snackbar.LENGTH_SHORT).show();
			} else if (et_country_checkout.getText().toString().equals("")) {
				Snackbar.make(firstname, "please select country", Snackbar.LENGTH_SHORT).show();
			} else if (state.getText().toString().equals("")) {
				Snackbar.make(firstname, "please enter state", Snackbar.LENGTH_SHORT).show();
			} else {
				sendShippingAddress(id);

			}

		}



		else if (type.equals("checkout")) {
			Log.e("address","checkout");
			sendShippingAddress("checkout");

		}

		else if (type.equals("add")){
			Log.e("address","add");

			sendShippingAddress("add");





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

						Toast.makeText(getContext(),""+jsonObject.getString("message"),Toast.LENGTH_SHORT).show();


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
				if (type.equals("add")||type.equals("checkout")){
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



	public void getCountriesList(){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/countries.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resCountries",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				countries_popup_shipping.setVisibility(View.VISIBLE);
				try {
					JSONArray jsonArray =new JSONArray(response);
					for (int i = 0;i<jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						CountryData temp = new CountryData(jsonObject);
						countriesdata.add(temp);

					}
					countries_adapter = new Countries_Adapter(getActivity(),countriesdata);

					countries_lv_shipping.setAdapter(countries_adapter);
					countries_adapter.notifyDataSetChanged();


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

				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}

}
