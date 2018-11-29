package com.yellowsoft.newproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
	private ProgressInterface progressInterface;


	RecyclerView rv_one,rv_two;
	ArrayList<MenuItem> menuItems = new ArrayList<>();
	RelativeLayout vechile_tracking;

	TextView insta_shopnow_tv;

	Sales_Adapter sales_adapter;
	Slider_Adapter slider_adapter;
	//SlidingImageAdapter slidingImageAdapter;

	RecyclerView slider_rv;
	TabLayout indicator_tab;


	ArrayList<Home_data> home_data = new ArrayList<>();
	ArrayList<Slider_Data> home_data1 = new ArrayList<>();
	ArrayList<Home_data> home_data2 = new ArrayList<>();


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

		insta_shopnow_tv = (TextView)view.findViewById(R.id.insta_shopnow_tv);
		insta_shopnow_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).insta_shop("0");
			}
		});

		rv_one.setNestedScrollingEnabled(true);
		slider_rv.setNestedScrollingEnabled(true);
		rv_two.setNestedScrollingEnabled(true);

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

		sales_adapter = new Sales_Adapter(getContext(),home_data);
		rv_one.setAdapter(sales_adapter);


		rv_two.setLayoutManager(new GridLayoutManager(getContext(),2));

		home_data2.add(new Home_data(R.drawable.sales));

		home_data2.add(new Home_data(R.drawable.sales));
		home_data2.add(new Home_data(R.drawable.sales));
		home_data2.add(new Home_data(R.drawable.sales));

		sales_adapter = new Sales_Adapter(getContext(),home_data2);
		rv_two.setAdapter(sales_adapter);




		/*menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem("Vechile","Traking",R.drawable.vehicletracking));
		menuItems.add(new MenuItem("Buy GPS","Traker",R.drawable.buygps));
		menuItems.add(new MenuItem("Referal","Scheme",R.drawable.referal));
		menuItems.add(new MenuItem("MY","Account",R.drawable.myaccount));

		homeAdapter = new HomeAdapter(getActivity(),menuItems);
		home_list.setAdapter(homeAdapter);*/



		return view;

	}


}
