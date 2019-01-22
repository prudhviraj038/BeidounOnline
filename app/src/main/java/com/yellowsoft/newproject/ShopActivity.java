package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

public class ShopActivity extends AppCompatActivity {

	TextView title, new_tv,low_tv,high_tv,noof_results_tv;
	TextView filter_tv,shortby_tv;
	RecyclerView shop_rv;

	ArrayList<Shop_Data> shopData= new ArrayList<Shop_Data>();
	ArrayList<Filter_Data> filter_data= new ArrayList<Filter_Data>();

	FilterAdapter filterAdapter;
	Shop_Adapter shop_adapter;

	LinearLayout filter,filter_top;
	LinearLayout  instagram_ll;
	ImageView grid_img, vertical_img;

	boolean grid=true;
	boolean vertical;
	boolean newSelected;
	boolean LtoH ;
	boolean HtoL ;

	String categories,brands;
	String brandid,categoryid;




	LinearLayout new_ll,high_ll,low_ll;
	ImageView checkoff_new,checkon_new,checkoff_high,checkon_high,checkoff_low,checkon_low,cashimg,walletimg,cardimg;



	EditText search_et;
	LinearLayout search_ll_search,search_popup,popup_sort_ll;


	TextView page_title;
	LinearLayout back_btn,menu_btn;
	ImageView back;

	ImageView search_img_title,close;

	int page = 0;

	LinearLayout filter_popup;
	RecyclerView filter_rv;

