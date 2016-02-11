package com.github.bhottovy.dataconverter.product;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("equipment")
public class Equipment extends Product {

	//Equipment has a flat price per product.
	private double unitPrice;
	
	public Equipment(String productCode, String productName, double unitPrice) {
		super(productCode, productName);
		
		this.unitPrice = unitPrice;
	}
	
	public double getUnitPrice() {
		return this.unitPrice;
	}
}
