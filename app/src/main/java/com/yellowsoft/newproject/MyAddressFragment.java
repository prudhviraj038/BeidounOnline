package com.yellowsoft.newproject;

import android.app.ProgressDialog;
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

public class MyAddressFragment extends Fragment {

	RecyclerView myaddress_rv_myadress;

	Myaddress_Adapter myaddress_adapter;
	ArrayList<AddressChechout_Data> myaddress_data = new ArrayList<>();

	TextView addaddress_myaddress_tv;


	public static MyAddressFragment newInstance(int someInt) {
		MyAddressFragment myFragment = new MyAddressFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_myaddress, container, false);

		myaddress_rv_myadress = (RecyclerView)view.findViewById(R.id.myaddress_rv_myadress);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		myaddress_rv_myadress.setLayoutManager(linearLayoutManager);

		myaddress_adapter = new Myaddress_Adapter(getContext(),myaddress_data,this);

		myaddress_rv_myadress.setAdapter(myaddress_adapter);





		addaddress_myaddress_tv = (TextView)view.findViewById(R.id.addaddress_myaddress_tv);
		addaddress_myaddress_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).addAddressFragment("add","add",myaddress_data.get(0));
			}
		});


		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;

		try {
			if (Session.getLanguage(getContext()).equals("0")){
				addaddress_myaddress_tv.setText(jsonObjectEN.getString("Add New Address"));
			}else {
				addaddress_myaddress_tv.setText(jsonObjectAR.getString("Add New Address"));

			}

		}catch (JSONException j){
			j.printStackTrace();
		}

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
						myaddress_data.add(temp);
					}





				} catch (JSONException e) {
					e.printStackTrace();
				}
				myaddress_adapter.notifyDataSetChanged();

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

	public void deleteAddress(final String id){

		final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL + "api/del-address.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resDeleteaddress", response);
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				try {
					JSONObject jsonObject =new JSONObject(response);
					if (jsonObject.getString("status").equals("Success")){
						Toast.makeText(getActivity(),""+jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
						myaddress_rv_myadress.setAdapter(myaddress_adapter);
						myaddress_adapter.notifyDataSetChanged();
					}
					else
						Toast.makeText(getActivity(),""+jsonObject.getString("message"),Toast.LENGTH_SHORT).show();





				} catch (JSONException e) {
					e.printStackTrace();
				}




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

				parameters.put("address_id",id);
				parameters.put("member_id",Session.getUserid(getActivity()));

				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}
}
