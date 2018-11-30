package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class ShopFragment extends Fragment {

	TextView title, description_tv,noof_results_tv;
	RecyclerView shop_rv;

	ArrayList<Shop_Data> shopData= new ArrayList<Shop_Data>();
	Shop_Adapter shop_adapter;

	LinearLayout filter,filter_top;
	LinearLayout  instagram_ll;
	ImageView grid_img, vertical_img;

	boolean grid=true;
	boolean vertical;

	String brandid,categoryid;

	public static ShopFragment newInstance(int someInt) {
		ShopFragment myFragment = new ShopFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shop, container, false);
		Log.e("shopfragment", "shopfragment");
		//
		//CallProductdetails();

		title = (TextView)view.findViewById(R.id.tilte_shop_frag);
		noof_results_tv = (TextView)view.findViewById(R.id.noof_results_tv);

		grid_img = (ImageView)view.findViewById(R.id.grid_img);
		vertical_img = (ImageView)view.findViewById(R.id.vertical_img);


		instagram_ll = (LinearLayout)view.findViewById(R.id.instagram_ll);
		//filter = (LinearLayout)view.findViewById(R.id.filter_ll);
		filter_top = (LinearLayout)view.findViewById(R.id.filter_ll_top);

		grid_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));
		vertical_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));





		final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);







		grid_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				grid=true;
				vertical=false;
				grid_img.setColorFilter(getResources().getColor(R.color.colorBlack));
				vertical_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));
				shop_rv.setLayoutManager(gridLayoutManager);
			}
		});

		vertical_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vertical=true;
				grid=false;
				grid_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));
				vertical_img.setColorFilter(getResources().getColor(R.color.colorBlack));
				shop_rv.setLayoutManager(linearLayoutManager);
			}
		});

		shop_rv = (RecyclerView)view.findViewById(R.id.rv_shop);
		shop_rv.setNestedScrollingEnabled(false);

		shop_adapter = new Shop_Adapter(getContext(),shopData);





		shop_rv.setAdapter(shop_adapter);

		shop_rv.setLayoutManager(gridLayoutManager);

		if (grid==true){
			shop_rv.setLayoutManager(gridLayoutManager);
			grid_img.setColorFilter(getResources().getColor(R.color.colorBlack));
			vertical_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));
		}
		else {
			shop_rv.setLayoutManager(linearLayoutManager);
		}




/*
		final Handler handler = new Handler();

		final 	Runnable update = new Runnable() {
			public void run() {
				if (viewPager.getCurrentItem() < slidingImage_data.size() - 1) {
					viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
				}
				else {
					viewPager.setCurrentItem(0);
				}

			}
		};


		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				handler.post(update);
			}
		}, 3000, 5000);*/

		//Snackbar.make(quantity,"shop fragment selected",Snackbar.LENGTH_SHORT).show();
		return view;
	}

	public void setParamentersBrands(String brandId,String titles){

        if (brandId.equals("0")){
            instagram_ll.setVisibility(View.GONE);
        }

        title.setText(""+titles);

		this.brandid = brandId ;
		callProducts("brands",brandId);

	}

	public void setParamentersCategories(String categories,String titles){

        if (categories.equals("0")){
            instagram_ll.setVisibility(View.GONE);
        }
		title.setText(""+titles);

		this.categoryid = categories ;
		callProducts("category",categories);

	}


	public void callProducts(final String type, final String id) {

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL + "api/products.php";

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

						Shop_Data temp = new Shop_Data(jsonObject);
						shopData.add(temp);
					}





				} catch (JSONException e) {
					e.printStackTrace();
				}
				shop_adapter.notifyDataSetChanged();
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

			if (type.equals("brands")){
				parameters.put("brand_id",id);
			}
			else {
				parameters.put("category_id",id);
			}

				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}
}
