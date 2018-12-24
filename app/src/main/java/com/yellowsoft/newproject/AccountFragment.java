package com.yellowsoft.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountFragment extends Fragment {


	LinearLayout points_ll,usrinfo_ll,address_ll,mywishlist_ll,myorders_ll;


	TextView orders_myaccount,wishlist_myaccount,personalinfo_myaccount,address_myaccount,loyality_myaccount;
	TextView orders_des_myaccount,wishlist_des_myaccount,personalinfo_des_myaccount,address_des_myaccount,loyality_des_myaccount;

	@Nullable
	@Override
	public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_account, container, false);



		orders_myaccount = (TextView) view.findViewById(R.id.orders_myaccount);
		wishlist_myaccount = (TextView) view.findViewById(R.id.wishlist_myaccount);
		personalinfo_myaccount = (TextView) view.findViewById(R.id.personalinfo_myaccount);
		address_myaccount = (TextView) view.findViewById(R.id.address_myaccount);
		loyality_myaccount = (TextView) view.findViewById(R.id.loyality_myaccount);

		orders_des_myaccount = (TextView) view.findViewById(R.id.orders_des_myaccount);
		wishlist_des_myaccount = (TextView) view.findViewById(R.id.wishlist_des_myaccount);
		personalinfo_des_myaccount = (TextView) view.findViewById(R.id.personalinfo_des_myaccount);
		address_des_myaccount = (TextView) view.findViewById(R.id.address_des_myaccount);
		loyality_des_myaccount = (TextView) view.findViewById(R.id.loyality_des_myaccount);


		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;

		try {

			if (Session.getLanguage(getContext()).equals("0")){

				orders_myaccount.setText(jsonObjectEN.getString("ORDERS"));
				wishlist_myaccount.setText(jsonObjectEN.getString("WISHLIST"));
				personalinfo_myaccount.setText(jsonObjectEN.getString("PERSONAL INFO"));
				address_myaccount.setText(jsonObjectEN.getString("ADDRESSES"));
				loyality_myaccount.setText(jsonObjectEN.getString("LOYALITY POINTS"));

				orders_des_myaccount.setText(jsonObjectEN.getString("VIEW YOUR ORDER INFO"));
				wishlist_des_myaccount.setText(jsonObjectEN.getString("VIEW AND MODIFY ITEMS ON YOUR WISHLIST"));
				personalinfo_des_myaccount.setText(jsonObjectEN.getString("PERSONAL INFO"));
				address_des_myaccount.setText(jsonObjectEN.getString("ADDRESSES"));
				loyality_des_myaccount.setText(jsonObjectEN.getString("LOYALITY POINTS"));

			}
			else {

				orders_myaccount.setText(jsonObjectAR.getString("ORDERS"));
				wishlist_myaccount.setText(jsonObjectAR.getString("WISHLIST"));
				personalinfo_myaccount.setText(jsonObjectAR.getString("PERSONAL INFO"));
				address_myaccount.setText(jsonObjectAR.getString("ADDRESSES"));
				loyality_myaccount.setText(jsonObjectAR.getString("LOYALITY POINTS"));


				orders_des_myaccount.setText(jsonObjectAR.getString("VIEW YOUR ORDER INFO"));
				wishlist_des_myaccount.setText(jsonObjectAR.getString("VIEW AND MODIFY ITEMS ON YOUR WISHLIST"));
				personalinfo_des_myaccount.setText(jsonObjectAR.getString("PERSONAL INFO"));
				address_des_myaccount.setText(jsonObjectAR.getString("ADDRESSES"));
				loyality_des_myaccount.setText(jsonObjectAR.getString("LOYALITY POINTS"));

			}

		}
		catch (JSONException j){j.printStackTrace();}


		points_ll = (LinearLayout) view.findViewById(R.id.points_ll);
		usrinfo_ll = (LinearLayout) view.findViewById(R.id.usrinfo_ll);
		address_ll = (LinearLayout) view.findViewById(R.id.address_ll);
		mywishlist_ll = (LinearLayout) view.findViewById(R.id.mywishlist_ll);
		myorders_ll = (LinearLayout) view.findViewById(R.id.myorders_ll);


		address_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//((HomeActivity)getActivity()).addAddressFragment();
				((HomeActivity)getActivity()).myAddressFragment();
			}
		});mywishlist_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).myWishList();
			}
		});

		myorders_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getContext(),MyOrdersActivity.class);
				startActivity(intent);
			}
		});

		usrinfo_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).myProfileFragment();
			}
		});





		return view;
	}
	public static AccountFragment newInstance(int someInt) {
		AccountFragment myFragment = new AccountFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}
}
