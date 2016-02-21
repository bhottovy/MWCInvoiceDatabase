package com.github.bhottovy.dataconverter.information;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("invoices")
public class Invoices {
	//Contains list of Invoices objects.
		@XStreamImplicit
		private ArrayList<Invoice> invoices;
		
		public void setList(ArrayList<Invoice> invoices) {
			this.invoices = invoices;
		}
		
		public ArrayList<Invoice> getList() {
			return this.invoices;
		}
}
