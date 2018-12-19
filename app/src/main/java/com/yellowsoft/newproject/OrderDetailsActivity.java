package com.yellowsoft.newproject;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
	OrderHistory_Adapter orderHistory_adapter ;

	ArrayList<ProductsData_OrderDetails> productsData_orderDetails = new ArrayList<>();
	MyOrdersData myOrdersData;
	LinearLayout back_btn;
	ImageView back;
	ListView orderdetails_rv;

	ImageView search_img_title;
	ArrayList<Object> orderdetails ;

	Checkout_Adapter orderdetails_adapter;

	TextView page_title;
	TextView orderno_details,address_details,paymentmethod_details,orderstatus_details,subtotal_details;
	TextView deliverych_details,discount_details,orderamt_details;

	ListView messages_lv;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderdetails);


		orderno_details = (TextView) findViewById(R.id.orderno_details);
		address_details = (TextView) findViewById(R.id.address_details);
		paymentmethod_details = (TextView) findViewById(R.id.paymentmethod_details);
		orderstatus_details = (TextView) findViewById(R.id.orderstatus_details);
		//subtotal_details = (TextView) findViewById(R.id.subtotal_details);
		deliverych_details = (TextView) findViewById(R.id.deliverych_details);
		discount_details = (TextView) findViewById(R.id.discount_details);
		orderamt_details = (TextView) findViewById(R.id.orderamt_details);



		orderdetails_rv = (ListView) findViewById(R.id.orderdetails_rv);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetailsActivity.this);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);



		myOrdersData = (MyOrdersData)getIntent().getSerializableExtra("details") ;

		orderno_details.setText(myOrdersData.order_id);
		address_details.setText(myOrdersData.address);
		paymentmethod_details.setText(myOrdersData.payment_method);
		orderstatus_details.setText(myOrdersData.status);
		//subtotal_details.setText(myOrdersData.);
		deliverych_details.setText(myOrdersData.delivery_charges);
		discount_details.setText(myOrdersData.discount_amount);

		float f = Float.valueOf(Session.getCurrencyRate(OrderDetailsActivity.this));

		float f2 = Float.valueOf(myOrdersData.price);

		float Fprice = f*f2;


		orderamt_details.setText(String.valueOf(Fprice));

		//Log.e("arraylist",""+myOrdersData.productsData_orderDetails.get(0).price);

//		orderdetails_adapter = new  Checkout_Adapter(OrderDetailsActivity.this,);

		//orderdetails = myOrdersData.

		String title,price,quantity;
		for (int i =0;i<myOrdersData.productsData_orderDetails.size();i++){

			Log.e("arraylist",""+myOrdersData.productsData_orderDetails.get(i).price);


			title = myOrdersData.productsData_orderDetails.get(i).title;
			price = myOrdersData.productsData_orderDetails.get(i).price;
			quantity = myOrdersData.productsData_orderDetails.get(i).quantity;
			Log.e("price",price);

			productsData_orderDetails.add(new ProductsData_OrderDetails(title,quantity,price));

		}

		orderHistory_adapter = new OrderHistory_Adapter(OrderDetailsActivity.this,productsData_orderDetails);

		orderdetails_rv.setAdapter(orderHistory_adapter);


	//	Log.e("details",""+myOrdersData.payment_method);


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_orderdetails);

		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();



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


		search_img_title = (ImageView)v.findViewById(R.id.search_img_title);
		search_img_title.setVisibility(View.GONE);

		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}
	private void setupHeader(){
		page_title.setText("");
		//btn_edit.setVisibility(View.VISIBLE);
		//btn_edit.setText("Search");
		//page_title.setText("Home");
	}
}
