package com.github.bhottovy.dataconverter.person;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("customers")
public class Customers {
	
	//Contains list of Customer objects.
	@XStreamImplicit
	private ArrayList<Customer> customers;
	
	public void setList(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public ArrayList<Customer> getList() {
		return this.customers;
	}
	
	public Customer getCustomerFromCode(String code) {
		for(Customer customer : this.customers) {
			if(customer.getCode().equalsIgnoreCase(code)) {
				return customer;
			}
		}
		
		return null;
	}
}
