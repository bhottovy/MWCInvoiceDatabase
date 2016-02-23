package com.github.bhottovy.dataconverter.product;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("equipment")
public class Equipment extends Product {

	//Constants
	private static final double TAX_RATE = 0.07;
	private static final ProductType PRODUCT_TYPE = ProductType.PRODUCT_TYPE_EQUIPMENT;
	
	//Equipment has a flat price per product.
	private double unitPrice;
	
	public Equipment(String productCode, String productName, double unitPrice) {
		super(productCode, productName, PRODUCT_TYPE);
		
		this.unitPrice = unitPrice;
	}
	
	public double getUnitPrice() {
		return this.unitPrice;
	}
	
	@Override
	public double getTaxes(double taxedAmount) {
		return taxedAmount * TAX_RATE;
	}
}
