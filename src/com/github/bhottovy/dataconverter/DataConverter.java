package com.github.bhottovy.dataconverter;
import java.io.FileNotFoundException;
import java.util.Map;

import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.product.Product;

public class DataConverter {

	public static final String PERSON_FILE = "data/Persons.dat";
	public static final String CUSTOMER_FILE = "data/Customers.dat";
	public static final String PRODUCT_FILE = "data/Products.dat";
	
	public static void main (String args[]) {

		Map<String, Person> persons = null;
		Map<String, Customer> customers = null;
		Map<String, Product> products = null;
		
		try {
			persons = FileReader.importPersons(PERSON_FILE);
			//customers = FileReader.importCustomers(CUSTOMER_FILE);
			//products = FileReader.importProducts(PRODUCT_FILE);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(persons != null) {
			FileExport.exportJSON(persons, customers, products);
			FileExport.exportXML(persons, customers, products);
			System.out.println("Success!");
		} else {
			System.out.println("Error! Something went wrong.");
		}
	}
}
