package com.yellowsoft.newproject;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubCategoriesActivity extends AppCompatActivity {


FrameLayout frameLayout;
TextView page_title;
LinearLayout back_btn,menu_btn;
ImageView back;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subcategories);


		frameLayout = (FrameLayout)findViewById(R.id.subcategories_framelayout);

		if (savedInstanceState==null){

			Bundle bundle = new Bundle();
			CatergoriesData subCategoriesData = (CatergoriesData) getIntent().getSerializableExtra("subcategories");

			bundle.putSerializable("subcategories",subCategoriesData);
			Fragment fragment = new CategoriesFragment();

			fragment.setArguments(bundle);

			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

			ft.add(R.id.subcategories_framelayout,fragment).commit();

			//Shop_Data shop_data = (Shop_Data)getIntent().getSerializableExtra("productDetails");


			//((ProductFragment) fragment).productDetails(shop_data);

		}

		Toolbar toolbar = (Toolbar) findViewById(R.id.subcategories_toolbar);
		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();

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
	/*public void CallProductdetails(){

		final ProgressDialog progressDialog = new ProgressDialog(ProductActivity.this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/products.php";

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

					String producttitle=jsonArray.getJSONObject(0).getString("title");
					Log.e("pagetitletitle",""+producttitle);
					title.setText(producttitle);


					String price=jsonArray.getJSONObject(0).getString("price");
					Log.e("price",""+price);
					discount_price.setText(price);
					Session.setPrice(ProductActivity.this,price);



					String discount=jsonArray.getJSONObject(0).getString("discount");
					Log.e("discount",""+discount);
					String givenprice = discount+price;
					int i = Integer.parseInt(discount)+Integer.parseInt(price);
					originalprice_tv.setText(""+i);

					String quantitys=jsonArray.getJSONObject(0).getString("quantity");
					Log.e("quantity",""+quantitys);
					quantity.setText(quantitys);


					String description=jsonArray.getJSONObject(0).getString("description");
					Log.e("description",""+description);
					description_tv.setText(Html.fromHtml("<p>"+description+"</p>"));

					String images=jsonArray.getJSONObject(0).getString("images");
					Log.e("images",""+images);

					JSONArray jsonArray1 = new JSONArray(images);
					Log.e("jsonarray1",""+jsonArray1);
					Log.e("jsonarray1length",""+jsonArray1.length());
					//	Log.e("imagessssss",""+jsonArray1.getJSONObject(0).getString("image"));

					if (jsonArray1.length()>1){
						Log.e("length","length");
						for (int j=0;j<=jsonArray1.length();j++){
							//slidingImage_data.add(new SlidingImage_Data(jsonArray.getJSONObject(j).getString("image")));
							String s = jsonArray1.getString(j);
							Log.e("s",""+s);
							slidingImage_data.add(new SlidingImage_Data(s));
							Log.e("imagessssss",""+jsonArray1.getString(j));

						}

					}


				} catch (JSONException e) {
					e.printStackTrace();
				}
				slidingImageAdapter.notifyDataSetChanged();
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
	}*/
}
