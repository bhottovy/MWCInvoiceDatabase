package com.github.bhottovy.dataconverter.person;

import com.github.bhottovy.dataconverter.information.Address;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("residential")
public class ResidentialCustomer extends Customer {

	public ResidentialCustomer(String code, Address address, String name, Person contact) {
		super(code, address, name, contact);
	}
}
