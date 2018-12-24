package com.yellowsoft.newproject;

import android.app.ProgressDialog;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/1/2018.
 */

public class Myaddress_Adapter extends RecyclerView.Adapter<Myaddress_Adapter.MyViewHolder> {
	Context context;
	MyAddressFragment myAddressFragment;
	ArrayList<AddressChechout_Data> data;

	public Myaddress_Adapter(Context context, ArrayList<AddressChechout_Data> data,MyAddressFragment myAddressFragment){
		this.context=context;
		this.data=data;
		this.myAddressFragment=myAddressFragment;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item_myaddress,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;
try {


	if (Session.getLanguage(context).equals("0")) {
		holder.address_title_myaddress.setText(jsonObjectEN.getString("ADDRESSES"));
		holder.edit_addressitem_et.setText(jsonObjectEN.getString("EDIT"));
		holder.delete_myadressitem.setText(jsonObjectEN.getString("DELETE"));
	} else {
		holder.address_title_myaddress.setText(jsonObjectAR.getString("ADDRESSES"));
		holder.edit_addressitem_et.setText(jsonObjectAR.getString("EDIT"));
		holder.delete_myadressitem.setText(jsonObjectAR.getString("DELETE"));
	}
}catch (JSONException j){
	j.printStackTrace();
}

		holder.address.setText(data.get(position).fname+data.get(position).lname+"\n"+data.get(position).address+"\n"+data.get(position).email+"\n"+data.get(position).phone);


		holder.edit_addressitem_et.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			//	Toast.makeText(context,"Edit",Toast.LENGTH_SHORT).show();

				((HomeActivity)context).addAddressFragment("edit",data.get(position).id,data.get(position));

			}
		});

		holder.delete_myadressitem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				myAddressFragment.deleteAddress(data.get(position).id);

				//Toast.makeText(context,"Deleted address at position : "+position,Toast.LENGTH_SHORT).show();
			}
		});



	}
	public int getItemCount(){
		return data.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView  address,edit_addressitem_et,delete_myadressitem;
		TextView address_title_myaddress;


		public MyViewHolder(View itemView){
			super(itemView);

			address = (TextView) itemView.findViewById(R.id.address_myaddressitem);
			address_title_myaddress = (TextView) itemView.findViewById(R.id.address_title_myaddress);

			edit_addressitem_et = (TextView) itemView.findViewById(R.id.edit_addressitem_et);
			delete_myadressitem = (TextView) itemView.findViewById(R.id.delete_myadressitem);


		}
	}



}
