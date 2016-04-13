package com.github.bhottovy.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.github.bhottovy.dataconverter.information.Address;
import com.github.bhottovy.dataconverter.information.Invoice;
import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.person.ResidentialCustomer;
import com.github.bhottovy.dataconverter.product.Consultation;
import com.github.bhottovy.dataconverter.product.Equipment;
import com.github.bhottovy.dataconverter.product.Product;
import com.github.bhottovy.dataconverter.product.SellableProduct.ProductType;
import com.github.bhottovy.dataconverter.product.Service;
import com.github.bhottovy.dataconverter.product.SoldProduct;
import com.github.bhottovy.invoice.InvoiceList;

public class DatabaseReader {

	public static ArrayList<Person> importPersons() {
		
		ArrayList<Person> list = new ArrayList<Person>();
		
		String query_get_all_persons = "SELECT * FROM Person;";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement personPS = conn.prepareStatement(query_get_all_persons);
			ResultSet personRS = personPS.executeQuery();
			
			while(personRS.next()) {
				String personCode;
				Address address;
				ArrayList<String> email;
				String firstName;
				String lastName;
				
				int contactID = personRS.getInt("ContactID");
				
				firstName = personRS.getString("FirstName");
				lastName = personRS.getString("LastName");
				
				
				String query_get_contact = "SELECT * FROM Contact WHERE ContactID = ?;";
				PreparedStatement contactPS = conn.prepareStatement(query_get_contact);
				contactPS.setInt(1, contactID);
				ResultSet contactRS = contactPS.executeQuery();
				contactRS.next();
				
				personCode = contactRS.getString("ContactCode");
				int addressID = contactRS.getInt("AddressID");
				
				contactRS.close();
				contactPS.close();
				
				
				String query_get_address = "SELECT * FROM Address WHERE AddressID = ?;";
				PreparedStatement addressPS = conn.prepareStatement(query_get_address);
				addressPS.setInt(1, addressID);
				ResultSet addressRS = addressPS.executeQuery();
				addressRS.next();
				
				String street;
				String city;
				String state;
				String zip;
				String country;
				
				street = addressRS.getString("Street");
				city = addressRS.getString("City");
				state = addressRS.getString("State");
				zip = addressRS.getString("Zip");
				country = addressRS.getString("Country");
				
				address = new Address(street, city, state, zip, country);
				
				addressRS.close();
				addressPS.close();
				
				String query_get_emails = "SELECT * FROM Email WHERE PersonID = ?;";
				PreparedStatement emailPS = conn.prepareStatement(query_get_emails);
				emailPS.setInt(1, addressID);
				ResultSet emailRS = emailPS.executeQuery();
				emailRS.next();
				
				email = new ArrayList<String>();
				
				while(emailRS.next()) {
					String emailAddress;
					
					emailAddress = emailRS.getString("EmailAddress");
					
					email.add(emailAddress);
				}
				
				emailRS.close();
				emailPS.close();
				
				
				Person person = new Person(personCode, address, email, firstName, lastName);
				list.add(person);
			}
			
			personRS.close();
			personPS.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static ArrayList<Customer> importCustomers(ArrayList<Person> persons) {
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		String query_get_all_customers = "SELECT * FROM Customer;";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement customerPS = conn.prepareStatement(query_get_all_customers);
			ResultSet customerRS = customerPS.executeQuery();
			
			while(customerRS.next()) {
				String customerCode;
				Address address = null;
				String name;
				Person contact = null;
				
				name = customerRS.getString("CustomerName");
				
				int personID = customerRS.getInt("PersonID");
				
				contact = DatabaseReader.findPerson(persons, personID);
				
				customerCode = contact.getCode();
				
				Customer customer = new ResidentialCustomer(customerCode, address, name, contact);
				list.add(customer);
			}
			
			customerRS.close();
			customerPS.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static ArrayList<Product> importProducts(ArrayList<Person> persons) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query_get_all_products = "SELECT * FROM Product;";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement productPS = conn.prepareStatement(query_get_all_products);
			ResultSet productRS = productPS.executeQuery();
			
			while(productRS.next()) {
				String productCode;
				String productName;
				ProductType type = null;
				
				double unitPrice = 0.0;
				
				double activationFee = 0.0;
				double annualFee = 0.0;
				
				double hourlyCost = 0.0;
				Person consultant = null;
				
				productCode = productRS.getString("ProductCode");
				productName = productRS.getString("ProductName");
				
				int productID = productRS.getInt("ProductID");
				
				String query_does_equipment_exist = "SELECT * FROM Equipment WHERE ProductID = ?;";
				String query_does_service_exist = "SELECT * FROM Service WHERE ProductID = ?;";
				String query_does_consultation_exist = "SELECT * FROM Consultation WHERE ProductID = ?;";
				
				PreparedStatement equipmentPS = conn.prepareStatement(query_does_equipment_exist);
				equipmentPS.setInt(1, productID);
				ResultSet equipmentRS = equipmentPS.executeQuery();
				if(equipmentRS.next()) {
					type = ProductType.PRODUCT_TYPE_EQUIPMENT;
					
					unitPrice = equipmentRS.getDouble("UnitPrice");
				}
				
				equipmentRS.close();
				equipmentPS.close();
				
				PreparedStatement servicePS = conn.prepareStatement(query_does_service_exist);
				servicePS.setInt(1, productID);
				ResultSet serviceRS = servicePS.executeQuery();
				if(serviceRS.next()) {
					type = ProductType.PRODUCT_TYPE_SERVICE;
					
					activationFee = serviceRS.getDouble("ActivationFee");
					annualFee = serviceRS.getDouble("AnnualFee");
				}
				
				serviceRS.close();
				servicePS.close();
				
				PreparedStatement consultationPS = conn.prepareStatement(query_does_consultation_exist);
				consultationPS.setInt(1, productID);
				ResultSet consultationRS = consultationPS.executeQuery();
				if(consultationRS.next()) {
					type = ProductType.PRODUCT_TYPE_CONSULTATION;
					
					hourlyCost = consultationRS.getDouble("HourlyCost");
					
					int personID = consultationRS.getInt("PersonID");
					
					consultant = DatabaseReader.findPerson(persons, personID);
				}
				
				consultationRS.close();
				consultationPS.close();
				
				Product product;
				
				if(type == ProductType.PRODUCT_TYPE_EQUIPMENT) {
					product = new Equipment(productCode, productName, unitPrice);
				} else if(type == ProductType.PRODUCT_TYPE_SERVICE) {
					product = new Service(productCode, productName, activationFee, annualFee);
				} else if(type == ProductType.PRODUCT_TYPE_CONSULTATION){
					product = new Consultation(productCode, productName, hourlyCost, consultant);
				} else {
					product = null;
				}
				list.add(product);
			}
			
			productRS.close();
			productPS.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static InvoiceList importInvoices(ArrayList<Person> persons, ArrayList<Customer> customers, ArrayList<Product> products) {
		
		InvoiceList list = new InvoiceList();
		
		String query_get_all_invoices = "SELECT * FROM Invoice;";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement invoicePS = conn.prepareStatement(query_get_all_invoices);
			ResultSet invoiceRS = invoicePS.executeQuery();
			
			while(invoiceRS.next()) {
				String invoiceCode;
				Customer customer = null;
				String date;
				ArrayList<SoldProduct> soldProducts = new ArrayList<SoldProduct>();
				Person salesPerson = null;
				
				invoiceCode = invoiceRS.getString("InvoiceCode");
				date = invoiceRS.getString("InvoiceDate");

				int invoiceID = invoiceRS.getInt("InvoiceID");
				int personID = invoiceRS.getInt("PersonID");
				int customerID = invoiceRS.getInt("CustomerID");
				
				salesPerson = DatabaseReader.findPerson(persons, personID);
				customer = DatabaseReader.findCustomer(customers, persons, customerID);
				
				
				String query_get_product_list = "SELECT * FROM ProductList WHERE InvoiceID = ?;";
				
				PreparedStatement listPS = conn.prepareStatement(query_get_product_list);
				listPS.setInt(1, invoiceID);
				ResultSet listRS = listPS.executeQuery();
				
				while(listRS.next()) {
					int soldProductID = listRS.getInt("SoldProductID");
					SoldProduct soldProduct;
					Product product;
					
					String startDate;
					String endDate;
					
					int numUnits;
					
					int numHours;
					
					String query_get_sold_product = "SELECT * FROM SoldProduct WHERE SoldProductID = ?;";
					
					PreparedStatement soldPS = conn.prepareStatement(query_get_sold_product);
					soldPS.setInt(1, invoiceID);
					ResultSet soldRS = soldPS.executeQuery();
					soldRS.next();
					
					startDate = soldRS.getString("StartDate");
					endDate = soldRS.getString("EndDate");
					
					numUnits = soldRS.getInt("NumUnits");
					
					numHours = soldRS.getInt("NumHours");
					
					int productID = soldRS.getInt("ProductID");
					
					product = DatabaseReader.findProduct(products, productID);
					
					if(startDate != null && endDate != null) {
						soldProduct = new SoldProduct(product, startDate, endDate);
					} else if(numUnits != 0) {
						soldProduct = new SoldProduct(product, numUnits);
					} else if(numHours != 0) {
						soldProduct = new SoldProduct(product, numHours);
					} else {
						soldProduct = null;
					}
					
					soldRS.close();
					soldPS.close();
					
					soldProducts.add(soldProduct);
				}
				
				listRS.close();
				listPS.close();
				
				Invoice invoice = new Invoice(invoiceCode, customer, date, soldProducts, salesPerson);
				list.addToEnd(invoice);
			}
			
			invoiceRS.close();
			invoicePS.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private static final Person findPerson(ArrayList<Person> persons, int personID) {
		Person matchedPerson = null;
		
		String query_get_person = "SELECT * FROM Person WHERE PersonID = ?;";
		
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement personPS = conn.prepareStatement(query_get_person);
			personPS.setInt(1, personID);
			ResultSet personRS = personPS.executeQuery();
			personRS.next();
			
			int contactID = personRS.getInt("ContactID");
			
			personRS.close();
			personPS.close();
			
			String query_get_contact = "SELECT * FROM Contact WHERE ContactID = ?;";
			PreparedStatement contactPS = conn.prepareStatement(query_get_contact);
			contactPS.setInt(1, contactID);
			ResultSet contactRS = contactPS.executeQuery();
			contactRS.next();
			
			String personCode = contactRS.getString("ContactCode");
			
			contactRS.close();
			contactPS.close();
			
			for(Person person : persons) {
				if(person.getCode().equals(personCode)) {
					matchedPerson = person;
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return matchedPerson;
	}
	
	private static final Customer findCustomer(ArrayList<Customer> customers, ArrayList<Person> persons, int customerID) {
		Customer matchedCustomer = null;
		
		String query_get_customer = "SELECT * FROM Customer WHERE CustomerID = ?;";
		
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement customerPS = conn.prepareStatement(query_get_customer);
			customerPS.setInt(1, customerID);
			ResultSet customerRS = customerPS.executeQuery();
			customerRS.next();
			
			int personID = customerRS.getInt("PersonID");
			
			Person person = DatabaseReader.findPerson(persons, personID);
			String customerCode = null;
			
			if(person != null) {
				customerCode =  person.getCode();
			}
			
			customerRS.close();
			customerPS.close();
			
			for(Customer customer : customers) {
				if(customer.getCode().equals(customerCode)) {
					matchedCustomer = customer;
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return matchedCustomer;
	}
	
	private static final Product findProduct(ArrayList<Product> products, int productID) {
		Product matchedProduct = null;
		
		String query_get_product = "SELECT * FROM Product WHERE ProductID = ?;";
		
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement productPS = conn.prepareStatement(query_get_product);
			productPS.setInt(1, productID);
			ResultSet productRS = productPS.executeQuery();
			productRS.next();
			
			String productCode = productRS.getString("ProductCode");
			
			productRS.close();
			productPS.close();
			
			for(Product product : products) {
				if(product.getCode().equals(productCode)) {
					matchedProduct = product;
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return matchedProduct;
	}
}
