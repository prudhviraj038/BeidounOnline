package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class Shop_Adapter extends RecyclerView.Adapter<Shop_Adapter.MyViewHolder> {
	Context context;
	ArrayList<Shop_Data> data;
	String finalPrice;
	float rate,prices,i;



	public Shop_Adapter(Context context, ArrayList<Shop_Data> data){
		this.context=context;
		this.data=data;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		holder.imageView.setImageResource(R.drawable.sales);

		if (data.get(position).product_images.size()>0) {
			Picasso.get().load(data.get(position).product_images.get(0).image_url).into(holder.imageView);
		}
		//Picasso.get().load(data.get(position).images).into(holder.imageView);


		//holder.price_tv.setText(data.get(position).price);

		holder.title_tv.setText(data.get(position).title);
		holder.subtitle_tv.setText(data.get(position).subtitle);
		holder.code.setText(data.get(position).code);


		holder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context,ProductActivity.class);
				intent.putExtra("openProduct",true);
				intent.putExtra("productDetails",data.get(position));
				Log.e("productid",data.get(position).id);

				context.startActivity(intent);
				/*((ShopActivity)context).finish();*/

				//((HomeActivity)context).getproductDetails(data.get(position));
			}
		});



		holder.like.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				ApplicationController.getInstance().wishList.add(data.get(position));

				Toast.makeText(context,"Added to Wishlist",Toast.LENGTH_LONG).show();
			}
		});


		 prices = Float.valueOf(data.get(position).price);
		// Log.e("rate",""+applicationController.rate);
		 rate = Float.valueOf(Session.getCurrencyRate(context));

		 i  = prices * rate ;

		 finalPrice = String.valueOf(i);
		holder.price_tv.setText(ApplicationController.getInstance().formatNumber(i));

		holder.code.setText(Session.getCurrencyCode(context));

	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		ImageView imageView,like;
		TextView price_tv,strike_tv,title_tv,subtitle_tv,discount_tv,code;

		public MyViewHolder(View itemView){
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.shop_img_item);
			like = (ImageView) itemView.findViewById(R.id.like_shop_img);

			price_tv = (TextView)itemView.findViewById(R.id.price_shop_tv);

			title_tv = (TextView)itemView.findViewById(R.id.title_shop_tv);
			subtitle_tv = (TextView)itemView.findViewById(R.id.subtitle_shop_tv);
			code = (TextView) itemView.findViewById(R.id.code_tv);




		}
	}


}
