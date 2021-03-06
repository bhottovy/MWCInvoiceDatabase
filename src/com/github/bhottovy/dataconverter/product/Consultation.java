package com.github.bhottovy.dataconverter.product;

import com.github.bhottovy.dataconverter.person.Person;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("consultation")
public class Consultation extends Product {

	//Constants
	private static final double TAX_RATE = 0.0425;
	private static final ProductType PRODUCT_TYPE = ProductType.PRODUCT_TYPE_CONSULTATION;
	
	//Consultations have an hourly cost, and a flat $150 service fee.
	private static double SERVICE_FEE = 150.00;
	private double hourlyCost;
	
	//Consultations also have an associated consultant.
	Person consultant;
	
	public Consultation(String productCode, String productName, double hourlyCost, Person consultant) {
		super(productCode, productName, PRODUCT_TYPE);
		
		this.hourlyCost = hourlyCost;
		this.consultant = consultant;
	}
	
	public double getServiceFee() {
		return SERVICE_FEE;
	}
	
	public double getHourlyCost() {
		return this.hourlyCost;
	}

	public Person getConsultant() {
		return this.consultant;
	}

	@Override
	public double getTaxes(double taxedAmount) {
		return taxedAmount * TAX_RATE;
	}
}
