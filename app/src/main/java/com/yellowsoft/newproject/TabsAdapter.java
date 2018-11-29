package com.yellowsoft.newproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by mac on 3/18/17.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

   public   ShopFragment shopFragment;


    public TabsAdapter(FragmentManager fm, HomeActivity activity) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {


    switch (position){


        case 0:{
            HomeFragment demoFragment = HomeFragment.newInstance(position);

            return demoFragment;

        }
        case 1:{
            if (shopFragment==null) {
                 shopFragment = ShopFragment.newInstance(position);
            }

            return shopFragment;
        }

        case 2:{
            BrandsFragment productsFragment = BrandsFragment.newInstance(position);

            return  productsFragment;

        }

        case 3:{
            CartFragment cartFragment = CartFragment.newInstance(position);
            return cartFragment;
        }

        case 4:{
            CategoriesFragment schemeFragment = CategoriesFragment.newInstance(position);
            return schemeFragment;


        }


        case 5:{
            MyAccountFragment myAccountFragment = MyAccountFragment.newInstance(position);
            return myAccountFragment;


        }
        case 6:{
            ProductFragment productFragment = ProductFragment.newInstance(position);
            return productFragment;
        }

        default: {
            HomeFragment demoFragment = HomeFragment.newInstance(position);

            return demoFragment;
        }


    }

    }

    @Override
    public int getCount() {
        return 7;
    }
}
