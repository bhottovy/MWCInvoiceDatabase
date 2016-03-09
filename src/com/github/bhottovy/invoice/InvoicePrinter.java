package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.FileReader;
import com.github.bhottovy.dataconverter.information.Invoice;
import com.github.bhottovy.dataconverter.information.Invoices;
import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Customers;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.person.Persons;
import com.github.bhottovy.dataconverter.product.Products;
import com.github.bhottovy.dataconverter.product.Product;
import com.github.bhottovy.dataconverter.product.SoldProduct;

public class InvoicePrinter {
	public static final String FOLDER_NAME = "data/";

	public static final String PERSON_FILE = FOLDER_NAME + "Persons";
	public static final String CUSTOMER_FILE = FOLDER_NAME + "Customers";
	public static final String PRODUCT_FILE = FOLDER_NAME + "Products";
	public static final String INVOICE_FILE = FOLDER_NAME + "Invoices";

	public static void printReport(Invoices invoices) {
		
		System.out.println("=================================");
		System.out.println("INVOICE SUMMARY REPORT");
		System.out.println("=================================");
		System.out.printf("%-8s %-40s %-33s %-17s %-12s %-12s %s %n","Invoice","Customer","Salesperson","SubTotal","Fees","Taxes","Total");

		//Used for the division of categories
		String lineFormat1 = "=================================";
		String lineFormat2 = "===============================================";
		String lineFormat3 = "===================================================================================================================================";
		String lineFormat4 = "----------------------------------";
		

		double costPH = 1000;
		int numOfInvoices = invoices.getList().size();
		int count = 0;

		for(Invoice invoiceA : invoices.getList()){
			String invoicePH = invoiceA.getCode();
			String customerPH = invoiceA.getCustomer().getName();
			String salespersonName = /*invoiceA.getSalesPerson().getName()*/"NEED SALESPERSON NAME";
			


			System.out.printf("%-8s %-40s %-30s %s %9.2f %2s %10.2f %2s %10.2f %2s %9.2f %n",invoicePH, customerPH, salespersonName,"$",costPH,"$",costPH,"$",costPH,"$",costPH);

			if (count == numOfInvoices-1){
				System.out.println(lineFormat3);
				System.out.printf("%-80s %s %9.2f %2s %10.2f %2s %10.2f %2s %9.2f %n","TOTALS","$",costPH,"$",costPH,"$",costPH,"$",costPH);
			}
			count++;
		}
		System.out.printf("%n %n");
		System.out.println(lineFormat1);
		System.out.println("INVOICE DETAIL REPORTS");
		System.out.println(lineFormat1);
		System.out.println("");

		for(Invoice invoiceB : invoices.getList()){
			int numOfProducts= invoiceB.getProducts().size();
			String invoiceCode = invoiceB.getCode();
			String date = invoiceB.getDate().substring(0,10);
			String customerName = invoiceB.getCustomer().getName();
			String salespersonName = invoiceB.getSalesPerson().getName();
			String customerCode = invoiceB.getCustomer().getCode();
			String businessType = "NEED RESIDENTIAL or BUSINESS";
			String customerPerson = invoiceB.getCustomer().getContact().getName();
			String address = invoiceB.getCustomer().getAddress().getStreet().substring(1, invoiceB.getCustomer().getAddress().getStreet().length());
			String cityState = invoiceB.getCustomer().getAddress().getCity()+", " + invoiceB.getCustomer().getAddress().getState() + " " + invoiceB.getCustomer().getAddress().getZip()+ " " + invoiceB.getCustomer().getAddress().getCountry();

			System.out.println("");
			System.out.println(lineFormat4);
			System.out.println("Invoice:" + invoiceCode);
			System.out.println("Date:" + date);
			System.out.println(lineFormat4);
			System.out.println("Salesperson: " + salespersonName);
			System.out.println("Customer:");
			System.out.println("	" + customerName + " (" + customerCode + ")");
			System.out.println("	(" + businessType + ")");
			System.out.println("	[" + customerPerson + "]");
			System.out.println("	" + address);
			System.out.println("	" + cityState);
			System.out.println(lineFormat4);
			System.out.printf("%-8s %-61s %7s %11s %12s %12s %n","Code","Item","Subtotal","Taxes","Fees","Total");

			//Prints the products
			for(int j=0;j<numOfProducts; ++j){
				SoldProduct productCode = invoiceB.getProducts().get(j);
				String codePH = /*a.getProducts().get(j).getProduct().getCode();*/"code";
				String itemPH = /*a.getProducts().get(j).getProduct().getName();*/"NEED PRODUCT NAME";
				System.out.printf("%-8s %-58s %s %9.2f %1s %9.2f %1s %10.2f %n",productCode,itemPH,"$",costPH,"$",costPH,"$",costPH,"$",costPH,"$");
				if (j==numOfProducts-1){
					System.out.printf("%117s",lineFormat2);
					System.out.printf("%-68s %s %9.2f %1s %9.2f %1s %10.2f %s %10.2f %n","\nSUB-TOTALS","$",costPH,"$",costPH,"$",costPH,"$",costPH);
					System.out.printf("%-104s %s %10.2f %n","COMPLIANCE FEE","$",costPH);
					System.out.printf("%-104s %s %10.2f %n %n","FINAL TOTAL","$",costPH);
				}
			}
		}
	}
}
