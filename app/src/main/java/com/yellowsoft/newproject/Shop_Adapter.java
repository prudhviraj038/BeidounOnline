package com.yellowsoft.newproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
		Picasso.get().load(data.get(position).image).into(holder.imageView);


		holder.price_tv.setText(data.get(position).price);

		holder.title_tv.setText(data.get(position).title);
		holder.subtitle_tv.setText(data.get(position).subtitle);


		holder.like.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"Added to Wishlist",Toast.LENGTH_LONG).show();
			}
		});

	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		ImageView imageView,like;
		TextView price_tv,strike_tv,title_tv,subtitle_tv,discount_tv;

		public MyViewHolder(View itemView){
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.shop_img_item);
			like = (ImageView) itemView.findViewById(R.id.like_shop_img);

			price_tv = (TextView)itemView.findViewById(R.id.price_shop_tv);

			title_tv = (TextView)itemView.findViewById(R.id.title_shop_tv);
			subtitle_tv = (TextView)itemView.findViewById(R.id.subtitle_shop_tv);




		}
	}

}
