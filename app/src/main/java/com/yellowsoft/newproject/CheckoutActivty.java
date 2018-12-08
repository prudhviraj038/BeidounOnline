package com.yellowsoft.newproject;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CheckoutActivty extends AppCompatActivity {

	TextView quantity,page_title,total_tv,price_cart_tv,subtotal_tv,discount_tv;
	TextView shippingcharge_tv,ordertotal_tv_cart;
	TextView country_code_cartsub,country_code__cartdiscount,country_code_cart_total;

	LinearLayout empty_cart_ll,apply_ll_btn;
	LinearLayout menu_btn,back_btn,coupencode_ll;
	LinearLayout cart_items_ll,payconfirm_ll_checkout;

	LinearLayout orders_ll_toolbar;
	ImageView btn_like,btn_back;

	Checkout_Adapter checkout_adapter;
	ArrayList<Cart_Data> cart_data = new ArrayList<>();


	Integer cartquantity;
	ProductsData product;
	ImageView back,countries_img;

	EditText coupon_code;
	int subtotal;
	int discount_int = 0;
	String coupon_code_str = "";

	RecyclerView cart_rv;

	float total;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);







		/*coupencode_ll = (LinearLayout)view. findViewById(R.id.coupencode_ll);
		total_tv = (TextView)view. findViewById(R.id.total_tv);

		discount_tv = (TextView)view.findViewById(R.id.discount_tv);*/
		//quantity.setText(cartquantity.toString());

		subtotal_tv = (TextView)findViewById(R.id.subtotal_tv_checkout);
		ordertotal_tv_cart = (TextView)findViewById(R.id.total_tv_checkout);

		country_code_cartsub = (TextView)findViewById(R.id.countrycode_checkout);
		country_code__cartdiscount = (TextView)findViewById(R.id.countrycode_dis_checkout);
		country_code_cart_total = (TextView)findViewById(R.id.countrycode_total_checkout);

		country_code_cart_total.setText(Session.getCurrencyCode(CheckoutActivty.this));
		country_code__cartdiscount.setText(Session.getCurrencyCode(CheckoutActivty.this));
		country_code_cartsub.setText(Session.getCurrencyCode(CheckoutActivty.this));


		/*empty_cart_ll = (LinearLayout)view.findViewById(R.id.empty_cart_ll);
		cart_items_ll = (LinearLayout)view.findViewById(R.id.cart_items_ll);*/
		payconfirm_ll_checkout = (LinearLayout)findViewById(R.id.payconfirm_ll_checkout);

		payconfirm_ll_checkout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CheckoutActivty.this,"Payment done",Toast.LENGTH_SHORT).show();
			}
		});



		cart_rv = (RecyclerView)findViewById(R.id.myorders_rv_checkout);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivty.this);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		cart_rv.setLayoutManager(linearLayoutManager);



	/*	cart_data.add(new Cart_Data("456","https:\\/\\/beidounonline.com\\/uploads\\/collections\\/51517215687.jpg","Gold Watch","This is a gold watch","ddd"));
		cart_data.add(new Cart_Data("456","https:\\/\\/beidounonline.com\\/uploads\\/collections\\/51517215687.jpg","Gold Watch","This is a gold watch","ddd"));
		cart_data.add(new Cart_Data("456","https:\\/\\/beidounonline.com\\/uploads\\/collections\\/51517215687.jpg","Gold Watch","This is a gold watch","ddd"));
		cart_data.add(new Cart_Data("456","https:\\/\\/beidounonline.com\\/uploads\\/collections\\/51517215687.jpg","Gold Watch","This is a gold watch","ddd"));
		cart_data.add(new Cart_Data("456","https:\\/\\/beidounonline.com\\/uploads\\/collections\\/51517215687.jpg","Gold Watch","This is a gold watch","ddd"));

*/


		ArrayList<Object> temp = (ArrayList<Object>) ApplicationController.getInstance().cartProducts;

		total=0;
		cart_data.clear();

		for(int i=0;i<temp.size();i++) {

			Cart_Data temp_obj = (Cart_Data) temp.get(i);
			cart_data.add(temp_obj);

			total = total + (temp_obj.cartquantity * Float.parseFloat(temp_obj.shop_data.price));

		}

