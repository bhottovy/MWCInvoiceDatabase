package com.github.bhottovy.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.github.bhottovy.dataconverter.information.Address;
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
			
			String personCode;
			String name;
			String address;
			String email;
			
			String firstName;
			String lastName;
			String street;
			String city;
			String state;
			String zip;
			String Country;
			
			ArrayList<String> emailList = new ArrayList<String>();
			
			for(int i=0;i<count;++i){
				String[] nameHolder;
				String[] addressHolder;
				String[] emailHolder;
				
				int e = 0;
				int j = 0;
				input.useDelimiter(";");
				personCode = input.next();
				name = input.next();
				address = input.next();
				email = input.nextLine();
				
				nameHolder = name.split(",");
				firstName = nameHolder[j+1].trim();
				lastName = nameHolder[j].trim();
				
				addressHolder = address.split(",");
				street = addressHolder[j].trim();
				city = addressHolder[j+1].trim();
				state = addressHolder[j+2].trim();
				zip = addressHolder[j+3].trim();
				Country = addressHolder[j+4].trim();
				
				Address custAddress = new Address(street, city, state, zip, Country);
				Person newPerson = new Person(personCode, firstName, lastName, custAddress);
				
				// Add the person to the map
				
//				email = email.substring(1, email.length());
//				addressHolder = address.split(",");
//				while(input.hasNext()){
//					++e;
//				}
//				
//				for(int z = 0;z<e;++z){
//					emailList.add(input.next());
//					
//				}
//				System.out.println(email);	
			}
			
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
			
			int count = input.nextInt();
			
			String customerCode;
			String personCode;
			String name;
			String type;
			String address;
			String street;
			String city;
			String state;
			String zip;
			String Country;
			
			for(int i=0;i<count;++i){
				String[] nameHolder;
				String[] addressHolder;
				String[] emailHolder;
				
				int e = 0;
				int j = 0;
				input.useDelimiter(";");
				customerCode = input.next();
				type = input.next();
				personCode = input.next();
				name = input.next();
				address = input.nextLine();
				
				nameHolder = name.split(",");
				name = nameHolder[j].trim();
				
				addressHolder = address.split(",");
				street = addressHolder[j].trim();
				city = addressHolder[j+1].trim();
				state = addressHolder[j+2].trim();
				zip = addressHolder[j+3].trim();
				Country = addressHolder[j+4].trim();
				
				Address custAddress = new Address(street, city, state, zip, Country);
				Customer newCustomer = new Customer(personCode, name, ""/*lastName?*/, custAddress);
				// Add the new customer to the map

				System.out.println(name);	
			}
			
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
