package com.yellowsoft.newproject;


import android.app.ProgressDialog;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CheckoutActivty extends AppCompatActivity {

	TextView quantity,page_title,total_tv,price_cart_tv,subtotal_tv,discount_tv;
	TextView shippingcharge_tv,ordertotal_tv_checkout;
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

	LinearLayout visa_ll,knet_ll,cod_ll;


	RecyclerView cart_rv;

	float total;

	boolean collect_payment;

	ImageView checkoff_cod_img,checkon_code_img,checkoff_knet_img,checkon_knet_img,checkoff_visa_img,checkon_visa_img;


	AddressChechout_Data addressChechout_data;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);



		checkoff_cod_img = (ImageView)findViewById(R.id.checkoff_cod_img);
		checkon_code_img = (ImageView)findViewById(R.id.checked_cod_img);

		checkoff_knet_img = (ImageView)findViewById(R.id.checkoff_knet_img);
		checkon_knet_img = (ImageView)findViewById(R.id.checked_knet_img);

		checkoff_visa_img = (ImageView)findViewById(R.id.checkoff_visa_img);
		checkon_visa_img = (ImageView)findViewById(R.id.checked_visa_img);


		visa_ll = (LinearLayout)findViewById(R.id.visa_ll);
		knet_ll = (LinearLayout)findViewById(R.id.knet_ll);
		cod_ll = (LinearLayout)findViewById(R.id.cod_ll);






		visa_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		cod_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				collect_payment = true;

				Log.e("addressdata",String.valueOf(getIntent().getStringExtra("addressId")));
				//callPlaceOrderService();
			}
		});



		knet_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});






		/*coupencode_ll = (LinearLayout)view. findViewById(R.id.coupencode_ll);
		total_tv = (TextView)view. findViewById(R.id.total_tv);

		discount_tv = (TextView)view.findViewById(R.id.discount_tv);*/
		//quantity.setText(cartquantity.toString());

		subtotal_tv = (TextView)findViewById(R.id.subtotal_tv_checkout);
		ordertotal_tv_checkout = (TextView)findViewById(R.id.total_tv_checkout);

		country_code_cartsub = (TextView)findViewById(R.id.countrycode_checkout);
		country_code__cartdiscount = (TextView)findViewById(R.id.countrycode_dis_checkout);
		country_code_cart_total = (TextView)findViewById(R.id.countrycode_total_checkout);

		country_code_cart_total.setText(Session.getCurrencyCode(CheckoutActivty.this));
		country_code__cartdiscount.setText(Session.getCurrencyCode(CheckoutActivty.this));
		country_code_cartsub.setText(Session.getCurrencyCode(CheckoutActivty.this));



		/*empty_cart_ll = (LinearLayout)view.findViewById(R.id.empty_cart_ll);
		cart_items_ll = (LinearLayout)view.findViewById(R.id.cart_items_ll);*/


		addressChechout_data = (AddressChechout_Data)getIntent().getSerializableExtra("address");
		Log.e("addressData",addressChechout_data.email);


		payconfirm_ll_checkout = (LinearLayout)findViewById(R.id.payconfirm_ll_checkout);

		payconfirm_ll_checkout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(CheckoutActivty.this,"Payment done",Toast.LENGTH_SHORT).show();

				callPlaceOrderService();



			}
		});



		cart_rv = (RecyclerView)findViewById(R.id.myorders_rv_checkout);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivty.this);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		cart_rv.setLayoutManager(linearLayoutManager);





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


		ordertotal_tv_checkout.setText(ApplicationController.getInstance().formatNumber(total));


        ordertotal_tv_checkout.setText(String.valueOf(floatTemp));


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








		btn_like = (ImageView)v.findViewById(R.id.btn_like);
		btn_like.setVisibility(View.GONE);

		/*btn_back = (ImageView)v.findViewById(R.id.btn_back_title);
		back_btn.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
*/


		countries_img = (ImageView)v.findViewById(R.id.countries_img);
		countries_img.setVisibility(View.GONE);













		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}




	private void setupHeader(){

	}



	public void callPlaceOrderService(){


		final ProgressDialog progressDialog = new ProgressDialog(CheckoutActivty.this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/place-order.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("resPlaceOrder",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				try {
					JSONObject jsonObject=new JSONObject(response);
					String reply=jsonObject.getString("status");
					Log.e("status",""+reply);
					if(reply.equals("Success")) {


						Toast.makeText(CheckoutActivty.this,"Payment done",Toast.LENGTH_LONG);
					/*	if(collect_payment){
							//intent.putExtra("id", jsonObject.getString("invoice_id"));
							*//*invoiceid = jsonObject.getString("invoice_id");


							startPayment(invoiceid);
*//*

							Toast.makeText(CheckoutActivty.this,"Payment done",Toast.LENGTH_SHORT);
						}else {

						*//*	Intent intent = new Intent(CheckoutActivty.this, ThankyouActivity.class);
							intent.putExtra("id", jsonObject.getString("invoice_id"));
							invoiceid = jsonObject.getString("invoice_id");
							startActivity(intent);*//*
							//finish();
						}*/

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
						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
					}
				}){
			@Override
			protected Map<String,String> getParams(){


				JSONObject jsonObject_to_send = new JSONObject();







				JSONObject address = new JSONObject();

				try {

					address.put("firstname",addressChechout_data.fname);
					address.put("lastname",addressChechout_data.lname);
					address.put("address",addressChechout_data.address);
					address.put("city",addressChechout_data.city);
					address.put("state",addressChechout_data.state);
					address.put("country",addressChechout_data.country);
					address.put("phone",addressChechout_data.phone);
					address.put("email",addressChechout_data.email);
					address.put("pincode",addressChechout_data.pincode);
					address.put("message","test");



					jsonObject_to_send.put("address",address);
					jsonObject_to_send.put("products",getProductasJson());
					jsonObject_to_send.put("coupon_code"," ");
					jsonObject_to_send.put("currency_code",Session.getCurrencyCode(CheckoutActivty.this));
					Log.e("ValidCoupenCodes",""+getIntent().getStringExtra("coupon_code"));

					/*if(collect_payment) {

						jsonObject_to_send.put("payment_method", "Online");
					}else{
						jsonObject_to_send.put("payment_method", "Cash");
					}*/

					jsonObject_to_send.put("discount_amount",String.valueOf(getIntent().getIntExtra("discount_amount",0)));
					jsonObject_to_send.put("price",ordertotal_tv_checkout.getText().toString());
					jsonObject_to_send.put("delivery_charges",String.valueOf(getIntent().getIntExtra("delivery_charges",0)));
					jsonObject_to_send.put("member_id",Session.getUserid(CheckoutActivty.this));



					if(collect_payment) {

						jsonObject_to_send.put("payment", "1");
						jsonObject_to_send.put("payment_method", "1");
					}


				} catch (JSONException e) {
					e.printStackTrace();
				}





				Map<String,String> parameters = new HashMap<String, String>();
				parameters.put("content",jsonObject_to_send.toString());

				for (Map.Entry<String,String> entry : parameters.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					// do stuff
					Log.e(key,value);
				}

				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}



	public JSONArray getProductasJson(){


		ArrayList<Object> arrayListtemp = (ArrayList<Object>) ApplicationController.getInstance().cartProducts;

		total=0;
		JSONArray temp = new JSONArray();
		for(int i=0;i<arrayListtemp.size();i++) {

			Cart_Data temp_obj = (Cart_Data) arrayListtemp.get(i);
			cart_data.add(temp_obj);


			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("product_id",cart_data.get(i).shop_data.id);
				jsonObject.put("quantity",cart_data.get(i).cartquantity);
				jsonObject.put("price",cart_data.get(i).shop_data.price);
				temp.put(jsonObject);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}








		return temp;

	};
}
