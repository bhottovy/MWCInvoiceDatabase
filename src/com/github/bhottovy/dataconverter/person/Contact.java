package com.github.bhottovy.dataconverter.person;

import com.github.bhottovy.dataconverter.information.Address;

public abstract class Contact implements PersonCode {
	
	//All contacts have their own code, address, 
	
	protected String code;
	
	private Address address;
	
	public Contact(String code, Address address) {
		this.code = code;
		this.address = address;
	}
	
    public final Address getAddress() {
        return this.address;
    }
    
    public abstract String getName();
}