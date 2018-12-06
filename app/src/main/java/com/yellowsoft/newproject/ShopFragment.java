package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

	TextView title, cod_tv,wallet_tv,card_tv,noof_results_tv,shortby_tv;
	RecyclerView shop_rv;

	ArrayList<Shop_Data> shopData= new ArrayList<Shop_Data>();
	Shop_Adapter shop_adapter;

	LinearLayout filter,filter_top;
	LinearLayout  instagram_ll;
	ImageView grid_img, vertical_img;

	boolean grid=true;
	boolean vertical;

	String brandid,categoryid;




	LinearLayout new_ll,high_ll,low_ll;
	ImageView checkoff_new,checkon_new,checkoff_high,checkon_high,checkoff_low,checkon_low,cashimg,walletimg,cardimg;



	EditText search_et;
	LinearLayout search_ll_search,search_popup,popup_sort_ll;


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


		popup_sort_ll = (LinearLayout)view.findViewById(R.id.popup_sort_ll);
		popup_sort_ll.setVisibility(View.GONE);
		popup_sort_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popup_sort_ll.setVisibility(View.GONE);
			}
		});


		shortby_tv = (TextView)view.findViewById(R.id.shortby_tv);
		shortby_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popup_sort_ll.setVisibility(View.VISIBLE);
			}
		});

		search_et = (EditText)view.findViewById(R.id.et_search);
		search_ll_search = (LinearLayout)view. findViewById(R.id.search_ll_search);
		search_popup = (LinearLayout)view.findViewById(R.id.search_popup);


		search_ll_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				String keywords = search_et.getText().toString();
				Log.e("keyword",search_et.getText().toString());

				search(search_et.getText().toString());


			}
		});

		search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                search(search_et.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



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




		new_ll = (LinearLayout)view.findViewById(R.id.new_ll);
		high_ll = (LinearLayout)view.findViewById(R.id.high_ll);
		low_ll = (LinearLayout)view.findViewById(R.id.low_ll);


		cod_tv = (TextView)view.findViewById(R.id.new_tv);


		wallet_tv = (TextView)view.findViewById(R.id.low_tv);


		card_tv = (TextView)view.findViewById(R.id.high_tv);

		checkoff_new = (ImageView)view.findViewById(R.id.checkoff_new);
		checkoff_new = (ImageView)view.findViewById(R.id.checkon_new);

		checkoff_high = (ImageView)view.findViewById(R.id.checkoff_high);
		checkon_high = (ImageView)view.findViewById(R.id.checkon_high);

		checkoff_low = (ImageView)view.findViewById(R.id.checkoff_low);
		checkon_low = (ImageView)view.findViewById(R.id.checkon_low);


		//Sorting


		new_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				resetColors();
				checkoff(checkoff_high,checkoff_low);
				changecolor(cod_tv);
				check(checkoff_new, checkon_new,checkon_high,checkon_low);


				popup_sort_ll.setVisibility(View.GONE);

				callProducts("new","");



			}
		});


		low_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetColors();
				checkoff(checkoff_new,checkoff_high);
				check(checkoff_low,checkon_low,checkon_new,checkon_high);

				changecolor(wallet_tv);

				callProducts("lowtohigh","PLOW");
				popup_sort_ll.setVisibility(View.GONE);
			}
		});


		high_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetColors();
				check(checkoff_high,checkon_high,checkon_new,checkon_low);
				checkoff(checkoff_new,checkoff_low);
				changecolor(card_tv);
				callProducts("hightoLow","Phigh");
				popup_sort_ll.setVisibility(View.GONE);

			}
		});


		shopData.clear();

		return view;
	}





	private void check(ImageView imageView,ImageView imageView2,ImageView imageView3,ImageView imageView4){
		imageView.setVisibility(View.GONE);
		imageView2.setVisibility(View.VISIBLE);
		imageView3.setVisibility(View.GONE);
		imageView4.setVisibility(View.GONE);
	}

	private void checkoff(ImageView imageView,ImageView imageView2){
		imageView.setVisibility(View.VISIBLE);
		imageView2.setVisibility(View.VISIBLE);

	}

	private void changecolor(TextView textView){
		textView.setTextColor(getResources().getColor(R.color.colorBlack));

	}

	private void resetColors(){
		cod_tv.setTextColor(getResources().getColor(R.color.colorlightGrey));

		wallet_tv.setTextColor(getResources().getColor(R.color.colorlightGrey));

		card_tv.setTextColor(getResources().getColor(R.color.colorlightGrey));

	}




	public void setParamentersBrands(String brandId,String titles){

        if (brandId.equals("0")){
            instagram_ll.setVisibility(View.GONE);
        }

        title.setText(""+titles);

		this.brandid = brandId ;
		callProducts("brands",brandId);
		search_popup.setVisibility(View.GONE);

	}

	public void setParamentersCategories(String categories,String titles){

        if (categories.equals("0")){
            instagram_ll.setVisibility(View.GONE);
        }
		title.setText(""+titles);

		this.categoryid = categories ;
		callProducts("category",categories);

		search_popup.setVisibility(View.GONE);

	}

	public void search(String keywords){

		search_popup.setVisibility(View.VISIBLE);

		callProducts("search",search_et.getText().toString());

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
			else if (type.equals("category")){
				parameters.put("category_id",id);
			}
			else if (type.equals("search")){
				parameters.put("search",id);
			}
			else if (type.equals("LtoH")){

				parameters.put("sorting","PLOW");
			}
			else if (type.equals("HtoL")){
				parameters.put("sorting","PHIG");
			}
			else if (type.equals("new")){
				parameters.put("sorting","");
			}

				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}
}
