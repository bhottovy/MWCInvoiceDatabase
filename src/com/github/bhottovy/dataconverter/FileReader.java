package com.github.bhottovy.dataconverter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.product.Product;

public class FileReader {

	public static Map<Person, String> importPersons(String fileName) {
		
		Map<Person, String> map = new HashMap<Person, String>();
		
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		
		return map;
	}
	
	public static Map<Customer, String> importCustomers(String fileName) {
		
		Map<Customer, String> map = new HashMap<Customer, String>();
		
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		
		return map;
	}

	public static Map<Product, String> importProducts(String fileName) {
	
		Map<Product, String> map = new HashMap<Product, String>();
		
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		
		return map;
	}
}
