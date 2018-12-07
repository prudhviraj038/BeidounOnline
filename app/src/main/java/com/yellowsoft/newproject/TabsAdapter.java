package com.yellowsoft.newproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by mac on 3/18/17.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

   public   ShopFragment shopFragment;
   public   ProductFragment productFragment;
   public   HomeFragment homeFragment;
   public   CartFragment cartFragment;
   public  AddAddressFragment addAddressFragment;


    public TabsAdapter(FragmentManager fm, HomeActivity activity) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {


    switch (position){


        case 0:{
            if (homeFragment ==null){
                 homeFragment = HomeFragment.newInstance(position);
            }


            return homeFragment;

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
            if (cartFragment==null){
                 cartFragment = CartFragment.newInstance(position);
            }


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
            if (productFragment==null){
                 productFragment = ProductFragment.newInstance(position);
            }

            return productFragment;
        }

        case 7:{

            WhishListFragment whishListFragment = WhishListFragment.newInstance(position);
            return whishListFragment;

        }

        case 8:{

            SignupFragment signupFragment = SignupFragment.newInstance(position);
            return signupFragment;
        }

        case 9:{
            AccountFragment accountFragment = AccountFragment.newInstance(position);

            return accountFragment;
        }

        case 10:{
            ShippingAddressFragment shippingAddressFragment = ShippingAddressFragment.newInstance(position);
            return shippingAddressFragment;
        }

        case 11:{
            if (addAddressFragment==null){
                 addAddressFragment = AddAddressFragment.newInstance(position);
            }


            return addAddressFragment;
        }

        case 12 :{
            MyProfileFragment myProfileFragment = MyProfileFragment.newInstance(position);

            return myProfileFragment;
        }

        case 13:{
            CheckoutAddressFragment checkoutAddressFragment = CheckoutAddressFragment.newInstance(position);

            return checkoutAddressFragment;
        }

        case 14:{
            MyAddressFragment myAddressFragment = MyAddressFragment.newInstance(position);
            return myAddressFragment;
        }


        default: {
            HomeFragment demoFragment = HomeFragment.newInstance(position);

            return demoFragment;
        }


    }

    }

    @Override
    public int getCount() {
        return 15;
    }
}
