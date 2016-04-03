package com.github.bhottovy.dataconverter;

import com.github.bhottovy.dataconverter.person.Customers;
import com.github.bhottovy.dataconverter.person.Persons;
import com.github.bhottovy.dataconverter.product.Products;

public class DataConverter {

	//Use constants for data file names.
	public static final String FOLDER_NAME = "data/";
	
	public static final String PERSON_FILE = FOLDER_NAME + "Persons";
	public static final String CUSTOMER_FILE = FOLDER_NAME + "Customers";
	public static final String PRODUCT_FILE = FOLDER_NAME + "Products";
	
	public static void main (String args[]) {

		//Create a List Object for each object (For formatting Json/XML files).
		Persons persons = new Persons();
		Customers customers = new Customers();
		Products products = new Products();
		
		//FileReader takes a file-name as an input, and returns a list of Objects in return from the file.
		persons.setList(FileReader.importPersons(PERSON_FILE));
		customers.setList(FileReader.importCustomers(persons, CUSTOMER_FILE));
		products.setList(FileReader.importProducts(persons, PRODUCT_FILE));
		
		//Send in the Object list to be exported to JSON and XML files. Will be blank if null.
		FileExport.exportJSON(persons, PERSON_FILE);
		FileExport.exportXML(persons, PERSON_FILE);
		
		FileExport.exportJSON(customers, CUSTOMER_FILE);
		FileExport.exportXML(customers, CUSTOMER_FILE);
		
		FileExport.exportJSON(products, PRODUCT_FILE);
		FileExport.exportXML(products, PRODUCT_FILE);
		
		//Program was run without problems!
		System.out.println("Success!");
	}
}