	LinearLayoutManager linearLayoutManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_shop);



		Log.e("shopfragment", "shopfragment");


		filterAdapter = new FilterAdapter(ShopActivity.this,filter_data);

		//filterpopup layout
		filter_popup = (LinearLayout)findViewById(R.id.filter_popup);

		//close img
		close = (ImageView)findViewById(R.id.close_img);




		popup_sort_ll = (LinearLayout)findViewById(R.id.popup_sort_ll);
		popup_sort_ll.setVisibility(View.GONE);
		popup_sort_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popup_sort_ll.setVisibility(View.GONE);
			}
		});


		filter_tv = (TextView)findViewById(R.id.filter_tv);

		shortby_tv = (TextView)findViewById(R.id.shortby_tv);
		shortby_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popup_sort_ll.setVisibility(View.VISIBLE);



               // popup_sort_ll.setVisibility(View.GONE);
			}
		});


		new_tv = (TextView)findViewById(R.id.new_tv);


		low_tv = (TextView)findViewById(R.id.low_tv);


		high_tv = (TextView)findViewById(R.id.high_tv);

		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;

		try {
			if (Session.getLanguage(ShopActivity.this).equals("0")){
				filter_tv.setText(jsonObjectEN.getString("Filter By"));
				shortby_tv.setText(jsonObjectEN.getString("Sort By Beidoun"));
				new_tv.setText(jsonObjectEN.getString("Newest"));
				low_tv.setText(jsonObjectEN.getString("Price Low"));
				high_tv.setText(jsonObjectEN.getString("Price HIgh"));

			}
			else {
				filter_tv.setText(jsonObjectAR.getString("Filter By"));
				shortby_tv.setText(jsonObjectAR.getString("Sort By Beidoun"));
				new_tv.setText(jsonObjectAR.getString("Newest"));
				low_tv.setText(jsonObjectAR.getString("Price Low"));
				high_tv.setText(jsonObjectAR.getString("Price High"));
			}
		}catch (JSONException j){
			j.printStackTrace();
		}



		search_et = (EditText)findViewById(R.id.et_search);
		search_ll_search = (LinearLayout) findViewById(R.id.search_ll_search);
		search_popup = (LinearLayout)findViewById(R.id.search_popup);


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
                if (search_et.getText().toString().equals("")){
                	shopData.clear();
				}

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



		//CallProductdetails();

		title = (TextView)findViewById(R.id.tilte_shop_frag);
		noof_results_tv = (TextView)findViewById(R.id.noof_results_tv);

		grid_img = (ImageView)findViewById(R.id.grid_img);
		vertical_img = (ImageView)findViewById(R.id.vertical_img);


		instagram_ll = (LinearLayout)findViewById(R.id.instagram_ll);
		//filter = (LinearLayout)view.findViewById(R.id.filter_ll);
		filter_top = (LinearLayout)findViewById(R.id.filter_ll_top);

		grid_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));
		vertical_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));





	linearLayoutManager	 = new LinearLayoutManager(ShopActivity.this);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		final GridLayoutManager gridLayoutManager = new GridLayoutManager(ShopActivity.this,2);










		shop_rv = (RecyclerView)findViewById(R.id.rv_shop);


		shop_adapter = new Shop_Adapter(ShopActivity.this,shopData,1);





		shop_rv.setAdapter(shop_adapter);

		shop_rv.setLayoutManager(gridLayoutManager);


		filter_rv = (RecyclerView) findViewById(R.id.filter_rv);

		filter_rv.setLayoutManager(linearLayoutManager);
		filter_rv.setAdapter(filterAdapter);

		filterAdapter.notifyDataSetChanged();


		if (grid==true){
			shop_rv.setLayoutManager(gridLayoutManager);
			grid_img.setColorFilter(getResources().getColor(R.color.colorBlack));
			vertical_img.setColorFilter(getResources().getColor(R.color.colorlightGrey));
		}
		else {
			shop_rv.setLayoutManager(linearLayoutManager);
		}







		new_ll = (LinearLayout)findViewById(R.id.new_ll);
		high_ll = (LinearLayout)findViewById(R.id.high_ll);
		low_ll = (LinearLayout)findViewById(R.id.low_ll);




		checkoff_new = (ImageView)findViewById(R.id.checkoff_new);
		checkon_new = (ImageView)findViewById(R.id.checkon_new);

		checkoff_high = (ImageView)findViewById(R.id.checkoff_high);
		checkon_high = (ImageView)findViewById(R.id.checkon_high);

		checkoff_low = (ImageView)findViewById(R.id.checkoff_low);
		checkon_low = (ImageView)findViewById(R.id.checkon_low);





		//getting category id from category brandsadapter and category fragment

		categories = getIntent().getStringExtra("Category");

		//getting brand id from category brandsadapter and brands fragment

		brands = getIntent().getStringExtra("brands");





		if (categories!=null){
			callProducts("category",categories);
			Log.e("tag",String.valueOf(categories));

		}
		else if (brands!=null){


			callProducts("brands",brands);
			Log.e("tag",String.valueOf(brands));
		}





		//Sorting


		new_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				/*resetColors();
				checkoff(checkoff_high,checkoff_low);
				changecolor(cod_tv);
				check(checkoff_new, checkon_new,checkon_high,checkon_low);*/

				/*LtoH = false;
				HtoL = false;
				newSelected = true;
				*/
				shopData.clear();
				shop_adapter.notifyDataSetChanged();
				popup_sort_ll.setVisibility(View.GONE);
				callProducts("new",categoryid);
				Log.e("New","newselected");





			}
		});


		low_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*resetColors();
				checkoff(checkoff_new,checkoff_high);
				check(checkoff_low,checkon_low,checkon_new,checkon_high);

				changecolor(wallet_tv);*/
				/*LtoH = true;
				HtoL = false;
				newSelected = false;
*/
				shopData.clear();
				shop_adapter.notifyDataSetChanged();
				callProducts("LtoH",categoryid);

				popup_sort_ll.setVisibility(View.GONE);

				Log.e("low","lowselected");
			}
		});


		high_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*resetColors();
				check(checkoff_high,checkon_high,checkon_new,checkon_low);
				checkoff(checkoff_new,checkoff_low);
				changecolor(high_tv);*/
				/*LtoH = false;
				HtoL = true;
				newSelected = false;

			*/
				shopData.clear();
				shop_adapter.notifyDataSetChanged();
				callProducts("HtoL",categoryid);
				popup_sort_ll.setVisibility(View.GONE);
				Log.e("high","highselected");

			}
		});

	/*	if (newSelected){
			resetColors();
			checkoff(checkoff_high,checkoff_low);
			changecolor(new_tv);
			check(checkoff_new, checkon_new,checkon_high,checkon_low);

		}
		else if (LtoH){
			resetColors();
			checkoff(checkoff_new,checkoff_high);
			check(checkoff_low,checkon_low,checkon_new,checkon_high);

			changecolor(low_tv);
		}
		else if (HtoL){
			resetColors();
			check(checkoff_high,checkon_high,checkon_new,checkon_low);
			checkoff(checkoff_new,checkoff_low);
			changecolor(high_tv);
		}
*/



	filter_tv.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			filter_popup.setVisibility(View.VISIBLE);
			callFilter();
		}
	});

	close.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			filter_popup.setVisibility(View.GONE);
		}
	});





    //setParamentersBrands(id,"brands");



		shopData.clear();

		Toolbar toolbar = (Toolbar) findViewById(R.id.shop_toolbar);
		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();




		///shop_rv.setNestedScrollingEnabled(false);
