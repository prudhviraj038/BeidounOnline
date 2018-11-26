package com.yellowsoft.newproject;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
	OrderHistory_Adapter orderHistory_adapter ;

	MyOrdersData ordersData;
	LinearLayout back_btn;
	ImageView back;
	TextView page_title;
	TextView order_number_tv;

	ListView messages_lv;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderdetails);

		messages_lv = (ListView)findViewById(R.id.order_his_lv);

		order_number_tv = (TextView)findViewById(R.id.order_number_tv);
		order_number_tv.setText("Order id: "+getIntent().getStringExtra("order_no"));

		ordersData = (MyOrdersData) getIntent().getSerializableExtra("details");

		for (int i = 0;i<ordersData.messageData.size();i++){

			String message = ordersData.messageData.get(i).message;
			String date = ordersData.messageData.get(i).message_date;

			Log.e("details",""+message+"- "+date);

		}
		orderHistory_adapter = new OrderHistory_Adapter(OrderDetailsActivity.this,ordersData.messageData);

		messages_lv.setAdapter(orderHistory_adapter);
		orderHistory_adapter.notifyDataSetChanged();


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_orderdetails);

		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();

		//messageData.add(new  MessageData());

		//orderHistory_adapter = new OrderHistory_Adapter(OrderDetailsActivity.this,messageData);


	}
	private void setupActionBar() {
//set action bar
		getSupportActionBar().setHomeButtonEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT);
		LayoutInflater inflater = getLayoutInflater();
		View v = inflater.inflate(R.layout.action_bar_login,null);

		page_title = (TextView) v.findViewById(R.id.page_title);
		back_btn = (LinearLayout)v.findViewById(R.id.btn_back_container);

		back = (ImageView)v.findViewById(R.id.btn_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}
	private void setupHeader(){
		page_title.setText("Order history");
		//btn_edit.setVisibility(View.VISIBLE);
		//btn_edit.setText("Search");
		//page_title.setText("Home");
	}
}
