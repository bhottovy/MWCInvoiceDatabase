package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.information.Invoice;
import com.github.bhottovy.dataconverter.information.Invoices;

public class InvoicePrinter {

	public static void printReport(Invoices invoices) {
		System.out.println("=================================");
		System.out.println("INVOICE SUMMARY REPORT");
		System.out.println("=================================");
		int invoiceNum = invoices.getList().size();
		System.out.printf("%-8s %-40s %-33s %-17s %-12s %-12s %-12s","Invoice","Customer","Salesperson","SubTotal","Fees","Taxes","Total");
//		if(invoices.getList() != null){
//			
//		}
//		else{
//
//		}
//		for(Invoice invoice : invoices.getList()){
//
//		}


	}
}
