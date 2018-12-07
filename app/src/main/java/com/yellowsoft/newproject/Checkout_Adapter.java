package com.yellowsoft.newproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class Checkout_Adapter extends RecyclerView.Adapter<Checkout_Adapter.MyViewHolder> {
	Context context;
	ArrayList<Cart_Data> items;

	public Checkout_Adapter(Context context, ArrayList<Cart_Data> items){
		this.context=context;
		this.items=items;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		holder.producttitle_tv_cart.setText(items.get(position).shop_data.title);



		float q = Float.valueOf(items.get(position).shop_data.price) * Float.valueOf(Session.getCurrencyRate(context));




		float temp = q * Float.valueOf(items.get(position).cartquantity);



		holder.product_price_tv_item.setText(ApplicationController.getInstance().formatNumber(temp));





	}
	public int getItemCount(){
		return items.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView producttitle_tv_cart,product_price_tv_item,quantity_tv_product;


		public MyViewHolder(View itemView){
			super(itemView);

			producttitle_tv_cart = (TextView)itemView.findViewById(R.id.producttitle_tv_checkout);

			product_price_tv_item = (TextView)itemView.findViewById(R.id.productprice_tv_checkout);

            quantity_tv_product = (TextView)itemView.findViewById(R.id.quantity_tv_checkout);


		}
	}
}
