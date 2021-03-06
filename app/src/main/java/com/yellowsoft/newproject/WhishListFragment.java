package com.yellowsoft.newproject;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WhishListFragment extends Fragment {


	TextView shopping;

	ArrayList<Shop_Data> wishlistData = new ArrayList<>();

	LinearLayout empty_wishlist_ll,wishlist_items_ll;

	RecyclerView wishlist_rv;
	Shop_Adapter shop_adapter;

	public static WhishListFragment newInstance(int someInt) {
		WhishListFragment myFragment = new WhishListFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_wishlist, container, false);



		shopping = (TextView)view.findViewById(R.id.shopping_ll_wishlist);

		shopping.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).openHome();

			}
		});


		wishlist_items_ll = (LinearLayout)view.findViewById(R.id.wishlist_items_ll);
		wishlist_items_ll.setVisibility(View.GONE);

		empty_wishlist_ll = (LinearLayout)view.findViewById(R.id.empty_wishlist_ll);
		empty_wishlist_ll.setVisibility(View.GONE);


		wishlist_rv = (RecyclerView)view.findViewById(R.id.wishlist_rv);

		final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);

		wishlist_rv.setLayoutManager(gridLayoutManager);



		/*coupencode_ll = (LinearLayout)view. findViewById(R.id.coupencode_ll);
		total_tv = (TextView)view. findViewById(R.id.total_tv);
		subtotal_tv = (TextView)view.findViewById(R.id.subtotal_tv);
		discount_tv = (TextView)view.findViewById(R.id.discount_tv);*/
		//quantity.setText(cartquantity.toString());

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




		//prdcheckout_btn = (LinearLayout)view.findViewById(R.id.proceedtocheckout_ll_btn);
	/*	prdcheckout_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int total_to_send;
				total_to_send = subtotal+shippingcharges_int;

				intent1.putExtra("product",product);

				intent1.putExtra("price",subtotal);
				intent1.putExtra("delivery_charges",shippingcharges_int);
				if (coupon_code_str.equals("")) {
					intent1.putExtra("discount_amount", "");
					intent1.putExtra("coupon_code", "");

				}
				else {

					intent1.putExtra("discount_amount", discount_int);
					intent1.putExtra("coupon_code", coupon_code_str);
					total_to_send =total_to_send-discount_int;
				}


				if (schemeAmtUsed==true) {
					intent1.putExtra("schemeAmt_used", "1");
					intent1.putExtra("scheme_amount",scheme_amt);
					total_to_send =total_to_send-Integer.parseInt(scheme_amt);
				}
				else {
					intent1.putExtra("schemeAmt_used", "0");

					intent1.putExtra("scheme_amount","0");
				}

				intent1.putExtra("total_price",total_to_send);
				Log.e("total_tosend",""+total_to_send);
				startActivity(intent1);
			}
		});
*/
		getWishlistItems();

return  view;

	}


	public void getWishlistItems(){

		ArrayList<Object> temp = (ArrayList<Object>) ApplicationController.getInstance().wishList;

		//total=0;
		//cart_data.clear();

		for(int i=0;i<temp.size();i++) {

			Shop_Data temp_obj = (Shop_Data) temp.get(i);
			wishlistData.add(temp_obj);


			//total = total + (temp_obj.cartquantity * Float.parseFloat(temp_obj.shop_data.price));

		}

		Log.e("tempsize= ",""+temp.size());
		//Toast.makeText(getContext(),""+temp.size(),Toast.LENGTH_LONG).show();

		//ArrayList<Shop_Data> shop_data  = (ArrayList<Shop_Data>)(wishlistData);

		shop_adapter = new Shop_Adapter(getContext(),wishlistData,2);
		shop_adapter.notifyDataSetChanged();

		wishlist_rv.setAdapter(shop_adapter);


		if(temp.size()==0){

			empty_wishlist_ll.setVisibility(View.VISIBLE);
			wishlist_items_ll.setVisibility(View.GONE);
		}
		else {
			empty_wishlist_ll.setVisibility(View.GONE);
			wishlist_items_ll.setVisibility(View.VISIBLE);
		}






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
