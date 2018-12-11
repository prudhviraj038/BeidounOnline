package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class Myaddress_Adapter extends RecyclerView.Adapter<Myaddress_Adapter.MyViewHolder> {
	Context context;
	ArrayList<AddressChechout_Data> data;

	public Myaddress_Adapter(Context context, ArrayList<AddressChechout_Data> data){
		this.context=context;
		this.data=data;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item_myaddress,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		holder.address.setText(data.get(position).fname+data.get(position).lname+"\n"+data.get(position).address+"\n"+data.get(position).email+"\n"+data.get(position).phone);


		holder.edit_addressitem_et.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"Edit",Toast.LENGTH_SHORT).show();

				((HomeActivity)context).addAddressFragment("edit",data.get(position).id,data.get(position));

			}
		});

		holder.delete_myadressitem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"Deleted address at position : "+position,Toast.LENGTH_SHORT).show();
			}
		});



	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView  address,edit_addressitem_et,delete_myadressitem;


		public MyViewHolder(View itemView){
			super(itemView);

			address = (TextView) itemView.findViewById(R.id.address_myaddressitem);

			edit_addressitem_et = (TextView) itemView.findViewById(R.id.edit_addressitem_et);
			delete_myadressitem = (TextView) itemView.findViewById(R.id.delete_myadressitem);


		}
	}

}
