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
	
	public static void main (String args[]) throws FileNotFoundException {
		
		Map<Person, String> persons = FileReader.importPersons(PERSON_FILE);
		Map<Customer, String> customers = FileReader.importCustomers(CUSTOMER_FILE);
		Map<Product, String> products = FileReader.importProducts(PRODUCT_FILE);
		
		//FileWriter.exportData(persons, customers, products);
	}
}
