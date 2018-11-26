package com.yellowsoft.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

public class SchemeSuccessActivity extends AppCompatActivity {
	LinearLayout backtohome_ll_btn,codeshare_ll;
	TextView order_number_tv,success_ref_membercode;
	String playstorelink;

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
		finish();

	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schemethankyou);

		success_ref_membercode = (TextView)findViewById(R.id.success_ref_membercode);

		success_ref_membercode.setText(Session.getMemberCode(SchemeSuccessActivity.this));


		order_number_tv = (TextView) findViewById(R.id.successreferal_trans_id);

		order_number_tv.setText(getIntent().getStringExtra("paymentid"));


		try {
			playstorelink = ApplicationController.getInstance().settings.getString("playstore");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		codeshare_ll = (LinearLayout)findViewById(R.id.codeshare_ll);
		codeshare_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String shareBody = "Hi, I am "+Session.getUserName(SchemeSuccessActivity.this)+", join me on My Cop App and register in their Purchase Advance Scheme to get your Referral Code. Enter my code ("+ Session.getMemberCode(SchemeSuccessActivity.this)+") before making the payment. You can earn Rs. 1000/- for every successful referral. "+playstorelink;
				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share with");
				startActivity(sharingIntent);
			}
		});

		backtohome_ll_btn = (LinearLayout)findViewById(R.id.backtohome_ll_btn_scheme);
		backtohome_ll_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(intent);
				finish();


				}
		});

	}
}
