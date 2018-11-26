package com.yellowsoft.newproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistory_Adapter extends BaseAdapter {
    Context context;
    ArrayList<MessageData> messageData;




    private static LayoutInflater inflater=null;
    public OrderHistory_Adapter(Context mainActivity, ArrayList<MessageData> messageData) {
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
        TextView message,date;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();

        View rowView;

        rowView = inflater.inflate(R.layout.message_item, null);




        holder.message=(TextView) rowView.findViewById(R.id.message_tv_item);
        holder.message.setText(messageData.get(position).message);

        holder.date = (TextView)rowView.findViewById(R.id.date_tv_item);
        holder.date.setText(messageData.get(position).message_date);


        //Picasso.with(context).load(categories.get(position).icon).into(holder.country_flag);


        return rowView;
        
    }




}