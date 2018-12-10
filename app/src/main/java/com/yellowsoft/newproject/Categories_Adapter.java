package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
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

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.MyViewHolder> {
	Context context;
	ArrayList<CatergoriesData> data;

	public Categories_Adapter(Context context, ArrayList<CatergoriesData> data){
		this.context=context;
		this.data=data;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		//holder.imageView.setImageResource(R.drawable.sales);
		Picasso.get().load(data.get(position).images).into(holder.imageView);

		holder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (Integer.getInteger(data.get(position).subcat_cnt) == 0) {
					Toast.makeText(context, "No sub Categories", Toast.LENGTH_LONG).show();
				} else {

					Intent intent = new Intent(context, ShopActivity.class);
					intent.putExtra("categories", data.get(position).id);
					context.startActivity(intent);
					// 	((HomeActivity)context).insta_shop(data.get(position).id,data.get(position).title);
				}
			}
		});



	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		ImageView imageView;


		public MyViewHolder(View itemView){
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.categories_img_item);



		}
	}

}
