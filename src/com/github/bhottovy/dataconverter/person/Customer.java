package com.github.bhottovy.dataconverter.person;

import com.github.bhottovy.dataconverter.information.Address;

public abstract class Customer extends Contact {

	//Customers are both People and Businesses, so their names do not have a first and last part.
	private String name;
	
	//Customers also have a primary contact, which is a person.
	private Person contact;
	
	public Customer(String code, Address address, String name, Person contact) {
		super(code, address);
		
		this.name = name;
		this.contact = contact;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public Person getContact() {
		return this.contact;
	}
}