/*

		shop_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);

				Log.e("recyclerviewScrolled","scrolled");

                if (!recyclerView.canScrollVertically(1)) {


                	page = page + 1;

                    Log.e("requestPagenation",""+page);

                    callProducts("page",String.valueOf(page));


                }


                if (!recyclerView.canScrollVertically(-1)){



                }

			}

		});
*/



	}




	private void setupActionBar() {
//set action bar
		getSupportActionBar().setHomeButtonEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT);
		LayoutInflater inflater = getLayoutInflater();
		View v = inflater.inflate(R.layout.action_bar_login,null);

		page_title = (TextView) v.findViewById(R.id.page_title);
		back_btn = (LinearLayout)v.findViewById(R.id.btn_back_container);

		back = (ImageView)v.findViewById(R.id.btn_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		search_img_title = (ImageView)v.findViewById(R.id.search_img_title);
		search_img_title.setVisibility(View.VISIBLE);

		search_img_title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				search_popup.setVisibility(View.VISIBLE);
			}
		});


		menu_btn = (LinearLayout) v.findViewById(R.id.btn_menu_container);

		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}
	private void setupHeader(){

		page_title.setText(""+getIntent().getStringExtra("title"));
		//btn_edit.setVisibility(View.VISIBLE);
		//btn_edit.setText("Search");
		//page_title.setText("Home");
	}


	/*private void check(ImageView imageView,ImageView imageView2,ImageView imageView3,ImageView imageView4){
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
		new_tv.setTextColor(getResources().getColor(R.color.colorlightGrey));

		low_tv.setTextColor(getResources().getColor(R.color.colorlightGrey));

		high_tv.setTextColor(getResources().getColor(R.color.colorlightGrey));

	}
*/



	/*public void setParamentersBrands(String brandId,String titles){

        if (brandId.equals("0")){
            instagram_ll.setVisibility(View.GONE);
        }

        title.setText(""+titles);

		this.brandid = brandId ;
		callProducts("brands",brandId);
		search_popup.setVisibility(View.GONE);

	}*/

	/*public void setParamentersCategories(String categories,String titles){

        if (categories.equals("0")){
            instagram_ll.setVisibility(View.GONE);
        }
		title.setText(""+titles);

		this.categoryid = categories ;
		callProducts("category",categories);

		search_popup.setVisibility(View.GONE);

	}*/

	public void search(String keywords){

		search_popup.setVisibility(View.VISIBLE);

		callProducts("search",search_et.getText().toString());

	}


	public void callProducts(final String type, final String id) {

		final ProgressDialog progressDialog = new ProgressDialog(ShopActivity.this);
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

					categoryid = jsonArray.getJSONObject(0).getJSONObject("category").getString("id");

					Log.e("categoryid",""+categoryid);

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

				parameters.put("category_id",id);
				parameters.put("sorting","PLOW");
			}
			else if (type.equals("HtoL")){
				parameters.put("category_id",id);
				parameters.put("sorting","PHIG");
			}
			else if (type.equals("new")){
				parameters.put("category_id",id);
				parameters.put("sorting"," ");
			}
			else if (type.equals("page")){
				parameters.put("page",id);
			}

				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}





	//filter service
	public void callFilter() {

		final ProgressDialog progressDialog = new ProgressDialog(ShopActivity.this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL + "api/brands.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resFilter", response);
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				try {

					JSONArray jsonArray = new JSONArray(response);




					Log.e("categoryid",""+categoryid);

					//noof_results_tv.setText(""+jsonArray.length()+" ");



					for (int i=0;i<=jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);

						Filter_Data temp = new Filter_Data(jsonObject);
						filter_data.add(temp);
					}

					filter_rv.setLayoutManager(linearLayoutManager);



					filter_rv.setAdapter(filterAdapter);

					filterAdapter.notifyDataSetChanged();





				} catch (JSONException e) {
					e.printStackTrace();
				}
				filterAdapter.notifyDataSetChanged();

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

					parameters.put("category_id",categoryid);



				//	parameters.put("password",password.getText().toString());
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
//		slidingPageAdapter.notifyDataSetChanged();
	}
}
