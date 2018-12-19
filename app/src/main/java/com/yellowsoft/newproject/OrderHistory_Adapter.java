package com.yellowsoft.newproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistory_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ProductsData_OrderDetails> messageData;




    private static LayoutInflater inflater=null;
    public OrderHistory_Adapter(Context mainActivity, ArrayList<ProductsData_OrderDetails> messageData) {
        // TODO Auto-generated constructor stubcontext=mainActivity;
        this.context = mainActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messageData = messageData;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return messageData.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    public class Holder
    {
        TextView title,quantity,price;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();

        View rowView;

        rowView = inflater.inflate(R.layout.checkout_item, null);




        holder.title=(TextView) rowView.findViewById(R.id.producttitle_tv_checkout);
        holder.title.setText(messageData.get(position).title);

        holder.quantity = (TextView)rowView.findViewById(R.id.quantity_tv_checkout);
        holder.quantity.setText(messageData.get(position).quantity);

        holder.price = (TextView)rowView.findViewById(R.id.productprice_tv_checkout);
      ///  holder.price.setText(messageData.get(position).price);

        float f = Float.valueOf(Session.getCurrencyRate(context));

        float f2 = Float.valueOf(messageData.get(position).price);

        float Fprice = f*f2;

        holder.price.setText(String.valueOf(Fprice));

        //Picasso.with(context).load(categories.get(position).icon).into(holder.country_flag);


        return rowView;
        
    }




}