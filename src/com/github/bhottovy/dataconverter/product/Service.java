package com.github.bhottovy.dataconverter.product;

import java.time.temporal.ChronoUnit;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("service")
public class Service extends Product {

	//Constants
	private static final double TAX_RATE = 0.0425;
	private static final ProductType PRODUCT_TYPE = ProductType.PRODUCT_TYPE_SERVICE;
	
	//Services have an initial activation fee, and an annual fee.
	private double activationFee;
	private double annualFee;
	
	public Service(String productCode, String productName, double activationFee, double annualFee) {
		super(productCode, productName, PRODUCT_TYPE);
		
		this.activationFee = activationFee;
		this.annualFee = annualFee;
	}
	
	public double getActivationFee() {
		return this.activationFee;
	}
	
	public double getAnnualFee() {
		return this.annualFee;
	}

	@Override
	public double getTaxes(double taxedAmount) {
		return taxedAmount * TAX_RATE;
	}
	
	public static int getDays(DateTime startDate, DateTime endDate) {
		return Days.daysBetween(startDate, endDate).getDays();
	}
}
