package com.github.bhottovy.dataconverter.information;

import java.util.ArrayList;

import org.joda.time.DateTime;

import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.product.SoldProduct;

public class Invoice {

	//Invoices have an alphanumeric code.
	private String code;
	
	//An invoice contains a customer, date sold, a list of products, and a salesperson (optional).
	private Customer customer;
	private DateTime date;
	private ArrayList<SoldProduct> products;
	private Person salesPerson;
	
	public Invoice(String code, Customer customer, String date, ArrayList<SoldProduct> products, Person salesPerson) {
		this.code = code;
		this.customer = customer;
		this.date = DateTime.parse(date);
		this.products = products;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public String getDate() {
		return this.date.toString();
	}
	
	public ArrayList<SoldProduct> getProducts() {
		return this.products;
	}
	
	public Person getSalesPerson() {
		return this.salesPerson;
	}
}
