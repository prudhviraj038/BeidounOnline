package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class HomeFragment extends Fragment {
	private ProgressInterface progressInterface;


	RecyclerView rv_one,rv_two;
	ArrayList<MenuItem> menuItems = new ArrayList<>();
	RelativeLayout vechile_tracking;

	//TextView insta_shopnow_tv;

	Sales_Adapter sales_adapter;
	Slider_Adapter slider_adapter;
	//SlidingImageAdapter slidingImageAdapter;

	RecyclerView slider_rv;
	TabLayout indicator_tab;

	ListView countries_lv;

	ArrayList<Home_data> home_data = new ArrayList<>();
	ArrayList<Slider_Data> home_data1 = new ArrayList<>();
	ArrayList<Home_data> home_data2 = new ArrayList<>();
	ArrayList countriesList = new ArrayList();

	MenuAdapter menuAdapter;

	LinearLayout countries_popup_ll;

	/*@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try {
			progressInterface = (ProgressInterface) context;
		} catch (ClassCastException e) {
			throw new ClassCastException(context.toString() + e.getMessage());
		}
	}*/


	public static HomeFragment newInstance(int someInt) {
		HomeFragment myFragment = new HomeFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		Log.e("homefragment","homefragment");

		rv_one = (RecyclerView) view.findViewById(R.id.rv_one);
		slider_rv = (RecyclerView) view.findViewById(R.id.slider_rv);
		rv_two = (RecyclerView)view.findViewById(R.id.rv_two);

		countries_lv = (ListView) view.findViewById(R.id.countries_lv);

		countries_popup_ll = (LinearLayout) view.findViewById(R.id.countries_popup_ll);
		countries_popup_ll.setVisibility(View.GONE);
/*

		insta_shopnow_tv = (TextView)view.findViewById(R.id.insta_shopnow_tv);
		insta_shopnow_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).insta_shop("3","Shop");
			}
		});
*/

		rv_one.setNestedScrollingEnabled(true);
		slider_rv.setNestedScrollingEnabled(true);
		//0rv_two.setNestedScrollingEnabled(true);

		ViewCompat.setNestedScrollingEnabled(rv_one, false);


		indicator_tab=(TabLayout)view.findViewById(R.id.indicator);


		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

		slider_rv.setLayoutManager(linearLayoutManager);




		home_data1.add(new Slider_Data(R.drawable.slider_img_one,"SELF - PORTRAIT","Check Firll Midi Dress","150","40","90"));
		home_data1.add(new Slider_Data(R.drawable.slider_img_two,"GIANVITO ROSSI","White Buckled Strap Sandals","90","39","120"));
		home_data1.add(new Slider_Data(R.drawable.slider_img_one,"SELF - PORTRAIT","Check Firll Midi Dress","150","40","90"));
		home_data1.add(new Slider_Data(R.drawable.slider_img_two,"GIANVITO ROSSI","White Buckled Strap Sandals","150","40","90"));
		home_data1.add(new Slider_Data(R.drawable.slider_img_one,"SELF - PORTRAIT","Check Firll Midi Dress","150","40","90"));
		home_data1.add(new Slider_Data(R.drawable.slider_img_two,"GIANVITO ROSSI","White Buckled Strap Sandals","150","40","90"));
		home_data1.add(new Slider_Data(R.drawable.slider_img_one,"SELF - PORTRAIT","Check Firll Midi Dress","150","40","90"));



		slider_adapter = new Slider_Adapter(getContext(),home_data1);
		slider_rv.setAdapter(slider_adapter);



		//indicator_tab.setupWithViewPager(slider_rv,true);



		rv_one.setLayoutManager(new GridLayoutManager(getContext(),2));

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


		sales_adapter = new Sales_Adapter(getContext(),home_data);
		rv_one.setAdapter(sales_adapter);






		/*menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem("Vechile","Traking",R.drawable.vehicletracking));
		menuItems.add(new MenuItem("Buy GPS","Traker",R.drawable.buygps));
		menuItems.add(new MenuItem("Referal","Scheme",R.drawable.referal));
		menuItems.add(new MenuItem("MY","Account",R.drawable.myaccount));

		homeAdapter = new HomeAdapter(getActivity(),menuItems);
		home_list.setAdapter(homeAdapter);*/



		return view;

	}

	public void setCountries_lv() {

		callCountriesList();
	}


    public void callCountriesList(){

		final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/countries.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONArray jsonArray =new JSONArray(response);
					for (int i = 0;i<jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);

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

				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

}
}
