package com.yellowsoft.newproject;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

/**
 * Created by sriven on 6/1/2018.
 */

public class SlidingImageAdapter extends PagerAdapter{
ArrayList<SlidingImage_Data> images;
Context context;

	public SlidingImageAdapter(Context context, ArrayList<SlidingImage_Data> images){
	this.context=context;
	this.images=images;
	}
	@Override
	public int getCount(){
	return images.size();
	}
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	@Override
	public Object instantiateItem(final ViewGroup container, final int position) {
		do {
			context = this.context; //Using just "this" doesn't work either.
		} while (context==null);

		//	context = SlidingPageAdapter.this.context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.slidingviewpager_item, container,false);

		final ImageView imageView = (ImageView)view.findViewById(R.id.viewpagerimage_item);


		//	textView.setText(colorName.get(position));

		Picasso.get().load(images.get(position).image_url).into(imageView);

		Log.e("sliderimage",""+images.get(position).image_url);

		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,ShopActivity.class);
				intent.putExtra(images.get(position).type,images.get(position).type_id);
				Log.e("typeSlide",""+images.get(position).type);
				context.startActivity(intent);
			}
		});

		//	linearLayout.setBackgroundColor(color.get(position));

		ViewPager viewPager = (ViewPager) container;
		viewPager.addView(view, 0);

		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		ViewPager viewPager = (ViewPager) container;
		View view = (View) object;
		viewPager.removeView(view);
	}

}
