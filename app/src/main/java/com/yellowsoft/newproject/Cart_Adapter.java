package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {
	Context context;
	ArrayList<Cart_Data> items;
	CartFragment cartFragment;
	int quantity;



	int i = 1;


	public Cart_Adapter(Context context, ArrayList<Cart_Data> items,CartFragment cartFragment){
		this.context=context;
		this.items=items;
		this.cartFragment = cartFragment;
	}


	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position){


		if (Session.getLanguage(context).equals("0")){

			holder.producttitle_tv_cart.setText(items.get(position).shop_data.title);
			holder.subtitle_tv_cart.setText(Html.fromHtml(items.get(position).shop_data.description));
		}
		else {

			holder.producttitle_tv_cart.setText(items.get(position).shop_data.title_ar);
			holder.subtitle_tv_cart.setText(Html.fromHtml(items.get(position).shop_data.description_ar));
		}


		holder.quantity_cart.setText(items.get(position).shop_data.quantity);


		holder.quantity_tv_product.setText(String.valueOf(items.get(position).cartquantity));


		//holder.quantity_tv_product.setText(String.valueOf(userQuantity));
		//holder.product_price_tv_item.setText(items.get(position).shop_data.price);


		final float q = Float.valueOf(items.get(position).shop_data.price) * Float.valueOf(Session.getCurrencyRate(context));


		//holder.quantity_tv_product.setText(String.valueOf(items.get(position).cartquantity));

		//float temp = q * Float.valueOf(items.get(position).cartquantity);



		holder.product_price_tv_item.setText(ApplicationController.getInstance().formatNumber(q));


		if (items.get(position).shop_data.product_images.size()>0) {
			Picasso.get().load(items.get(position).shop_data.product_images.get(0).image_url).into(holder.product_img_cart);
		}







		holder.currency_code_tv.setText(Session.getCurrencyCode(context));



		i = items.get(position).cartquantity;

		holder.minus_cart_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				String _stringVal;
				if (i > 1) {
					i = i - 1;
					_stringVal = String.valueOf(i);
					//holder.quantity_tv_product.setText(_stringVal);

					Cart_Data temp_obj =

							(Cart_Data)(ApplicationController.getInstance().cartProducts.get(position));

					temp_obj.cartquantity = temp_obj.cartquantity-1;

					ApplicationController.getInstance().cartProducts.remove(position);

					ApplicationController.getInstance().cartProducts.add(position,temp_obj);

					cartFragment.getCartItems();



				} else {
					Log.e("src", "Value can't be less than 0");
					items.remove(position);
					notifyDataSetChanged();

					ApplicationController.getInstance().cartProducts.remove(position);
					//notifyDataSetChanged();

					cartFragment.getCartItems();

				}
			}
		});



		holder.plus_cart_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				int avalQty= Integer.parseInt(items.get(position).shop_data.quantity);

				int userQty = items.get(position).cartquantity;

				if (avalQty == userQty){


					Toast.makeText(context,"Maximum quantity is :"+avalQty,Toast.LENGTH_SHORT).show();

				}
				else {
					Cart_Data temp_obj =

					(Cart_Data)(ApplicationController.getInstance().cartProducts.get(position));

					temp_obj.cartquantity = temp_obj.cartquantity+1;

					ApplicationController.getInstance().cartProducts.remove(position);

					ApplicationController.getInstance().cartProducts.add(position,temp_obj);

					cartFragment.getCartItems();



				}



				//holder.quantity_tv_product.setText(_stringVal);
			}
		});

		quantity = Integer.parseInt(items.get(position).shop_data.quantity);



		holder.delete_product_img_cart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				items.remove(position);
				notifyDataSetChanged();

				ApplicationController.getInstance().cartProducts.remove(position);
				//notifyDataSetChanged();

				cartFragment.getCartItems();


			}
		});



	}
	public int getItemCount(){
		return items.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView producttitle_tv_cart,subtitle_tv_cart,quantity_cart,product_price_tv_item,quantity_tv_product,currency_code_tv;
		ImageView product_img_cart,delete_product_img_cart;
		LinearLayout plus_cart_ll,minus_cart_ll;

		public MyViewHolder(View itemView){
			super(itemView);

			producttitle_tv_cart = (TextView)itemView.findViewById(R.id.producttitle_tv_cart);
			subtitle_tv_cart = (TextView)itemView.findViewById(R.id.subtitle_tv_cart);
			quantity_cart = (TextView)itemView.findViewById(R.id.quantity_cart);
			product_price_tv_item = (TextView)itemView.findViewById(R.id.product_price_tv_cart);
			currency_code_tv = (TextView) itemView.findViewById(R.id.currency_code_tv);
            quantity_tv_product = (TextView)itemView.findViewById(R.id.quantity_tv_cartitem);

			product_img_cart = (ImageView)itemView.findViewById(R.id.product_img_cart);
			delete_product_img_cart = (ImageView)itemView.findViewById(R.id.delete_product_img_cart);

			minus_cart_ll = (LinearLayout) itemView.findViewById(R.id.minus_cart_ll);
			plus_cart_ll = (LinearLayout) itemView.findViewById(R.id.plus_cart_ll);
		}
	}










}
