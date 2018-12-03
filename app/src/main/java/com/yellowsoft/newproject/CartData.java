package com.yellowsoft.newproject;

import java.io.Serializable;

public class CartData extends Object implements Serializable{
	String title,price,id,quantity;
	ProductsData productsData;
	int qty;
	public CartData(String title,String price,String id,String quantity){
		this.title=title;
		this.price = price;
		this.id=id;
		this.quantity = quantity;

	}

	public CartData(ProductsData productsData,int qty){


		this.productsData = productsData;
		this.id = productsData.product_id;
		this.price = productsData.discountprice;
		this.title = productsData.product_title;
		this.qty = qty;
	}


}
