package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.FileReader;
import com.github.bhottovy.dataconverter.information.Invoice;
import com.github.bhottovy.dataconverter.information.Invoices;
import com.github.bhottovy.dataconverter.person.Customers;
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

		//Create a List Object for each object 
		Persons persons = new Persons();
		Customers customers = new Customers();
		Products products = new Products();
		Invoices invoices1 = new Invoices();
		persons.setList(FileReader.importPersons(PERSON_FILE));
		customers.setList(FileReader.importCustomers(persons, CUSTOMER_FILE));
		products.setList(FileReader.importProducts(persons, PRODUCT_FILE));
		invoices1.setList(FileReader.importInvoices(persons, products, customers, INVOICE_FILE));

		//		
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
		int numOfInvoices = invoices1.getList().size();
		int count = 0;

		for(Invoice b : invoices1.getList()){
			String invoicePH = b.getCode();
			String customerPH = b.getCustomer().getName();
			String salespersonPH= "NEED SALESPERSON";


			System.out.printf("%-8s %-40s %-30s %s %9.2f %2s %10.2f %2s %10.2f %2s %9.2f %n",invoicePH, customerPH, salespersonPH,"$",costPH,"$",costPH,"$",costPH,"$",costPH);

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

		for(Invoice a : invoices1.getList()){
			int numOfProducts= a.getProducts().size();
			String invoiceCode = a.getCode();
			String date = a.getDate();
			String customerName = a.getCustomer().getName();
			String customerCode = "CODE";
			String salespersonName = "SALESPERSON NAME";
			String businessType = "RESIDENTIAL or  BUSINESS";
			String customerPerson = "NO PERSON NAME";
			String address = a.getCustomer().getAddress().getStreet();
			String cityState = a.getCustomer().getAddress().getCity()+", " + a.getCustomer().getAddress().getState() + " " + a.getCustomer().getAddress().getZip()+ " " + a.getCustomer().getAddress().getCountry();

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
				String codePH = a.getProducts().get(j).getProduct().getCode();
				String itemPH = a.getProducts().get(j).getProduct().getName();
				System.out.printf("%-8s %-58s %s %9.2f %1s %9.2f %1s %10.2f %n",customerCode,itemPH,"$",costPH,"$",costPH,"$",costPH,"$",costPH,"$");
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
