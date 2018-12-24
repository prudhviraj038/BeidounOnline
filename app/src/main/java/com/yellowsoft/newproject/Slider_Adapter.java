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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class Slider_Adapter extends RecyclerView.Adapter<Slider_Adapter.MyViewHolder> {
	Context context;
	ArrayList<Shop_Data> data;

	public Slider_Adapter(Context context, ArrayList<Shop_Data> data){
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
	public void onBindViewHolder(final MyViewHolder holder,final int position){

		holder.imageView.setImageResource(R.drawable.sales);
		Picasso.get().load(data.get(position).product_images.get(0).image_url).placeholder(R.drawable.logo).into(holder.imageView);

		//Log.e("image",""+data.get(position).product_images.get(0).image_url);

		//holder.discount_tv.setText(data.get(position).old_price);
		float i= Float.parseFloat(data.get(position).price) * Float.parseFloat(Session.getCurrencyRate(context));
		String format = ApplicationController.getInstance().formatNumber(i);
		holder.price_tv.setText(format);
		holder.strike_tv.setText(data.get(position).old_price);
		holder.strike_tv.setVisibility(View.GONE);
		if (Session.getLanguage(context).equals("0")){
			holder.title_tv.setText(data.get(position).title);
		}
		else {
			holder.title_tv.setText(data.get(position).title_ar);
		}

		holder.subtitle_tv.setText(data.get(position).subtitle);
		holder.currency_code_slideritem.setText(Session.getCurrencyCode(context));

		holder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			//	((HomeActivity)context).getproductDetails(data.get(position));

				Intent intent = new Intent(context,ProductActivity.class);
				intent.putExtra("openProduct",true);
				intent.putExtra("productDetails",data.get(position));
				Log.e("productid",data.get(position).id);

				context.startActivity(intent);
			}
		});

		holder.like.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ApplicationController.getInstance().wishList.add(data.get(position));

				holder.liked.setVisibility(View.VISIBLE);
				holder.like.setVisibility(View.GONE);
			}
		});

	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		ImageView imageView,like,liked;
		TextView price_tv,strike_tv,title_tv,subtitle_tv,discount_tv,currency_code_slideritem;

		public MyViewHolder(View itemView){
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.viewpagerimage);
			like = (ImageView) itemView.findViewById(R.id.like_slider_img);
			liked = (ImageView) itemView.findViewById(R.id.liked_slider_img);

			price_tv = (TextView)itemView.findViewById(R.id.price_slider_tv);
			strike_tv = (TextView)itemView.findViewById(R.id.strikeprice_slider_tv);
			title_tv = (TextView)itemView.findViewById(R.id.title_slider_tv);
			subtitle_tv = (TextView)itemView.findViewById(R.id.subtitle_slider_tv);
			discount_tv = (TextView)itemView.findViewById(R.id.discount_slider_tv);
			currency_code_slideritem = (TextView)itemView.findViewById(R.id.currency_code_slideritem);



		}
	}

}
