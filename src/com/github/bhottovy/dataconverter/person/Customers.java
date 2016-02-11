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
}
