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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class CheckoutAddress_Adapter extends RecyclerView.Adapter<CheckoutAddress_Adapter.MyViewHolder> {
	Context context;
	ArrayList<AddressChechout_Data> data;

	public CheckoutAddress_Adapter(Context context, ArrayList<AddressChechout_Data> data){
		this.context=context;
		this.data=data;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item_checkout,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;

		try {

			if (Session.getLanguage(context).equals("0")) {
				holder.address_title_checkoutaddress_item.setText(jsonObjectEN.getString("ADDRESSES"));
			}else {
				holder.address_title_checkoutaddress_item.setText(jsonObjectAR.getString("ADDRESSES"));
			}
		}
		catch (JSONException j){j.printStackTrace();}



		holder.address.setText(data.get(position).fname+data.get(position).lname+"\n"+data.get(position).address+"\n"+data.get(position).email+"\n"+data.get(position).phone);


		holder.delete_ckeckoutitem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
			}
		});

		Log.e("address",data.get(position).state+""+data.get(position).country);
	/*	holder.checkoutitem_checkoff_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(context,"Checked address of: "+position,Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(context,CheckoutActivty.class);
				intent.putExtra("address",data.get(position));
				context.startActivity(intent);
			}
		});*/

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,CheckoutActivty.class);
				intent.putExtra("address",data.get(position));
				context.startActivity(intent);
			}
		});

		/*holder.password.setText(data.get(position).password);
		holder.vehicle_id.setText(data.get(position).veh_id);*/




		/*if(position %2 == 1)
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
			*//*	Intent intent;
				intent =  new Intent(context, Order_type.class);
				intent.putExtra("service",items.get(position));
				context.startActivity(intent);*//*

			}

		});*/


	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView  address,address_title_checkoutaddress_item;
		ImageView checkoutitem_checkoff_img,delete_ckeckoutitem;

		public MyViewHolder(View itemView){
			super(itemView);

			address = (TextView) itemView.findViewById(R.id.address_checkoutitem);
			address_title_checkoutaddress_item = (TextView)itemView.findViewById(R.id.address_title_checkoutaddress_item);
			checkoutitem_checkoff_img = (ImageView) itemView.findViewById(R.id.checkoutitem_checkoff_img);
			delete_ckeckoutitem = (ImageView) itemView.findViewById(R.id.delete_ckeckoutitem);


		}
	}

}
