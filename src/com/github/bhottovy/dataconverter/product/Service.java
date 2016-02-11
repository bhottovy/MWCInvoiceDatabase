package com.github.bhottovy.dataconverter.product;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("service")
public class Service extends Product {

	//Services have an initial activation fee, and an annual fee.
	private double activationFee;
	private double annualFee;
	
	public Service(String productCode, String productName, double activationFee, double annualFee) {
		super(productCode, productName);
		
		this.activationFee = activationFee;
		this.annualFee = annualFee;
	}
	
	public double getActivationFee() {
		return this.activationFee;
	}
	
	public double getAnnualFee() {
		return this.annualFee;
	}
}
