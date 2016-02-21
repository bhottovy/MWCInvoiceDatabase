package com.github.bhottovy.dataconverter.person;

import java.util.ArrayList;

import com.github.bhottovy.dataconverter.information.Invoice;

public class SalesPerson implements PersonCode {
	
	private String code;
	private Person person;
	
	ArrayList<Invoice> invoices;
	
	public SalesPerson(String code, Person person, ArrayList<Invoice> invoices) {
		this.code = code;
		this.person = person;
		this.invoices = invoices;
	}

	@Override
	public String getCode() {
		return this.code;
	}
}
