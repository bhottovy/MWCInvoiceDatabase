package com.github.bhottovy.invoice;

import com.github.bhottovy.database.DatabaseReader;
import com.github.bhottovy.dataconverter.information.Invoice;
import com.github.bhottovy.dataconverter.information.Invoices;
import com.github.bhottovy.dataconverter.person.Customers;
import com.github.bhottovy.dataconverter.person.Persons;
import com.github.bhottovy.dataconverter.product.Products;
import com.github.bhottovy.dataconverter.product.SoldProduct;

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
		persons.setList(DatabaseReader.importPersons());
		customers.setList(DatabaseReader.importCustomers(persons.getList()));
		products.setList(DatabaseReader.importProducts(persons.getList()));
		invoices.setList(DatabaseReader.importInvoices(persons.getList(), customers.getList(), products.getList()));
		
		InvoiceList list = invoices.getList();
		
		//After collecting all the Invoices from the file, print a report.
		InvoicePrinter.printReport(invoices);
	}
}
