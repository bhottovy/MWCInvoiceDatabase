package com.github.bhottovy.dataconverter.product;

public abstract class Product implements SellableProduct {

	private String productCode;
	private String productName;
	
	private ProductType type;
	
	public Product(String productCode, String productName, ProductType type) {
		this.productCode = productCode;
		this.productName = productName;
		this.type = type;
	}
	
	public final String getCode() {
		return this.productCode;
	}
	
	public final String getName() {
		return this.productName;
	}
	
	public ProductType getType() {
		return this.type;
	}
}
