package com.github.bhottovy.dataconverter.product;

public interface SellableProduct {

	//Used to link SoldProducts and Products, with the product's type. All products have a calculateable total.
	public static enum ProductType {
		PRODUCT_TYPE_SERVICE, PRODUCT_TYPE_EQUIPMENT, PRODUCT_TYPE_CONSULTATION	
	}
	
	public double getTaxes(double taxedAmount);
}
