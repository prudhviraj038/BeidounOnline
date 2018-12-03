package com.yellowsoft.newproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Countries_Adapter extends BaseAdapter {
    Context context;
    ArrayList<CountryData> countryData;




    private static LayoutInflater inflater=null;
    public Countries_Adapter(Context mainActivity, ArrayList<CountryData> countryData) {
        // TODO Auto-generated constructor stubcontext=mainActivity;
        this.context = mainActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.countryData = countryData;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return countryData.size();
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
        TextView title,cur_name;
        ImageView country_flag;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();

        View rowView;

        rowView = inflater.inflate(R.layout.countries_item, null);




        holder.title=(TextView) rowView.findViewById(R.id.country_title_item);
        holder.title.setText(countryData.get(position).name);

        //holder.cur_name=(TextView)rowView.findViewById(R.id.menu_iem2);
       // holder.cur_name.setText(menuItems.get(position).title_ar);


        holder.country_flag = (ImageView) rowView.findViewById(R.id.country_img_item);
       // holder.country_flag.setImageResource(countryData.get(position).image);

        Picasso.get().load(countryData.get(position).image).into(holder.country_flag);


        return rowView;
        
    }




}