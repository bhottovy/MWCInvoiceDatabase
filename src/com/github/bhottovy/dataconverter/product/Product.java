package com.github.bhottovy.dataconverter.product;

public abstract class Product {

	private String productCode;
	private String productName;
	
	public Product(String productCode, String productName) {
		this.productCode = productCode;
		this.productName = productName;
	}
	
	public final String getCode() {
		return this.productCode;
	}
	
	public final String getName() {
		return this.productName;
	}
}
