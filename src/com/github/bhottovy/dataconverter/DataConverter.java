package com.github.bhottovy.dataconverter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.product.Product;

public class DataConverter {
	
	Map<Person, String> persons = new HashMap<Person, String>();
	Map<Customer, String> customers = new HashMap<Customer, String>();
	Map<Product, String> products = new HashMap<Product, String>();
	
	public static final String PERSON_FILE = "data/Persons.dat";
	public static final String CUSTOMER_FILE = "data/Customers.dat";
	public static final String PRODUCT_FILE = "data/Products.dat";
	
	public static void main (String args[]) {
		
		this.persons = FileReader.importData(PERSON_FILE);
		this.customers = FileReader.importData(CUSTOMER_FILE);
		this.products = FileReader.importData(PRODUCT_FILE);
			   
	}
}
