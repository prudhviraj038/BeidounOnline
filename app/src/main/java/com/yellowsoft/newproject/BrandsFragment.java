package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class BrandsFragment extends Fragment {


	RecyclerView brands_rv;
	Brands_Adapter brands_adapter;

	ArrayList<Brands_Data> brands_data = new ArrayList<>();


	public static BrandsFragment newInstance(int someInt) {
		BrandsFragment myFragment = new BrandsFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}
	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_brands, container, false);
		CallProductdetails();

		brands_rv = (RecyclerView)view.findViewById(R.id.brands_recycler);

		brands_adapter = new Brands_Adapter(getContext(),brands_data);

		GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);

		brands_rv.setLayoutManager(gridLayoutManager);

		/*home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));
		home_data.add(new Home_data(R.drawable.sales));*/



		brands_rv.setAdapter(brands_adapter);

		return view;
	}


	//product request
	public void CallProductdetails(){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/brands.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null&& progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				try {

					JSONArray jsonArray = new JSONArray(response);
					Log.e("jsonArray",""+jsonArray.toString());
					for (int i = 0;i<jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						Log.e("jsonobject",""+jsonObject.getString("image"));
						Log.e("jsonobjectLength",""+jsonObject.length());
						Brands_Data temp = new Brands_Data(jsonObject);
						brands_data.add(temp);
						//productsData.add(products_Data);
					}
					brands_adapter.notifyDataSetChanged();
					//Log.e("jsonobject",""+jsonArray.getJSONObject())

					//JSONObject jsonObject = new JSONObject(jsonArray.getJSONObject(0));


				//	Log.e("imagessssss",""+jsonArray1.getJSONObject(0).getString("image"));

				/*	if (jsonArray1.length()>1){
						Log.e("length","length");
						for (int j=0;j<=jsonArray1.length();j++){
							//slidingImage_data.add(new SlidingImage_Data(jsonArray.getJSONObject(j).getString("image")));
							String s = jsonArray1.getString(j);
							Log.e("s",""+s);
							slidingImage_data.add(new SlidingImage_Data(s));
							Log.e("imagessssss",""+jsonArray1.getString(j));

						}

					}*/


				} catch (JSONException e) {
					e.printStackTrace();
				}
			//	slidingPageAdapter.notifyDataSetChanged();
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("error",""+error);
						if(progressDialog!=null)
							progressDialog.dismiss();
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				//parameters.put("email",u_name.getText().toString());
			//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}
}
