package com.github.bhottovy.dataconverter.product;

import org.joda.time.DateTime;

public class SoldProduct implements SellableProduct {
	
	//Equipment Data
	private int unitsSold;
	
	//Service Data
	private DateTime startDate;
	private DateTime endDate;
	
	//Consultation Data
	private int hours;
	
	//Totals
	private double subTotal;
	private double taxes;
	private double fees;
	
	private Product product;
	private ProductType type;
	
	//Main method and overloading method, depending on type of Product.
	public SoldProduct(Product product, int amount, String startDate, String endDate) {
		this.product = product;
		this.type = this.product.getType();
		
		int unitsSold = 0;
		int hours = 0;
		if(product.getType() == ProductType.PRODUCT_TYPE_EQUIPMENT) unitsSold = amount;
		if(product.getType() == ProductType.PRODUCT_TYPE_CONSULTATION) hours = amount;
		
		if(startDate != null && endDate != null) {
			this.startDate = DateTime.parse(startDate);
			this.endDate = DateTime.parse(endDate);
		}
		this.unitsSold = unitsSold;
		this.hours = hours;
		
		this.taxes = 0;
		this.subTotal = 0;
		this.fees = 0;
		
		this.calcTotals();
	}
	
	public SoldProduct(Product product, int amount) { //Equipment & Consultations
		this(product, amount, null, null);
	}
	
	public SoldProduct(Product product, String startDate, String endDate) { //Services
		this(product, 0, startDate, endDate);
	}

	public void calcTotals() {
		
		switch(type) {
			case PRODUCT_TYPE_EQUIPMENT:
				Equipment equipment = (Equipment)this.product;
				this.subTotal = equipment.getUnitPrice() * this.unitsSold;
				this.taxes = equipment.getTaxes(this.subTotal);
				System.out.println(subTotal+" "+taxes);
				break;
			case PRODUCT_TYPE_SERVICE:
				Service service = (Service)this.product;
				int days = Service.getDays(startDate, endDate);
				this.subTotal = (service.getAnnualFee() / 365.0) * days;
				this.taxes = service.getTaxes(this.subTotal);
				this.fees = service.getActivationFee();
				break;
			case PRODUCT_TYPE_CONSULTATION:
				Consultation consultation = (Consultation)this.product;
				this.subTotal = consultation.getHourlyCost() * this.hours;
				this.taxes = consultation.getTaxes(this.subTotal);
				this.fees = consultation.getServiceFee();
				break;
		}
	}
	
	@Override
	public double getTaxes(double taxedAmount) {
		return this.product.getTaxes(taxedAmount);
	}
	
	public String getStartDate() {
		return this.startDate.toString();
	}
	
	public String getEndDate() {
		return this.endDate.toString();
	}
	
	public double getSubTotal() {
		return this.subTotal;
	}
	
	public double getTaxTotal() {
		return this.taxes;
	}
	
	public double getFees() {
		return this.fees;
	}
	
	public Product getProduct() {
		return this.product;
	}
}
