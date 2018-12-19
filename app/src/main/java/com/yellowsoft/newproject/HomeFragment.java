package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
	private ProgressInterface progressInterface;


	RecyclerView rv_one,rv_two;
	ArrayList<MenuItem> menuItems = new ArrayList<>();
	RelativeLayout vechile_tracking;

	ImageView main_img_homeone;

	//TextView insta_shopnow_tv;

	Sales_Adapter sales_adapter;
	Slider_Adapter slider_adapter;
	SlidingImageAdapter slidingImageAdapter;
	//SlidingImageAdapter slidingImageAdapter;

	RecyclerView slider_rv;
	TabLayout indicator_tab;


	ArrayList<Home_data> home_data = new ArrayList<>();
	ArrayList<SlidingImage_Data> slidingImage_data = new ArrayList<>();
	ArrayList<Shop_Data> shopData = new ArrayList<>();

	String id,type;

	ProgressBar homefrag_progress;

	private static int currentPage = 0;

	ViewPager slider_viewpager;

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

		slider_viewpager = (ViewPager)view.findViewById(R.id.slider_viewpager);


		homefrag_progress = (ProgressBar)view.findViewById(R.id.homefrag_progress);
		homefrag_progress.setVisibility(View.GONE);

		main_img_homeone = (ImageView) view.findViewById(R.id.main_img_homeone);
	/*	main_img_homeone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//((HomeActivity)getActivity()).insta_shop(id,"shop");
				Intent intent = new Intent(getContext(),ShopActivity.class);
				intent.putExtra(type,id);
				startActivity(intent);
			}
		});*/


		rv_one = (RecyclerView) view.findViewById(R.id.rv_one);
		slider_rv = (RecyclerView) view.findViewById(R.id.slider_rv);
		rv_two = (RecyclerView)view.findViewById(R.id.rv_two);


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





		shopData.clear();
		slider_adapter = new Slider_Adapter(getContext(),shopData);
		slider_rv.setAdapter(slider_adapter);



		//indicator_tab.setupWithViewPager(slider_rv,true);



		rv_one.setLayoutManager(new GridLayoutManager(getContext(),2));




		home_data.clear();
		sales_adapter = new Sales_Adapter(getContext(),home_data);
		rv_one.setAdapter(sales_adapter);


		/*final Handler handler = new Handler();
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (slider_viewpager.getCurrentItem()<=slidingImage_data.size()){
					slider_viewpager.setCurrentItem(slider_viewpager.getCurrentItem()+1);
				}
				else {
					slider_viewpager.setCurrentItem(0);
				}
				//slider_viewpager.setCurrentItem(currentPage++,true);

				handler.postDelayed(this,5000);
			}
		};
		handler.postDelayed(runnable,300);*/


		/*slider_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				currentPage=position;

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// state equals to 0 means the scroll action is stop
				if(state == 0) {

					if(currentPage == 0)
						slider_viewpager.setCurrentItem(slidingImage_data.size()-2,false);

					if(currentPage == slidingImage_data.size()-1)
						slider_viewpager.setCurrentItem(1,false);
				}
			}
		});
*/




        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 4000);
		getHomeApi();

		return view;

	}







	//call homepage api

	public void getHomeApi()
	{

		/*final ProgressDialog progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);*/
		homefrag_progress.setVisibility(View.VISIBLE);
		String URL = Session.BASE_URL+"api/home.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);

				homefrag_progress.setVisibility(View.GONE);

				try {

					/*if(progressDialog!=null&&progressDialog.isShowing()) {
						progressDialog.dismiss();
					}*/


					JSONObject jsonObject =new JSONObject(response);


					JSONArray jsonArray = jsonObject.getJSONArray("banners");


					if (jsonArray.length()>1) {

						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject1 = jsonArray.getJSONObject(i);

							//Picasso.get().load(jsonObject1.getString("image")).into(main_img_homeone);

						//	id = jsonObject1.getString("type_id");

						//	type = jsonObject1.getString("type");

							SlidingImage_Data temp = new SlidingImage_Data(jsonObject1);
							slidingImage_data.add(temp);

						}

						slidingImageAdapter = new SlidingImageAdapter(getActivity(),slidingImage_data);
						slider_viewpager.setAdapter(slidingImageAdapter);
						slider_adapter.notifyDataSetChanged();
					}









					JSONArray jsonArray2 = jsonObject.getJSONArray("latest_products");
					Log.e("latestProducts",""+jsonObject.getJSONArray("latest_products"));

					for (int i=0;i<jsonArray2.length();i++) {
						JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
						Shop_Data temp = new Shop_Data(jsonObject2);
						shopData.add(temp);
					}
					slider_adapter.notifyDataSetChanged();






						//images
					JSONArray jsonArray1 = jsonObject.getJSONArray("images");


					for (int i=0;i<jsonArray1.length();i++) {

						JSONObject jsonObject3 = jsonArray1.getJSONObject(i);
						Log.e("imagesjson",""+jsonObject3.getString("image"));
						Home_data home_temp = new Home_data(jsonObject3);
						home_data.add(home_temp);

					}

					sales_adapter.notifyDataSetChanged();








				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getActivity()," Please try after some time.",Toast.LENGTH_LONG).show();
					homefrag_progress.setVisibility(View.GONE);
				}
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						/*if(progressDialog!=null)
							progressDialog.dismiss();*/
						homefrag_progress.setVisibility(View.GONE);

						Toast.makeText(getActivity()," Please try after some time.",Toast.LENGTH_LONG).show();

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





    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slider_viewpager.getCurrentItem() < slidingImage_data.size() - 1) {
                        slider_viewpager.setCurrentItem(slider_viewpager.getCurrentItem() + 1);
                    } else {
                        slider_viewpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
