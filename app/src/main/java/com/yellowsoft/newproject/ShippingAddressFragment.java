package com.yellowsoft.newproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ShippingAddressFragment extends Fragment {



	Cart_Adapter cart_adapter;
	ArrayList<Cart_Data> cart_data = new ArrayList<>();



	RecyclerView cart_rv;

	public static ShippingAddressFragment newInstance(int someInt) {
		ShippingAddressFragment myFragment = new ShippingAddressFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shipping, container, false);





return  view;

	}

/*public void CallCoupenService(final String coupen, final String total){

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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

						//Log.e("totalss",""+totalss);

						discount_int = Integer.parseInt(discount);
						//totals = addShippingCharges-Integer.parseInt(discount);

						Log.e("subtotal",""+subtotal);

						//total_tv.setText(""+totals);

						//Log.e("total",""+totals);

						//Session.setTotalPrice(pay.this,""+totals);


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
						//Toast.makeText(hs.this,"Internet error",Toast.LENGTH_SHORT).show();
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
	}*/

}
