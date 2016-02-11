package com.github.bhottovy.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.github.bhottovy.dataconverter.information.Address;
import com.github.bhottovy.dataconverter.person.BusinessCustomer;
import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.person.Persons;
import com.github.bhottovy.dataconverter.person.ResidentialCustomer;
import com.github.bhottovy.dataconverter.product.Consultation;
import com.github.bhottovy.dataconverter.product.Equipment;
import com.github.bhottovy.dataconverter.product.Product;
import com.github.bhottovy.dataconverter.product.Service;

public class FileReader {

	public static ArrayList<Person> importPersons(String fileName) {
		
		ArrayList<Person> list = new ArrayList<Person>();
		
		//Attempt to open file and read contents.
		try {
			File file = new File(fileName + ".dat");
			Scanner input = new Scanner(file);
			
			//Get the number of Persons in the file, then read every line.
			int count = input.nextInt();
			input.nextLine();
			
			for(int i = 0; i < count; i++){
				//Strings for all of the Person's information
				String code;
				
				String tempName;
				String[] fullName;
				String firstName;
				String lastName;
				
				String tempAddress;
				String[] fullAddress;
				String street;
				String city;
				String state;
				String zip;
				String country;
				
				String tempEmail;
				String[] emailList;
				ArrayList<String> emails = new ArrayList<String>();
				
				//Read everything on an entire line.
				input.useDelimiter(";");
				code = input.next();
				tempName = input.next();
				tempAddress = input.next();
				tempEmail = input.nextLine().substring(1);
				
				//Split the full email string into seperate emails, then add to the List.
				emailList = tempEmail.split(",");
				for(String email : emailList) {
					emails.add(email.trim());
				}
				
				//Split the full name into first and last.
				fullName = tempName.split(",");
				firstName = fullName[0].trim();
				lastName = fullName[1].trim();
				
				//Split the address into its parts, create Address object.
				fullAddress = tempAddress.split(",");
				street = fullAddress[0].trim();
				city = fullAddress[1].trim();
				state = fullAddress[2].trim();
				zip = fullAddress[3].trim();
				country = fullAddress[4].trim();
				Address address = new Address(street, city, state, zip, country);
				
				//Create a Person object with all the details, then add to list.
				Person person = new Person(code, address, emails, firstName, lastName);
				list.add(person);		
			}
			
			//Finally, close the file reader.
			input.close();
		} catch(FileNotFoundException e) {
			//File could not be opened. Print the stack trace.
			e.printStackTrace();
		}
		
		//Return the list of Persons. If empty or file could not be read, will return null;
		return list;
	}

	public static ArrayList<Customer> importCustomers(Persons personList, String fileName) {
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		//Attempt to open file and read contents.
		try {
			File file = new File(fileName + ".dat");
			Scanner input = new Scanner(file);
			
			//Get the number of Customers in the file, then read every line.
			int count = input.nextInt();
			input.nextLine();
			
			for(int i = 0; i < count; i++){
				//Strings for all of the Customer's information
				String code;
				String type;
				
				String contactCode;
				Person contact = null;
				
				String name;
				
				String tempAddress;
				String[] fullAddress;
				String street;
				String city;
				String state;
				String zip;
				String country;
				
				//Read everything on an entire line.
				input.useDelimiter(";");
				code = input.next();
				type = input.next();
				contactCode = input.next();
				name = input.next();
				
				//Using contact code, get the Person from list of People.
				contact = personList.getPersonFromCode(contactCode);
				
				tempAddress = input.nextLine();
				
				//Split the address into its parts, create Address object.
				fullAddress = tempAddress.split(",");
				street = fullAddress[0].trim();
				city = fullAddress[1].trim();
				state = fullAddress[2].trim();
				zip = fullAddress[3].trim();
				country = fullAddress[4].trim();
				Address address = new Address(street, city, state, zip, country);
				
				//Using type, create a Business or Residential Customer object.
				Customer customer = null;
				if(type.equalsIgnoreCase("b")) { //Business Customer
					customer = new BusinessCustomer(code, address, name, contact);
				} else if(type.equalsIgnoreCase("r")) { //Residential Customer
					customer = new ResidentialCustomer(code, address, name, contact);
				} else {
					System.out.println("Invalid customer! Type needed.");
				}
				
				//If customer was created successfully, add to list. Otherwise don't.
				if(customer != null) list.add(customer);		
			}
			
			//Finally, close the file reader.
			input.close();
		} catch(FileNotFoundException e) {
			//File could not be opened. Print the stack trace.
			e.printStackTrace();
		}
		
		//Return the list of Customers. If empty or file could not be read, will return null;
		return list;
	}
	
	public static ArrayList<Product> importProducts(Persons personList, String fileName) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		//Attempt to open file and read contents.
		try {
			File file = new File(fileName + ".dat");
			Scanner input = new Scanner(file);
			
			//Get the number of Products in the file, then read every line.
			int count = input.nextInt();
			input.nextLine();
			
			for(int i = 0; i < count; i++){
				//Strings for all of the Product's information
				String productCode;
				String type;
				String productName;
				
				input.useDelimiter(";");
				productCode = input.next();
				type = input.next();
				productName = input.next();
				
				//Using type, create a product of the correct type.
				Product product = null;
				if(type.equalsIgnoreCase("e")) { //Equipment
					
					double unitPrice;
					
					//Attempt to get unitPrice from last part. Skip ';' and trim whitespace.
					unitPrice = Double.parseDouble(input.nextLine().substring(1).trim());
					
					product = new Equipment(productCode, productName, unitPrice);
				} else if(type.equalsIgnoreCase("s")) { //Service
					
					double activationFee;
					double annualFee;
					
					//Attempt to get both fees from the remaining two strings.
					activationFee = Double.parseDouble(input.next().trim());
					annualFee = Double.parseDouble(input.nextLine().substring(1).trim());
					
					product = new Service(productCode, productName, activationFee, annualFee);
				} else if(type.equalsIgnoreCase("c")) { //Consultation
					
					String personCode;
					double hourlyCost;
					
					Person consultant = null;
					
					personCode = input.next();
					
					//Attempt to get hourly cost from the remaining string.
					hourlyCost = Double.parseDouble(input.nextLine().substring(1).trim());
					
					//Using person code, get the Consultant from list of People.
					consultant = personList.getPersonFromCode(personCode);
					
					product = new Consultation(productCode, productName, hourlyCost, consultant);
				} else {
					System.out.println("Invalid product! Type needed.");
				}
				
				//If product was created successfully, add to list. Otherwise don't.
				if(product != null) list.add(product);		
			}
			
			//Finally, close the file reader.
			input.close();
		} catch(FileNotFoundException e) {
			//File could not be opened. Print the stack trace.
			e.printStackTrace();
		}
		
		//Return the list of Products. If empty or file could not be read, will return null;
		return list;
	}
}