/*

		if(temp.size()==0){
			empty_cart_ll.setVisibility(View.VISIBLE);
			cart_items_ll.setVisibility(View.GONE);
		}
		else {
			empty_cart_ll.setVisibility(View.GONE);
			cart_items_ll.setVisibility(View.VISIBLE);
		}
*/



		subtotal_tv.setText(ApplicationController.getInstance().formatNumber(total));
		Log.e("total",""+ApplicationController.getInstance().formatNumber(total));


		float floatTemp = total * Float.parseFloat(Session.getCurrencyRate(CheckoutActivty.this));

		subtotal_tv.setText(String.valueOf(floatTemp));


		ordertotal_tv_cart.setText(ApplicationController.getInstance().formatNumber(total));


        ordertotal_tv_cart.setText(String.valueOf(floatTemp));


		checkout_adapter = new Checkout_Adapter(CheckoutActivty.this,cart_data);

		cart_rv.setAdapter(checkout_adapter);





/*

		coupon_code = (EditText)view.findViewById(R.id.ed_couponcode);
		coupon_code.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
		coupon_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE){
					//CallCoupenService(coupon_code.getText().toString(),total_tv.getText().toString());

				}
				return false;
			}
		});

*/

		/*apply_ll_btn = (LinearLayout)view.findViewById(R.id.apply_ll_btn);
		apply_ll_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//CallCoupenService(coupon_code.getText().toString(),total_tv.getText().toString());
			}
		});




		discount_tv.setText("0");*/



        //    getting shipping charges


		Toolbar toolbar = (Toolbar) findViewById(R.id.checkout_toolbar);
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
		View v = inflater.inflate(R.layout.action_bar_title,null);











		orders_ll_toolbar = (LinearLayout)v.findViewById(R.id.orders_ll_toolbar);
		orders_ll_toolbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckoutActivty.this,MyOrdersActivity.class);
				startActivity(intent);
			}
		});







		btn_like = (ImageView)v.findViewById(R.id.btn_like);
		btn_like.setVisibility(View.GONE);

		btn_back = (ImageView)v.findViewById(R.id.btn_back_title);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});




		countries_img = (ImageView)v.findViewById(R.id.countries_img);
		countries_img.setVisibility(View.GONE);













		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}




	private void setupHeader(){

	}


	//
	/*public void CallCoupenService(final String coupen, final String total){

		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/coupon-check.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resCartActivity",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONObject jsonObject=new JSONObject(response);
					String reply=jsonObject.getString("status");
					Log.e("status",""+reply);
					if(reply.equals("Success")) {

						Snackbar.make(discount_tv,"Coupon code applied successfully",Snackbar.LENGTH_SHORT).show();
						String discount = jsonObject.getString("discount_value");
						Log.e("discount",""+discount);

						discount_tv.setText(""+discount);

						Log.e("totalss",""+totalss);

						discount_int = Integer.parseInt(discount);
						totals = addShippingCharges-Integer.parseInt(discount);

						Log.e("subtotal",""+subtotal);

						total_tv.setText(""+totals);

						Log.e("total",""+totals);

						Session.setTotalPrice(pay.this,""+totals);


						coupon_code_str = coupon_code.getText().toString();
						Log.e("coupen_code",coupon_code_str);

					}
					else {
						Snackbar.make(discount_tv,"Invalid Coupon Code",Snackbar.LENGTH_SHORT).show();
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
						Toast.makeText(hs.this,"Internet error",Toast.LENGTH_SHORT).show();
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("coupon",coupen);
				parameters.put("cart_total",total);
				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

	//referal amount check
	public void callReferalMoney(){
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/scheme_amount_check.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("referalmoney",response);

				if(progressDialog!=null) {
					progressDialog.dismiss();
					//scheme_amount
				}
				try {
					JSONObject jsonObject=new JSONObject(response);
					String reply=jsonObject.getString("scheme_amount");
					Log.e("status",""+reply);

					if (reply.equals("0")){
						discount_tv_payment.setText("use my scheme amount");
						usescheme_money_ll.setVisibility(View.GONE);
						scheme_amt="0";

					}
					else {
						discount_tv_payment.setText("use my scheme amount");

						//discount_tv.setText(reply);
						//discount_int = Integer.parseInt(reply);
						scheme_amt = String.valueOf(reply);


						*//*if (schemeAmtUsed==true){


						}*//*
					*//*	else {
							discount_tv.setText("0");
							discount_int = 0;
						}*//*

						//subtotal_tv.setText(reply);
					}


					referalmoney_payment.setText(reply);




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
						Toast.makeText(sd.this,"Internet error",Toast.LENGTH_SHORT).show();
						// /Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("member_id",Session.getUserid(sdf.this));
				Log.e("memberid",Session.getUserid(CartsdfActivity.this));



				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}*/
}
