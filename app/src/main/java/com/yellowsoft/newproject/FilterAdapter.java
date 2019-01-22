package com.yellowsoft.newproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {
	Context context;
	ArrayList<Filter_Data> items;

	public FilterAdapter(Context context, ArrayList<Filter_Data> items){
		this.context=context;
		this.items=items;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position){

		holder.title.setText(items.get(position).title);


		holder.checkoff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.checkon.setVisibility(View.VISIBLE);
				holder.checkoff.setVisibility(View.GONE);
			}
		});

		holder.checkon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.checkon.setVisibility(View.GONE);
				holder.checkoff.setVisibility(View.VISIBLE);
			}
		});







	}
	public int getItemCount(){
		return items.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView title;
		ImageView checkon,checkoff;


		public MyViewHolder(View itemView){
			super(itemView);

			title = (TextView)itemView.findViewById(R.id.title_tv_filter);

			checkoff = (ImageView)itemView.findViewById(R.id.checkoff_filter);

			checkon = (ImageView)itemView.findViewById(R.id.checked_filter);



		}
	}
}
