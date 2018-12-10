package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckoutAddressFragment extends Fragment {



	RecyclerView myaddress_rv;
	ArrayList<AddressChechout_Data> addressChechout_data = new ArrayList<>();
	CheckoutAddress_Adapter checkoutAddress_adapter;

	TextView nextstep_checkoutadddress;
	LinearLayout addnewaddress_checkout;


	public static CheckoutAddressFragment newInstance(int someInt) {
		CheckoutAddressFragment myFragment = new CheckoutAddressFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_checkoutaddress, container, false);

		nextstep_checkoutadddress = (TextView)view.findViewById(R.id.nextstep_checkoutadddress);

		addnewaddress_checkout = (LinearLayout) view.findViewById(R.id.addnewaddress_checkout);

		myaddress_rv = (RecyclerView)view.findViewById(R.id.myaddress_rv);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		myaddress_rv.setLayoutManager(linearLayoutManager);

		checkoutAddress_adapter = new CheckoutAddress_Adapter(getContext(),addressChechout_data);
		myaddress_rv.setAdapter(checkoutAddress_adapter);

		nextstep_checkoutadddress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				/*
				Log.e("sendtocheckout","sendtocheckout");
				Intent intent = new Intent(getContext(),CheckoutActivty.class);
				startActivity(intent);*/
			}
		});

		addnewaddress_checkout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).addAddressFragment("checkout",addressChechout_data.get(0));
			}
		});


		callAddressList();


		return view;
	}




	public void callAddressList() {

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL + "api/address-list.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res", response);
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				try {

					JSONArray jsonArray = new JSONArray(response);
					Log.e("jsonarraryLength",""+jsonArray.length());


					Log.e("jsonArray", "" + jsonArray.toString());

					//noof_results_tv.setText(""+jsonArray.length()+" ");



					for (int i=0;i<=jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);

						AddressChechout_Data temp = new AddressChechout_Data(jsonObject);
						addressChechout_data.add(temp);
					}





				} catch (JSONException e) {
					e.printStackTrace();
				}
				checkoutAddress_adapter.notifyDataSetChanged();

			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("error", "" + error);
						if (progressDialog != null)
							progressDialog.dismiss();
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> parameters = new HashMap<String, String>();

				parameters.put("member_id",Session.getUserid(getActivity()));

				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}



}
