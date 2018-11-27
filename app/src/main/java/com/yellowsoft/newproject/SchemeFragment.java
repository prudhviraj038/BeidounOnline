package com.yellowsoft.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.Login;

import org.json.JSONException;

import java.util.ArrayList;

public class SchemeFragment extends Fragment {

	RecyclerView categories_rv;
	Categories_Adapter categories_adapter;
	ArrayList<Home_data> home_data = new ArrayList<>();


	@Override
	public void onStart() {
		super.onStart();
		String memberid = Session.getUserid(getActivity());
		String membercode = Session.getMemberCode(getActivity());



	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_scheme, container, false);
		Log.e("schemefragment","schemefragment");

		categories_rv = (RecyclerView)view.findViewById(R.id.categories_rv);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		categories_rv.setLayoutManager(linearLayoutManager);

		home_data.add(new Home_data(R.drawable.categories));
		home_data.add(new Home_data(R.drawable.c2));
		home_data.add(new Home_data(R.drawable.c3));
		home_data.add(new Home_data(R.drawable.c2));
		home_data.add(new Home_data(R.drawable.c3));
		home_data.add(new Home_data(R.drawable.c2));
		home_data.add(new Home_data(R.drawable.c3));


		categories_adapter = new Categories_Adapter(getContext(),home_data);
		categories_rv.setAdapter(categories_adapter);



		return view;
	}
	public static SchemeFragment newInstance(int someInt) {
		SchemeFragment myFragment = new SchemeFragment();

		Bundle args = new Bundle();
		args.putInt("someInt", someInt);
		myFragment.setArguments(args);

		return myFragment;
	}


}
