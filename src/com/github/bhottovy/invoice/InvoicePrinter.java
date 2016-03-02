package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.information.Invoice;
import com.github.bhottovy.dataconverter.information.Invoices;

public class InvoicePrinter {

	public static void printReport(Invoices invoices) {

		//int invoiceNum = invoices.getList().size();

		System.out.println("=================================");
		System.out.println("INVOICE SUMMARY REPORT");
		System.out.println("=================================");
		System.out.printf("%-8s %-40s %-33s %-17s %-12s %-12s %s %n","Invoice","Customer","Salesperson","SubTotal","Fees","Taxes","Total");

		//		if(invoices.getList() != null){
		//
		//		}
		//		else{
		//
		//		}
		
		//Used for the division of categories
		String format1 = "=================================";
		String format2 = "===============================================";
		String format3 = "===================================================================================================================================";
		String format4 = "----------------------------------";
		
		//Placeholders for now
		String invoicePH = "INV001";
		String datePH = "Mar 03, 2016";
		String customerPH = "Stark Industries";
		String codePH = "ff23";
		String itemPH = "Satellite Reciever  (2 units at 2500.0/unit)";
		String customerCodePH = "C001";
		String salespersonPH = "Eccleston, Chris";
		String businessPH = "Business";
		String personPH = "McCoy, Sylvester";
		String addressPH = "912 E Kirwin Ave";
		String cityStatePH = "Salina, KS  67401  USA";
		int invoiceQuantityPH = 5;
		double costPH = 1000;
		int numOfProducts = 2;
		int numOfInvoices = 1;

		for(/*Invoice invoice : invoices.getList()*/int i =0; i<numOfInvoices;++i){
			System.out.printf("%-8s %-40s %-30s %s %9.2f %2s %10.2f %2s %10.2f %2s %9.2f %n",invoicePH, customerPH, salespersonPH,"$",costPH,"$",costPH,"$",costPH,"$",costPH);

			if (i==numOfInvoices-1){
				System.out.println("=====================================================================================================================================");
				System.out.printf("%-80s %s %9.2f %2s %10.2f %2s %10.2f %2s %9.2f %n","TOTALS","$",costPH,"$",costPH,"$",costPH,"$",costPH);
			}
		}
		System.out.printf("%n %n");
		System.out.println("=================================");
		System.out.println("INVOICE DETAIL REPORTS");
		System.out.println("=================================");
		System.out.println("");

		for(int i=0; i<numOfInvoices;++i){
			System.out.println("");
			System.out.println("----------------------------------");
			System.out.println("Invoice:" + invoicePH);
			System.out.println("Date:" + datePH);
			System.out.println("----------------------------------");
			System.out.println("Salesperson: " + salespersonPH);
			System.out.println("Customer:");
			System.out.println("	" + customerPH + " (" + customerCodePH + ")");
			System.out.println("	(" + businessPH + ")");
			System.out.println("	[" + personPH + "]");
			System.out.println("	" + addressPH);
			System.out.println("	" + cityStatePH);
			System.out.println("----------------------------------");
			System.out.printf("%-8s %-61s %7s %11s %12s %12s %n","Code","Item","Subtotal","Taxes","Fees","Total");
			for(int j=0;j<numOfProducts; ++j){
				System.out.printf("%-9s %-57s %s %9.2f %1s %9.2f %1s %10.2f %n",customerCodePH,itemPH,"$",costPH,"$",costPH,"$",costPH,"$",costPH,"$");
				if (j==numOfProducts-1){
					System.out.printf("%117s",format2);
					System.out.printf("%-68s %s %9.2f %1s %9.2f %1s %10.2f %s %10.2f %n","\nSUB-TOTALS","$",costPH,"$",costPH,"$",costPH,"$",costPH);
					System.out.printf("%-104s %s %10.2f %n","COMPLIANCE FEE","$",costPH);
					System.out.printf("%-104s %s %10.2f %n %n","FINAL TOTAL","$",costPH);
				}
			}
		}

	}
}
