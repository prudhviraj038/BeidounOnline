package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
	TextView page_title;
	TextView btn_edit,state;
	LinearLayout prdcheckout_btn;
	LinearLayout menu_btn,back_btn,submit_btn,proceedtopay_ll_btn;
	LinearLayout popup_checkout;

	ArrayList<StatesData> statesData = new ArrayList<StatesData>();

	StatesAdapter statesAdapter;

	EditText firstname,lastname,address,email,phone,city,pincode;
	ListView states_lv;

	String member,name;

	ImageView back;
	@Override
	public void onBackPressed() {
		super.onBackPressed();

		//Intent intent = new Intent(CheckoutActivity.this,HomeActivity.class);
		// startActivity(intent);
		finish();
	}
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_shipping);



	}

}
