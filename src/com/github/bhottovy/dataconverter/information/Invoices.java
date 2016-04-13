package com.github.bhottovy.dataconverter.information;

import com.github.bhottovy.invoice.InvoiceList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("invoices")
public class Invoices {
	//Contains list of Invoices objects.
	
	@XStreamImplicit
	private InvoiceList invoices;
	
	public void setList(InvoiceList invoices) {
		this.invoices = invoices;
	}
	
	public InvoiceList getList() {
		return this.invoices;
	}
}
