package com.yellowsoft.newproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class Slider_Adapter extends RecyclerView.Adapter<Slider_Adapter.MyViewHolder> {
	Context context;
	ArrayList<Slider_Data> data;

	public Slider_Adapter(Context context, ArrayList<Slider_Data> data){
		this.context=context;
		this.data=data;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.slidingimage_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		//holder.imageView.setImageResource(R.drawable.sales);
		Picasso.get().load(data.get(position).image).into(holder.imageView);

		holder.discount_tv.setText(data.get(position).discount);
		holder.price_tv.setText(data.get(position).price);
		holder.strike_tv.setText(data.get(position).strikePrice);
		holder.title_tv.setText(data.get(position).title);
		holder.subtitle_tv.setText(data.get(position).subtitle);

	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		ImageView imageView;
		TextView price_tv,strike_tv,title_tv,subtitle_tv,discount_tv;

		public MyViewHolder(View itemView){
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.viewpagerimage);

			price_tv = (TextView)itemView.findViewById(R.id.price_slider_tv);
			strike_tv = (TextView)itemView.findViewById(R.id.strikeprice_slider_tv);
			title_tv = (TextView)itemView.findViewById(R.id.title_slider_tv);
			subtitle_tv = (TextView)itemView.findViewById(R.id.subtitle_slider_tv);
			discount_tv = (TextView)itemView.findViewById(R.id.discount_slider_tv);



		}
	}

}
