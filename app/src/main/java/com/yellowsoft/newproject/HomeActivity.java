package com.yellowsoft.newproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

	ListView lv_menu;
	TextView btn_edit,logout,usr_name;
	TextView membercode;
	private static int SPLASH_TIME_OUT = 3000;


	TextView home_tv,bag_tv,brands_tv,categores_tv,account_tv;

	String playstorelink;
	private GoogleSignInClient mGoogleSignInClient;
	private FirebaseAuth mAuth;



	LinearLayout menu_btn,home,brands,bag,categories,account;
	LinearLayout ll_userdetails,orders_ll_toolbar;

	private DrawerLayout mDrawerLayout;
	LinearLayout ll_login_,ll_details_,login_btn,signup_btn;;
	TabsAdapter tabsAdapter;
	CustomViewPager mViewPager;

	ImageView home_img,brands_img,bag_img,categories_img,btn_back;
	ImageView account_img,btn_like;
	ImageView countries_img,search_img;

	ListView countries_lv;
	ArrayList<CountryData> countriesdata = new ArrayList<>();

	Countries_Adapter countries_adapter;
	LinearLayout countries_popup_ll,search_popup;

	RelativeLayout toolbar_content;


	Shop_Data shop_data;
	TextView usr_name_tv;



/*	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (mViewPager.getCurrentItem()!=0){
			mViewPager.setCurrentItem(0);
		}
		//finish();

	}*/

	@Override
	protected void onDestroy() {
		super.onDestroy();

		ApplicationController.getInstance().saveCart();
		ApplicationController.getInstance().saveWishlist();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		ll_userdetails = (LinearLayout)findViewById(R.id.ll_userdetails);




		usr_name_tv = (TextView)findViewById(R.id.usr_name);
		usr_name_tv.setText(Session.getUserName(HomeActivity.this));



		lv_menu = (ListView)findViewById(R.id.lv_menu);



		home_tv = (TextView)findViewById(R.id.home_tv);
		brands_tv = (TextView)findViewById(R.id.brands_tv);
		bag_tv = (TextView)findViewById(R.id.bag_tv);
		categores_tv = (TextView)findViewById(R.id.categories_tv);
		account_tv = (TextView)findViewById(R.id.account_tv);

		JSONObject jsonObjectAR = ApplicationController.getInstance().wordsAR;
		JSONObject jsonObjectEN = ApplicationController.getInstance().wordsEN;

		try {
			if (Session.getLanguage(HomeActivity.this).equals("0")) {
				home_tv.setText(jsonObjectEN.getString("HOME"));
				brands_tv.setText(jsonObjectEN.getString("BRANDS"));
				bag_tv.setText(jsonObjectEN.getString("BAG"));
				categores_tv.setText(jsonObjectEN.getString("CATEGORIES"));
				account_tv.setText(jsonObjectEN.getString("ACCOUNT"));

			} else {

				home_tv.setText(jsonObjectAR.getString("HOME"));
				brands_tv.setText(jsonObjectAR.getString("BRANDS"));
				bag_tv.setText(jsonObjectAR.getString("BAG"));
				categores_tv.setText(jsonObjectAR.getString("CATEGORIES"));
				account_tv.setText(jsonObjectAR.getString("ACCOUNT"));
			}
		}
		catch (JSONException j){

		}


		countries_popup_ll = (LinearLayout)findViewById(R.id.countries_popup_ll);
		countries_popup_ll.setVisibility(View.GONE);

		countries_lv = (ListView)findViewById(R.id.countries_lv);
		countries_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

				String img = countriesdata.get(position).image;
				String code = countriesdata.get(position).code;
				String rate = countriesdata.get(position).rate;

				Log.e("currency","rate = "+rate+", code = "+code+", img = "+img);

				Session.setCurrency(HomeActivity.this,code,rate,img);


				setCountries(img,rate,code);
			}
		});




		// [START initialize_auth]
		mAuth = FirebaseAuth.getInstance();
		// [END initialize_auth]


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setupActionBar();
		setupHeader();
		tabsAdapter = new TabsAdapter(getSupportFragmentManager(),this);
		mViewPager = (CustomViewPager) findViewById(R.id.home_viewpager);
		mViewPager.setOffscreenPageLimit(0);

		mViewPager.setAdapter(tabsAdapter);


		home_img = (ImageView)findViewById(R.id.home_img);
		brands_img = (ImageView)findViewById(R.id.brands_img);
		bag_img = (ImageView)findViewById(R.id.bag_img);
		categories_img = (ImageView)findViewById(R.id.categories_img);
		account_img = (ImageView)findViewById(R.id.account_img);


		home = (LinearLayout)findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetAllColors();
				changebg(home_tv,home_img);
				mViewPager.setCurrentItem(0);

				//orders_ll_toolbar.setVisibility(View.INVISIBLE);


			}
		});

		if (mViewPager.getCurrentItem()==0){
			changebg(home_tv,home_img);

		}


		brands = (LinearLayout)findViewById(R.id.brands);
		brands.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetAllColors();
				changebg(brands_tv,brands_img);
				mViewPager.setCurrentItem(2);


				//lorders_ll_toolbar.setVisibility(View.INVISIBLE);
			}
		});


		bag = (LinearLayout)findViewById(R.id.bag);
		bag.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetAllColors();
				changebg(bag_tv,bag_img);
				mViewPager.setCurrentItem(3);


				//orders_ll_toolbar.setVisibility(View.VISIBLE);
			}
		});


		categories = (LinearLayout)findViewById(R.id.categories);
		categories.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//orders_ll_toolbar.setVisibility(View.INVISIBLE);



					//((HomeActivity)getActivity()).schemeSelected();
					mViewPager.setCurrentItem(4);




					resetAllColors();
					changebg(categores_tv,categories_img);




			}
		});


		account = (LinearLayout)findViewById(R.id.account);
		account.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//orders_ll_toolbar.setVisibility(View.INVISIBLE);


					resetAllColors();
					changebg(account_tv, account_img);
					if (Session.getUserid(HomeActivity.this).equals("0")) {
						mViewPager.setCurrentItem(5);
					}
					else {
						mViewPager.setCurrentItem(9);
					/*	menu_btn.setVisibility(View.GONE);
						btn_back.setVisibility(View.VISIBLE);*/
					}






			}
		});







		mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_menu);

		ArrayList<MenuItem> menuItems = new ArrayList<>();




		if (Session.getUserid(HomeActivity.this).equals("0")){
			ll_userdetails.setVisibility(View.GONE);

			if (Session.getLanguage(HomeActivity.this).equals("0")) {
				menuItems.add(new MenuItem("Home", "", R.drawable.home));
				menuItems.add(new MenuItem("Change Language", "", R.drawable.change));
				menuItems.add(new MenuItem("Offers", "", R.drawable.offers));
				menuItems.add(new MenuItem("Customer Care", "", R.drawable.contact));
				menuItems.add(new MenuItem("About", "", R.drawable.about));
			}
			else {

				try {
					menuItems.add(new MenuItem(jsonObjectAR.getString("Home"), "", R.drawable.home));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Change Language"), "", R.drawable.change));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Offers"), "", R.drawable.offers));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Customer Care"), "", R.drawable.contact));
					menuItems.add(new MenuItem(jsonObjectAR.getString("About"), "", R.drawable.about));
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}

		else {

			ll_userdetails.setVisibility(View.VISIBLE);
			if (Session.getLanguage(HomeActivity.this).equals("0")) {
				menuItems.add(new MenuItem("Home", "", R.drawable.home));
				menuItems.add(new MenuItem("My Addresses", "", R.drawable.user1));
				menuItems.add(new MenuItem("Past Orders", "", R.drawable.orderbox));
				menuItems.add(new MenuItem("Change Language", "", R.drawable.change));
				menuItems.add(new MenuItem("Offers", "", R.drawable.offers));
				menuItems.add(new MenuItem("Customer Care", "", R.drawable.contact));
				menuItems.add(new MenuItem("About", "", R.drawable.about));
				menuItems.add(new MenuItem("Log Out", "", R.drawable.logout));
			}
			else {
				try {
					menuItems.add(new MenuItem(jsonObjectAR.getString("Home"), "", R.drawable.home));
					menuItems.add(new MenuItem(jsonObjectAR.getString("My Addresses"), "", R.drawable.user1));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Past Orders"), "", R.drawable.orderbox));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Change Language"), "", R.drawable.change));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Offers"), "", R.drawable.offers));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Customer Care"), "", R.drawable.contact));
					menuItems.add(new MenuItem(jsonObjectAR.getString("About"), "", R.drawable.about));
					menuItems.add(new MenuItem(jsonObjectAR.getString("Log Out"), "", R.drawable.logout));
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}






		MenuAdapter menuAdapter = new MenuAdapter(this,menuItems);
		lv_menu.setAdapter(menuAdapter);

		//menu one onclick
		lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position==0){
					mDrawerLayout.closeDrawer(GravityCompat.START);

					if (Session.getUserid(HomeActivity.this).equals("0")){
						mViewPager.setCurrentItem(0);
					}
					else {
						mViewPager.setCurrentItem(0);

					}

				}
				else if (position==1){

					mDrawerLayout.closeDrawer(GravityCompat.START);
					if (Session.getUserid(HomeActivity.this).equals("0")){
						mViewPager.setCurrentItem(5);
						//changelanguage

						if (Session.getLanguage(HomeActivity.this).equals("0")) {
							Session.setLanguage(HomeActivity.this, "1");
							Intent intent = new Intent(HomeActivity.this,HomeActivity.class);

							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

							startActivity(intent);
						}
						else
						{
							Session.setLanguage(HomeActivity.this,"0");
							Intent intent = new Intent(HomeActivity.this,HomeActivity.class);

							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

							startActivity(intent);
						}
					}
					else {
						//mViewPager.setCurrentItem(9);
						mViewPager.setCurrentItem(14);


					}


				}
				else if (position==2){
					mDrawerLayout.closeDrawer(GravityCompat.START);


					if (Session.getUserid(HomeActivity.this).equals("0")){

					    //offers

					}
					else {

                        Intent intent = new Intent(HomeActivity.this,MyOrdersActivity.class);
                        startActivity(intent);

					}

				}
				else if (position==3){

                    mDrawerLayout.closeDrawer(GravityCompat.START);
					if (Session.getUserid(HomeActivity.this).equals("0")){

						Intent intent = new Intent(HomeActivity.this,ContactusActivity.class);
						startActivity(intent);
					}

					else {

						Log.e("changeLang","language change");

                        if (Session.getLanguage(HomeActivity.this).equals("0")) {
                            Session.setLanguage(HomeActivity.this, "1");
                            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                        }
                        else
                        {
                            Session.setLanguage(HomeActivity.this,"0");
                            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                        }
					}

				}

				else if (position==4){
					if (Session.getUserid(HomeActivity.this).equals("0")){

						//aboutus
						mDrawerLayout.closeDrawer(GravityCompat.START);
						Intent intent = new Intent(HomeActivity.this,AboutusActivity.class);
						startActivity(intent);


					}else {//offers


					}
				}



				else if (position==5){

					mDrawerLayout.closeDrawer(GravityCompat.START);
					Intent intent = new Intent(HomeActivity.this,ContactusActivity.class);
					startActivity(intent);

				}

				else if (position==6){
					mDrawerLayout.closeDrawer(GravityCompat.START);
					Intent intent = new Intent(HomeActivity.this,AboutusActivity.class);
					startActivity(intent);
				}


				else if (position==7)
				{mDrawerLayout.closeDrawer(GravityCompat.START);


				Session.setUserid(HomeActivity.this,"0","0");

					Intent intent = new Intent(HomeActivity.this,HomeActivity.class);

					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

					startActivity(intent);


				}
				else {mDrawerLayout.closeDrawer(GravityCompat.START);


				}
			}
		});

		if (getIntent().hasExtra("openProduct")){
		/*	resetAllColors();
			changebg(bag_tv,shop_img);
			mViewPager.setCurrentItem(2);

			orders_ll_toolbar.setVisibility(View.VISIBLE);*/


		mViewPager.setCurrentItem(6);


		shop_data = (Shop_Data)getIntent().getSerializableExtra("productDetails");
		//	Log.e("dataShop",getIntent().getStringExtra("productDetails"));
			tabsAdapter.productFragment.productDetails(shop_data);
		}
		else if (getIntent().hasExtra("sendtoCart")){
			sendtoCart();
		}






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
		View v = inflater.inflate(R.layout.action_bar_title,null);


		toolbar_content = (RelativeLayout)v.findViewById(R.id.toolbar_content);








       /* orders_ll_toolbar = (LinearLayout)v.findViewById(R.id.orders_ll_toolbar);
		orders_ll_toolbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,MyOrdersActivity.class);
				startActivity(intent);
			}
		});
*/






		btn_like = (ImageView)v.findViewById(R.id.btn_like);

		btn_like.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(7);
			}
		});

		btn_back = (ImageView)v.findViewById(R.id.btn_back_title);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mViewPager.getCurrentItem()!=0){
					int page= mViewPager.getCurrentItem();
					mViewPager.setCurrentItem(0);
					btn_back.setVisibility(View.GONE);
					menu_btn.setVisibility(View.VISIBLE);
				}
			}
		});




		countries_img = (ImageView)v.findViewById(R.id.countries_img);
		Picasso.get().load(Session.getCurrencyImage(HomeActivity.this)).into(countries_img);
		countries_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (countries_popup_ll.getVisibility() == View.VISIBLE) {
					countries_popup_ll.setVisibility(View.GONE);
				} else {
					countriesdata.clear();
					getCountriesList();
					countries_popup_ll.setVisibility(View.VISIBLE);
				}
			}
		});





		search_img = (ImageView)v.findViewById(R.id.search_img);
		search_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			  //  tabsAdapter.shopFragment.search();
			    mViewPager.setCurrentItem(1);
			}
		});




		menu_btn = (LinearLayout) v.findViewById(R.id.btn_menu_container);
		menu_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mDrawerLayout.openDrawer(GravityCompat.START);
			}
		});


		getSupportActionBar().setCustomView(v, layoutParams);
		Toolbar parent = (Toolbar) v.getParent();

		parent.setContentInsetsAbsolute(0, 0);


	}




	private void setupHeader(){

	}











	public  void changebg(TextView l,ImageView i){


		l.setTextColor(getResources().getColor(R.color.buttonColor));
		i.setColorFilter(home_img.getContext().getResources().getColor(R.color.buttonColor), PorterDuff.Mode.SRC_ATOP);

	}


	public void resetAllColors(){


		home_tv.setTextColor(getResources().getColor(R.color.colorBlack));

		home_img.setColorFilter(getResources().getColor(R.color.colorBlack));


		brands_tv.setTextColor(getResources().getColor(R.color.colorBlack));
		brands_img.setColorFilter(getResources().getColor(R.color.colorBlack));


		bag_tv.setTextColor(getResources().getColor(R.color.colorBlack));
		bag_img.setColorFilter(getResources().getColor(R.color.colorBlack));


		categores_tv.setTextColor(getResources().getColor(R.color.colorBlack));
		categories_img.setColorFilter(getResources().getColor(R.color.colorBlack));


		account_tv.setTextColor(getResources().getColor(R.color.colorBlack));
		account_img.setColorFilter(getResources().getColor(R.color.colorBlack));

	}


	/*public void insta_shop(String id,String title){

		mViewPager.setCurrentItem(1);
		tabsAdapter.shopFragment.setParamentersBrands(id,title);
		tabsAdapter.shopFragment.setParamentersCategories(id,title);

		menu_btn.setVisibility(View.GONE);
		btn_back.setVisibility(View.VISIBLE);

	}
*/



	public void sendtoCart(){
	//	ApplicationController.getInstance().quatity = quantity;
		resetAllColors();
		changebg(bag_tv,bag_img);
		mViewPager.setCurrentItem(3);
	}








	public void openHome(){
		resetAllColors();
		changebg(home_tv,home_img);
		mViewPager.setCurrentItem(0);
	}

	public void getproductDetails(Shop_Data data){

		mViewPager.setCurrentItem(6);

		tabsAdapter.productFragment.productDetails(data);

	}

	public void proceedtoCheckout(){
	    mViewPager.setCurrentItem(10);
    }




	public void signupFragment(){

		mViewPager.setCurrentItem(8);


	}


	public void accountFragment(){

		if (Session.getUserid(HomeActivity.this).equals("0")){

			mViewPager.setCurrentItem(5);

		}
		else {
			mViewPager.setCurrentItem(9);


		}


	}

	public void knowSignedIn(){
		mViewPager.setCurrentItem(5);
	}



	public void setCountries(String img,String rate,String code){


		Picasso.get().load(img).into(countries_img);


		Intent intent = new Intent(HomeActivity.this,HomeActivity.class);

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		startActivity(intent);
	}






	public void addAddressFragment(String type,String id,AddressChechout_Data data){

		resetAllColors();

		mViewPager.setCurrentItem(11);



		tabsAdapter.addAddressFragment.addAddressId(type,id,data);
	}

	public void selectAddressFragment(){

		mViewPager.setCurrentItem(13);
	}

	public void myAddressFragment(){
		mViewPager.setCurrentItem(14);
	}



	public void myWishList(){

		resetAllColors();
		mViewPager.setCurrentItem(7);


	}






	public void myProfileFragment(){

		mViewPager.setCurrentItem(12);
	}



	//google signout
	public void signout(){
		Session.setUserid(HomeActivity.this,"0","MYCOP USER");
		Session.setMemberCode(HomeActivity.this,"0");

	}




