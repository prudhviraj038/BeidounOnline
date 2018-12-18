package com.yellowsoft.newproject;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProductFragment extends Fragment {


	TextView title_tv_product,subtitle_product_tv,price_product_tv;
	TextView code_tv_product,quatity_tv_product;
	TextView description_tv_editor;

	ImageView product_img,plus_img,minus_img;
	LinearLayout addtobag_ll,minus_ll;

	Shop_Data shop_data;

	int quantity;
    Integer i;

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
		View view = inflater.inflate(R.layout.fragment_product, container, false);
		Log.e("shopfragment", "shopfragment");



	//	save_tv = (TextView) view.findViewById(R.id.save_tv);
		title_tv_product = (TextView) view.findViewById(R.id.title_tv_product);
		subtitle_product_tv = (TextView) view.findViewById(R.id.subtitle_product_tv);
		price_product_tv = (TextView) view.findViewById(R.id.price_product_tv);
		description_tv_editor = (TextView) view.findViewById(R.id.description_tv_editor);
		code_tv_product = (TextView) view.findViewById(R.id.code_tv_product);

		quatity_tv_product = (TextView)view.findViewById(R.id.quantity_tv_product);




		product_img = (ImageView)  view.findViewById(R.id.product_img);
		minus_img = (ImageView) view.findViewById(R.id.minus_img);
		minus_ll = (LinearLayout) view.findViewById(R.id.minus_ll);
		plus_img = (ImageView) view.findViewById(R.id.plus_img);

//		plus_img.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				checkQuantity(quatity_tv_product.getText().toString());
//			}
//		});




        i = Integer.parseInt(quatity_tv_product.getText().toString());

        minus_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // checkQuantity(quatity_tv_product.getText().toString());
                String _stringVal;
                if (i > 1) {
                    i = i - 1;
                    _stringVal = String.valueOf(i);
                    quatity_tv_product.setText(_stringVal);
                } else {
                    Log.e("src", "Value can't be less than 0");
                }
            }
        });
        plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  checkQuantity(quatity_tv_product.getText().toString());

                String _stringVal;

               Log.e("src", "Increasing value...");
                i = i + 1;
                _stringVal = String.valueOf(i);
                quatity_tv_product.setText(_stringVal);
            }
        });





		addtobag_ll = (LinearLayout)view.findViewById(R.id.addtobag_ll);

		addtobag_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkQuantity(quatity_tv_product.getText().toString());
			}
		});

		//discount_product_tv.setText(shop_data.);
		//amberpoints_tv.setText(shop_data.);
		//pricematch_tv.setText(shop_data.title);
		//sizeguide_tv.setText(shop_data.title);
		//addtowishlist_tv.setText(shop_data.title);
		//product_code_tv.setText(shop_data.);


		shop_data = (Shop_Data)getArguments().getSerializable("productDetails");

		productDetails(shop_data);


		return view;
	}

	public void productDetails(Shop_Data data){

		shop_data = data;

		title_tv_product.setText(data.title);
		subtitle_product_tv.setText(data.subtitle);


		//strikeprice_product_tv.setText(data.old_price);
		description_tv_editor.setText(Html.fromHtml(data.description));



		float price = Float.valueOf(data.price) * Float.valueOf(Session.getCurrencyRate(getContext()));
		price_product_tv.setText(String.valueOf(price));

		code_tv_product.setText(Session.getCurrencyCode(getContext()));

		Log.e("prices",""+data.price+"   "+data.old_price);
		Log.e("imageURL",""+data.product_images.get(0).image_url);
		Log.e("quantity",""+data.quantity);

		quantity = Integer.parseInt(quatity_tv_product.getText().toString());

		Picasso.get().load(data.product_images.get(0).image_url).into(product_img);



	}




	public void checkQuantity(String q) {

		int i = Integer.parseInt(q);

		int j = Integer.parseInt(shop_data.quantity);


		if (i<=j){


			Cart_Data cartData = new Cart_Data(shop_data,i);
			Log.e("quantityproductchanged",""+i);

			ApplicationController.getInstance().cartProducts.add(cartData);

			//((HomeActivity)getActivity()).sendtoCart();

			Intent intent = new Intent(getContext(),HomeActivity.class);
			intent.putExtra("sendtoCart",true);
			startActivity(intent);

			//Session.setQuantity(getContext(),String.valueOf(i));
			//i=i-1;
            //
		}
		else {



		    quatity_tv_product.setText(""+quantity);

		    Session.setQuantity(getContext(),String.valueOf(i));
			Toast.makeText(getContext(),"Maximum quantity is :"+quantity,Toast.LENGTH_LONG).show();


        }

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
