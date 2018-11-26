package com.yellowsoft.newproject;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Notifications_Adapter extends BaseAdapter {
    Context context;
    ArrayList<NotifyData> notifyData;




    private static LayoutInflater inflater=null;
    public Notifications_Adapter(Context mainActivity, ArrayList<NotifyData> notifyData) {
        // TODO Auto-generated constructor stubcontext=mainActivity;
        this.context = mainActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notifyData = notifyData;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return notifyData.size();
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

        rowView = inflater.inflate(R.layout.notifi_item, null);




        holder.message=(TextView) rowView.findViewById(R.id.message_tv_notify);
        holder.message.setText(Html.fromHtml(notifyData.get(position).message));

        holder.message.setMovementMethod(LinkMovementMethod.getInstance());


        //Picasso.with(context).load(categories.get(position).icon).into(holder.country_flag);


        return rowView;
        
    }




}