package com.yellowsoft.newproject;

import android.content.Context;
import android.graphics.Color;
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

public class Track_Adapter extends RecyclerView.Adapter<Track_Adapter.MyViewHolder> {
	Context context;
	ArrayList<TrackData> data;

	public Track_Adapter(Context context, ArrayList<TrackData> data){
		this.context=context;
		this.data=data;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		holder.uname.setText(data.get(position).uname);
		holder.password.setText(data.get(position).password);
		holder.vehicle_id.setText(data.get(position).veh_id);


		if(position %2 == 1)
		{
			holder.itemView.setBackgroundColor(Color.parseColor("#e4e4e4"));
			//  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
		}
		else
		{
			holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
			//  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
		}
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			/*	Intent intent;
				intent =  new Intent(context, Order_type.class);
				intent.putExtra("service",items.get(position));
				context.startActivity(intent);*/

			}

		});


	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView uname, password,vehicle_id;

		public MyViewHolder(View itemView){
			super(itemView);

			uname = (TextView) itemView.findViewById(R.id.usrname_track_tv);
			password = (TextView)itemView.findViewById(R.id.password_track_tv);
			vehicle_id = (TextView) itemView.findViewById(R.id.vehid_track_tv);

		}
	}

}