/*
int resume_count = 0;
	@Override
	public void onResume(){
		super.onResume();
		// put your code here...
		if(resume_count>0){

			if(mViewPager.getCurrentItem()==3)


			if(Session.getUserid(HomeActivity.this).equals("0")) {

				Log.e("memberid","0");

			}
			else {



			}
			if (mViewPager.getCurrentItem()==2){

			}


		}

		resume_count++;


	}
*/


	/*@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.e("buygpsclicke","act");

		if(requestCode==888){

			if(resultCode==RESULT_OK){


				//shop_btn.performClick();
				Log.e("buygpsclicke","frag");

				try {

					shop_btn.performClick();
				}catch (Exception ex){
					ex.printStackTrace();
				}

			}
		}

	}*/

	public void showAlert(final String message,final String title){
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
		alertDialog.setMessage(Html.fromHtml(message));
		alertDialog.setTitle(title);
		alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.setCancelable(true);
			}
		});
		alertDialog.show();
	}

	public void getCountriesList(){

		final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
		progressDialog.setMessage("Please Wait....");
		progressDialog.show();
		progressDialog.setCancelable(false);
		String URL = Session.BASE_URL+"api/countries.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("res",response);
				if(progressDialog!=null) {
					progressDialog.dismiss();
				}
				countries_popup_ll.setVisibility(View.VISIBLE);
				try {
					JSONArray jsonArray =new JSONArray(response);
					for (int i = 0;i<jsonArray.length();i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						CountryData temp = new CountryData(jsonObject);
						countriesdata.add(temp);

					}
					countries_adapter = new Countries_Adapter(HomeActivity.this,countriesdata);

					countries_lv.setAdapter(countries_adapter);
					countries_adapter.notifyDataSetChanged();


				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(progressDialog!=null)
							progressDialog.dismiss();

					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> parameters = new HashMap<String, String>();

				return parameters;
			}
		};
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}

}
