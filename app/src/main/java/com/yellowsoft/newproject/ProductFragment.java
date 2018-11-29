package com.yellowsoft.newproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductFragment extends Fragment {


	TextView save_tv,title_tv_product,subtitle_product_tv,price_product_tv,strikeprice_product_tv,discount_product_tv,amberpoints_tv;
	TextView pricematch_tv,sizeguide_tv,addtowishlist_tv,product_code_tv,description_tv_editor;

	ImageView product_img;

	Shop_Data shop_data;


	public static ProductFragment newInstance(int someInt) {
		ProductFragment myFragment = new ProductFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.product_fragment, container, false);
		Log.e("shopfragment", "shopfragment");



		save_tv = (TextView) view.findViewById(R.id.save_tv);
		title_tv_product = (TextView) view.findViewById(R.id.title_tv_product);
		subtitle_product_tv = (TextView) view.findViewById(R.id.subtitle_product_tv);
		price_product_tv = (TextView) view.findViewById(R.id.price_product_tv);
		description_tv_editor = (TextView) view.findViewById(R.id.description_tv_editor);

		strikeprice_product_tv = (TextView) view.findViewById(R.id.strikeprice_product_tv);
		amberpoints_tv = (TextView) view.findViewById(R.id.amberpoints_tv);
		pricematch_tv = (TextView) view.findViewById(R.id.pricematch_tv);
		sizeguide_tv = (TextView) view.findViewById(R.id.sizeguide_tv);
		addtowishlist_tv = (TextView) view.findViewById(R.id.addtowishlist_tv);
		product_code_tv = (TextView) view.findViewById(R.id.product_code_tv);

		product_img = (ImageView)  view.findViewById(R.id.product_img);



		//discount_product_tv.setText(shop_data.);
		//amberpoints_tv.setText(shop_data.);
		//pricematch_tv.setText(shop_data.title);
		//sizeguide_tv.setText(shop_data.title);
		//addtowishlist_tv.setText(shop_data.title);
		//product_code_tv.setText(shop_data.);




		return view;
	}

	public void productDetails(Shop_Data data){


		title_tv_product.setText(data.title);
		subtitle_product_tv.setText(data.subtitle);
		price_product_tv.setText(data.price);
		strikeprice_product_tv.setText(data.old_price);
		description_tv_editor.setText(Html.fromHtml(data.description));


		Log.e("prices",""+data.price+"   "+data.old_price);
		Log.e("imageURL",""+data.product_images.get(0).image_url);

		Picasso.get().load(data.product_images.get(0).image_url).into(product_img);



	}


	//product request
//	public void CallProductdetails() {
//
//		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//		progressDialog.setMessage("Please Wait....");
//		progressDialog.show();
//		progressDialog.setCancelable(false);
//		String URL = Session.BASE_URL + "api/products.php";
//
//		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//			@Override
//			public void onResponse(String response) {
//				Log.e("res", response);
//				if (progressDialog != null && progressDialog.isShowing()) {
//					progressDialog.dismiss();
//				}
//				try {
//
//					JSONArray jsonArray = new JSONArray(response);
//					Log.e("jsonArray", "" + jsonArray.toString());
//
//					String producttitle = jsonArray.getJSONObject(0).getString("title");
//					Log.e("pagetitletitle", "" + producttitle);
//					title.setText(producttitle);
//
//
//					String price = jsonArray.getJSONObject(0).getString("price");
//					Log.e("price", "" + price);
//					discount_price.setText(price);
//					Session.setPrice(getContext(), price);
//
//
//					String discount = jsonArray.getJSONObject(0).getString("discount");
//					Log.e("discount", "" + discount);
//					String givenprice = discount + price;
//					int i = Integer.parseInt(discount) + Integer.parseInt(price);
//					originalprice_tv.setText("" + i);
//
//					String quantitys = jsonArray.getJSONObject(0).getString("quantity");
//					Log.e("quantity", "" + quantitys);
//					quantity.setText(quantitys);
//
//
//					String description = jsonArray.getJSONObject(0).getString("description");
//					Log.e("description", "" + description);
//					description_tv.setText(Html.fromHtml("<p>" + description + "</p>"));
//
//					String images = jsonArray.getJSONObject(0).getString("images");
//					Log.e("images", "" + images);
//
//					JSONArray jsonArray1 = new JSONArray(images);
//					Log.e("jsonarray1", "" + jsonArray1);
//					Log.e("jsonarray1length", "" + jsonArray1.length());
//					//	Log.e("imagessssss",""+jsonArray1.getJSONObject(0).getString("image"));
//
//					if (jsonArray1.length() > 1) {
//						Log.e("length", "length");
//						for (int j = 0; j <= jsonArray1.length(); j++) {
//							//slidingImage_data.add(new SlidingImage_Data(jsonArray.getJSONObject(j).getString("image")));
//							String s = jsonArray1.getString(j);
//							Log.e("s", "" + s);
//							slidingImage_data.add(new SlidingImage_Data(s));
//							Log.e("imagessssss", "" + jsonArray1.getString(j));
//
//						}
//
//					}
//
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				slidingPageAdapter.notifyDataSetChanged();
//			}
//		},
//				new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						Log.e("error", "" + error);
//						if (progressDialog != null)
//							progressDialog.dismiss();
//						//Snackbar.make(gmail_btn, error.toString(), Snackbar.LENGTH_SHORT).show();
//					}
//				}) {
//			@Override
//			protected Map<String, String> getParams() {
//				Map<String, String> parameters = new HashMap<String, String>();
//				//parameters.put("email",u_name.getText().toString());
//				//	parameters.put("password",password.getText().toString());
//				return parameters;
//			}
//		};
//		ApplicationController.getInstance().addToRequestQueue(stringRequest);
////		slidingPageAdapter.notifyDataSetChanged();
//	}
}
