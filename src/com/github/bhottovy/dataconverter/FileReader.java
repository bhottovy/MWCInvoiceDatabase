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

	public static Map<String, Person> importPersons(String fileName) throws FileNotFoundException {
		
		Map<String, Person> map = new HashMap<String, Person>();
		
		try {
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			
			int count = input.nextInt();
			input.nextLine();
			
			for(int i=0;i<count;++i){
				String personCode;
				
				ArrayList<String> emails = new ArrayList<String>();
				
				String firstName;
				String lastName;
				String street;
				String city;
				String state;
				String zip;
				String Country;
				
				String nameHolder;
				String[] fullName;
				String emailHolder;
				String[] emailList;
				String addressHolder;
				String[] fullAddress;
				
				input.useDelimiter(";");
				personCode = input.next();
				nameHolder = input.next();
				addressHolder = input.next();
				int e = 0;
				emailHolder = input.nextLine();
				
				fullName = nameHolder.split(",");
				firstName = fullName[0].trim();
				lastName = fullName[1].trim();
				
				fullAddress = addressHolder.split(",");
				street = fullAddress[0].trim();
				city = fullAddress[1].trim();
				state = fullAddress[2].trim();
				zip = fullAddress[3].trim();
				Country = fullAddress[4].trim();
				
				Address address = new Address(street, city, state, zip, Country);
				Person person = new Person(personCode, firstName, lastName, address);
				
				// Add the person to the map
				/*
				email = email.substring(1, email.length());
				
				while(input.hasNext()){
					++e;
				}
				
				for(int z = 0;z<e;++z){
					emailList.add(input.next());
				}
					*/
				map.put(person.getCode(), person);	
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
			input.nextLine();
			
			for(int i=0;i<count;++i){
				String personCode;
				String customerCode;
				
				String customerType;
				
				ArrayList<String> emails = new ArrayList<String>();
				
				String firstName;
				String lastName;
				String street;
				String city;
				String state;
				String zip;
				String Country;
				
				String nameHolder;
				String[] fullName;
				String emailHolder;
				String[] emailList;
				String addressHolder;
				String[] fullAddress;
				
				int e = 0;
				int j = 0;
				input.useDelimiter(";");
				customerCode = input.next();
				customerType = input.next();
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
				Customer newCustomer = new Customer(personCode, customerCode, firstName, lastName, custAddress);
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
