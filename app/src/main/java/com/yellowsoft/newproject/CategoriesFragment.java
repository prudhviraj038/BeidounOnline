package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

public class CategoriesFragment extends Fragment {

	RecyclerView categories_rv;
	Categories_Adapter categories_adapter;
	ArrayList<CatergoriesData> catergoriesData = new ArrayList<>();

	SubCategoriesData subCategoriesData;



	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_categories, container, false);
		Log.e("categories","categories");
		callCategories(0);

		categories_rv = (RecyclerView)view.findViewById(R.id.categories_rv);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		categories_rv.setLayoutManager(linearLayoutManager);

		Bundle bundle = getArguments();
		if (bundle!=null){
			if (bundle.containsKey("subcategories")){
				CatergoriesData catergoriesData = (CatergoriesData)getArguments().getSerializable("subcategories");
				Log.e("subcat",""+catergoriesData.subcat_cnt);
				callCategories(Integer.parseInt(catergoriesData.id));

			}
		}


	//	subCategoriesData = (SubCategoriesData)getArguments().getSerializable("subcategories");

		categories_adapter = new Categories_Adapter(getContext(),catergoriesData);
		categories_rv.setAdapter(categories_adapter);





		return view;
	}
	public static CategoriesFragment newInstance(int someInt) {
		CategoriesFragment myFragment = new CategoriesFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}






	public void callCategories(final int subcat_count){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/category.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null&& progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				try {
					catergoriesData.clear();

					JSONArray jsonArray = new JSONArray(response);
					Log.e("jsonArray",""+jsonArray.toString());
					for (int i = 0;i<jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						Log.e("jsonobject",""+jsonObject.getString("image"));
						Log.e("jsonobjectLength",""+jsonObject.length());
						CatergoriesData temp = new CatergoriesData(jsonObject);
						catergoriesData.add(temp);
						//productsData.add(products_Data);
					}
					categories_adapter.notifyDataSetChanged();
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

				if (subcat_count != 0 ) {
					parameters.put("parent",String.valueOf(subcat_count));
				}
				//parameters.put("email",u_name.getText().toString());
				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}


}
