package com.github.bhottovy.dataconverter.product;

public interface SellableProduct {

	public static enum ProductType {
		PRODUCT_TYPE_SERVICE, PRODUCT_TYPE_EQUIPMENT, PRODUCT_TYPE_CONSULTATION	
	}
	
	public double getTaxes(double taxedAmount);
}
