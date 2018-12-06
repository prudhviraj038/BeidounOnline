package com.yellowsoft.newproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class AccountFragment extends Fragment {


	LinearLayout points_ll,usrinfo_ll,address_ll,mywishlist_ll,myorders_ll;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_account, container, false);


		points_ll = (LinearLayout) view.findViewById(R.id.points_ll);
		usrinfo_ll = (LinearLayout) view.findViewById(R.id.usrinfo_ll);
		address_ll = (LinearLayout) view.findViewById(R.id.address_ll);
		mywishlist_ll = (LinearLayout) view.findViewById(R.id.mywishlist_ll);
		myorders_ll = (LinearLayout) view.findViewById(R.id.myorders_ll);


		address_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).addAddressFragment();
			}
		});mywishlist_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).myWishList();
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
