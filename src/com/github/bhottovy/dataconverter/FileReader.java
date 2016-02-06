package com.github.bhottovy.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.product.Product;

public class FileReader {

	public static Map<Person, String> importPersons(String fileName) throws FileNotFoundException {
		
		Map<Person, String> map = new HashMap<Person, String>();
		
		try {
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			
			int count = input.nextInt();
			input.useDelimiter(";");
			input.nextLine();
			
			System.out.print(input.next());
			System.out.print(input.next());
			
			input.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static Map<Customer, String> importCustomers(String fileName) throws FileNotFoundException {
		
		Map<Customer, String> map = new HashMap<Customer, String>();
		
		try {
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			
			input.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return map;
	}

	public static Map<Product, String> importProducts(String fileName) throws FileNotFoundException {
	
		Map<Product, String> map = new HashMap<Product, String>();
		
		try {
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			
			input.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
