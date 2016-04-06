package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.FileReader;
import com.github.bhottovy.dataconverter.information.Invoices;
import com.github.bhottovy.dataconverter.person.Customers;
import com.github.bhottovy.dataconverter.person.Persons;
import com.github.bhottovy.dataconverter.product.Products;

public class InvoiceReport {

	//Use constants for data file names
	public static final String FOLDER_NAME = "data/";
		
	public static final String PERSON_FILE = FOLDER_NAME + "Persons";
	public static final String CUSTOMER_FILE = FOLDER_NAME + "Customers";
	public static final String PRODUCT_FILE = FOLDER_NAME + "Products";
	public static final String INVOICE_FILE = FOLDER_NAME + "Invoices";
	
	public static void main(String[] args) {
		
		//Create a List Object for each object (For formatting Json/XML files).
		Persons persons = new Persons();
		Customers customers = new Customers();
		Products products = new Products();
		Invoices invoices = new Invoices();
		
		//FileReader takes a file-name as an input, and returns a list of Objects in return from the file.
		persons.setList(FileReader.importPersons(PERSON_FILE));
		customers.setList(FileReader.importCustomers(persons, CUSTOMER_FILE));
		products.setList(FileReader.importProducts(persons, PRODUCT_FILE));
		invoices.setList(FileReader.importInvoices(persons, products, customers, INVOICE_FILE));
		
		//After collecting all the Invoices from the file, print a report.
		InvoicePrinter.printReport(invoices);
	}
}
