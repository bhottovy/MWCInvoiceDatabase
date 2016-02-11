package com.github.bhottovy.dataconverter.person;

import com.github.bhottovy.dataconverter.information.Address;

public class Customer extends Person {

	private String customerCode;
	private String type;
	
	public Customer(String personCode, String customerCode, String name, Address address, String type) {
		super(personCode, name, address);
		this.customerCode = customerCode;
		this.type = type;
	}

	public String getCode() {
		return this.customerCode;
	}
}
