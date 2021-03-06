package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class MyOrders_Adapter extends RecyclerView.Adapter<MyOrders_Adapter.MyViewHolder> {
	Context context;
	ArrayList<MyOrdersData> items;

	public MyOrders_Adapter(Context context, ArrayList<MyOrdersData> items){
		this.context=context;
		this.items=items;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(final MyViewHolder holder,final int position){

		//holder.product_tittle.setText(items.get(position).tittle);
		//holder.address_myorders_tv.setText(items.get(position).address);
		holder.order_ids.setText(items.get(position).order_id);

		//holder.quantity_tv_myorders.setText(items.get(position).quantity);
		//holder.track_link.setText(items.get(position).tracking_link);


		holder.date_myorders.setText(items.get(position).date);

		float f = Float.valueOf(items.get(position).price) * Float.valueOf(Session.getCurrencyRate(context));

		holder.price_tv_myorders.setText(String.valueOf(f));


		holder.currcode_myorders.setText(Session.getCurrencyCode(context));


		//	holder.myorders_status.setText(items.get(position).status);

	/*	if(!items.get(position).images.equals(""))
		Picasso.get().load(items.get(position).images).into(holder.order_img);
*/




		holder.summary_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent =  new Intent(context, OrderDetailsActivity.class);
				intent.putExtra("details",items.get(position));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
				//Toast.makeText(context,""+items.get(position).messageData,Toast.LENGTH_LONG).show();
				//showAlert(items.get(position).messageData);

			}

		});


	}
	public int getItemCount(){
		return items.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		ImageView order_img;
		TextView address_myorders_tv, order_ids, quantity_tv_myorders, price_tv_myorders, product_tittle, myorders_status;
		TextView date_myorders, track_link;
		TextView summary_tv,currcode_myorders;
		public MyViewHolder(View itemView){
			super(itemView);

			//product_tittle = (TextView) itemView.findViewById(R.id.producttitle_tv);
			order_ids = (TextView)itemView.findViewById(R.id.orderid_tv_myorders);
			//quantity_tv_myorders = (TextView)itemView.findViewById(R.id.quantity_tv_myorders);
			price_tv_myorders = (TextView)itemView.findViewById(R.id.price_tv_myorders);
		//	address_myorders_tv=(TextView)itemView.findViewById(R.id.address_myorders_tv);
		//	myorders_status = (TextView) itemView.findViewById(R.id.ordersstatus_tv);
			date_myorders = (TextView)itemView.findViewById(R.id.date_myorders);
			summary_tv = (TextView)itemView.findViewById(R.id.summary_tv);
			currcode_myorders = (TextView)itemView.findViewById(R.id.currcode_myorders);
		//	track_link = (TextView)itemView.findViewById(R.id.trackinglink_tv_orders);

		//	order_img=(ImageView)itemView.findViewById(R.id.order_img);


		}
	}



}
