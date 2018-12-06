package com.yellowsoft.newproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;


import java.util.ArrayList;

public class TrackFragment extends Fragment {
	ViewFlipper mViewflipper;
	RelativeLayout publictab,personaltab;
	LinearLayout tabone,tabtwo,tabindicator_one,tabindicator_two,submit,vehicle_details,transparent;

	TextView tv_public,tv_personal,trackingtextthree,tracksolid_url;
	TextView noresults_tv,howto_tv,trackingtextone,trackingtexttwo;

	ImageView playstore,appstore;

	WebView webView;

	RecyclerView track_recycler;
	ArrayList<AddressChechout_Data> trackData = new ArrayList<AddressChechout_Data>();
	CheckoutAddress_Adapter track_adapter;



	EditText vechinumber;
	public static TrackFragment newInstance(int someInt) {
		TrackFragment myFragment = new TrackFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tracking, container, false);







		return view;
	}





}
