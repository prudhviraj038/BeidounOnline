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

public class Products_Adapter extends RecyclerView.Adapter<Products_Adapter.MyViewHolder> {
	Context context;
	ArrayList<ProductsData> items;

	public Products_Adapter(Context context, ArrayList<ProductsData> items){
		this.context=context;
		this.items=items;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		holder.producttitle.setText(items.get(position).product_title);


		holder.discountprice.setText(items.get(position).originalprice);
		holder.originalprice.setText(items.get(position).discountprice);

	//	holder.productimage.setImageResource(items.get(position).images);
		if (items.get(position).images.size()>0) {
			Picasso.get().load(items.get(position).images.get(0).image_url).placeholder(R.drawable.place_holder).into(holder.productimage);
		}
		else holder.productimage.setImageResource(R.drawable.product1);

			holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent;
				intent =  new Intent(context, ProductActivity.class);
				intent.putExtra("title",items.get(position).product_title);
				intent.putExtra("product",items.get(position));
				context.startActivity(intent);
				//Toast.makeText(context,"position"+position,Toast.LENGTH_LONG).show();



			}

		});


	}
	public int getItemCount(){
		return items.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView producttitle,originalprice,discountprice;
		ImageView productimage;

		public MyViewHolder(View itemView){
			super(itemView);

			producttitle = (TextView)itemView.findViewById(R.id.product_title_productsitem);
			originalprice = (TextView)itemView.findViewById(R.id.originalprice_tv_productsitem);
			discountprice = (TextView)itemView.findViewById(R.id.discounted_price_productsitem);
			productimage = (ImageView)itemView.findViewById(R.id.product_img_productsitem);
		}
	}
}
