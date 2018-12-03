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

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {
	Context context;
	ArrayList<Cart_Data> items;

	public Cart_Adapter(Context context, ArrayList<Cart_Data> items){
		this.context=context;
		this.items=items;
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);
		MyViewHolder myViewHolder=new MyViewHolder(v);

		return myViewHolder;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position){

		holder.producttitle_tv_cart.setText(items.get(position).shop_data.title);
		holder.subtitle_tv_cart.setText(items.get(position).shop_data.description);
		//holder.quantity_cart.setText(items.get(position).);
		holder.product_price_tv_item.setText(items.get(position).shop_data.price);





	//	holder.productimage.setImageResource(items.get(position).images);
		/*if (items.get(position).images.size()>0) {
			Picasso.get().load(items.get(position).images.get(0).image_url).into(holder.product_img_cart);
		}
		else*/ //holder.product_img_cart.setImageResource(R.drawable.product1);

			holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				/*Intent intent;
				intent =  new Intent(context, ProductActivity.class);
				intent.putExtra("title",items.get(position).product_title);
				intent.putExtra("product",items.get(position));
				context.startActivity(intent);
				Toast.makeText(context,"position"+position,Toast.LENGTH_LONG).show();
*/


			}

		});


	}
	public int getItemCount(){
		return items.size();
	}

	public  class MyViewHolder extends RecyclerView.ViewHolder
	{

		TextView producttitle_tv_cart,subtitle_tv_cart,quantity_cart,product_price_tv_item;
		ImageView product_img_cart;

		public MyViewHolder(View itemView){
			super(itemView);

			producttitle_tv_cart = (TextView)itemView.findViewById(R.id.producttitle_tv_cart);
			subtitle_tv_cart = (TextView)itemView.findViewById(R.id.subtitle_tv_cart);
			quantity_cart = (TextView)itemView.findViewById(R.id.quantity_cart);
			product_price_tv_item = (TextView)itemView.findViewById(R.id.product_price_tv_item);

			product_img_cart = (ImageView)itemView.findViewById(R.id.product_img_cart);
		}
	}
}
