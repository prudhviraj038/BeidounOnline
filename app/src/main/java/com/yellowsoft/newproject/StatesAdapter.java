package com.yellowsoft.newproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StatesAdapter extends BaseAdapter {
    Context context;
    ArrayList<StatesData> statesData;




    private static LayoutInflater inflater=null;
    public StatesAdapter(Context mainActivity, ArrayList<StatesData> statesData) {
        // TODO Auto-generated constructor stubcontext=mainActivity;
        this.context = mainActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.statesData = statesData;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return statesData.size();
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
        TextView states;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();

        View rowView;

        rowView = inflater.inflate(R.layout.states_item, null);

        holder.states = (TextView)rowView.findViewById(R.id.states_tv);
        Log.e("state",""+statesData.get(position).title+"   "+statesData.get(position).id);

        holder.states.setText(statesData.get(position).title);

        //Picasso.with(context).load(categories.get(position).icon).into(holder.country_flag);


        return rowView;
        
    }




}