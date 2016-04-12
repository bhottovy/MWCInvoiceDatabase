package com.mwc.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.github.bhottovy.database.DatabaseInfo;

/* NOTE: Donot change the package name or any of the method signatures.
 *  
 * There are 14 methods in total, all of which need to be completed as a 
 * bare minimum as part of the assignment.You can add additional methods 
 * for testing if you feel.
 * 
 * It is also recommended that you write a separate program to read
 * from the .dat files and test these methods to insert data into your 
 * database.
 * 
 * Donot forget to change your reports generation classes to read from 
 * your database instead of the .dat files.
 */

/**
 * Class containing all methods interacting with the database.
 */
public class InvoiceData {
	
	/**Method that removes every person record from the database. 
	 */
	public static void removeAllPersons() {
		String query_delete_all_persons = "DELETE FROM Person;"
				+ "ALTER TABLE Person AUTO_INCREMENT = 1";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_delete_all_persons);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method to add a person record to the database with the provided data. 
	 */
	
	public static void addAddress(String street, String city, String state, String zip, String country) {
		String query_add_address = "INSERT INTO Address (Street, City, State, Zip, Country) "
				+ "VALUES (?, ?, ?, ?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_add_address);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);

			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addContact(String contactCode, int addressID) {
		String query_add_contact = "INSERT INTO Contact (AddressID, ContactCode) "
				+ "VALUES (?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_add_contact);
			ps.setInt(1, addressID);
			ps.setString(2, contactCode);

			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addPerson(String personCode, String firstName, String lastName, 
		String street, String city, String state, String zip, String country) {
		
		String query_add_person = "INSERT INTO Person (ContactID, FirstName, LastName)"
				+ "VALUES (?, ?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			InvoiceData.addAddress(street, city, state, zip, country);
			String query_get_address_id = "SELECT LAST_INSERT_ID()";
			
			PreparedStatement ps = conn.prepareStatement(query_get_address_id);
			
			ResultSet rs = ps.executeQuery();
			InvoiceData.addContact(personCode, rs.getInt("AddressID"));
			String query_get_contact_id = "(SELECT LAST_INSERT_ID())";
			rs.close();
			
			ps = conn.prepareStatement(query_add_person);
			ps.setString(1, query_get_contact_id);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method to add an email record to the database with the associated personCode. 
	 */
	public static void addEmail(String personCode, String email) {
	
		String query_add_email = "INSERT INTO Email (PersonID, EmailAddress) VALUES (?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_add_email);
			
			String query_get_person_id = "(SELECT P.PersonID FROM Person AS P "
					+ "JOIN Contact AS C ON P.ContactID = C.ContactID WHERE C.ContactCode = '"+personCode+"')";
			
			ps.setString(1, query_get_person_id);
			ps.setString(2, email);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void removeAllCustomers() {
		String query_delete_all_customers = "DELETE FROM Customer;"
				+ "ALTER TABLE Customer AUTO_INCREMENT = 1";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_delete_all_customers);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method to add a customer record to the database with the provided data
	 */
	public static void addCustomer(String customerCode, String type, String primaryContactPersonCode, String name, 
			String street, String city, String state, String zip, String country) {
			
			String query_add_customer = "INSERT INTO Customer (PersonID, CustomerName)"
					+ "VALUES (?, ?);";
			Connection conn = DatabaseInfo.getConnection();
			
			try {
				InvoiceData.addPerson(customerCode, "", "", street, city, state, zip, country);
				String query_get_person_id = "SELECT LAST_INSERT_ID()";
				
				PreparedStatement ps = conn.prepareStatement(query_add_customer);

				ps.setString(1, query_get_person_id);
				ps.setString(2, name);
				
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	/**Removes all product records from the database. 
	 */
	public static void removeAllProducts() {
		String query_delete_all_products = "DELETE FROM Product;"
				+ "ALTER TABLE Product AUTO_INCREMENT = 1";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_delete_all_products);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Adds an equipment record to the database with the provided data.
	 */
	
	public static void addProduct(String productCode, String productName) {
		String query_add_product = "INSERT INTO Product VALUES (?, ?)";

		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_add_product);
			
			ps.setString(1, productCode);
			ps.setString(2, productName);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addEquipment(String productCode, String name, Double pricePerUnit) {
		String query_add_equipment = "INSERT INTO Equipment (ProductID, UnitPrice) VALUES (?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			InvoiceData.addProduct(productCode, name);
			String query_get_product_id = "(SELECT LAST_INSERT_ID())";
			
			PreparedStatement ps = conn.prepareStatement(query_add_equipment);
			
			ps.setString(1, query_get_product_id);
			ps.setDouble(2, pricePerUnit);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**Adds a service record to the database with the provided data.
	 */
	public static void addService(String productCode, String name, double activationFee, double annualFee) {
		String query_add_service = "INSERT INTO Service (ProductID, ActivationFee, AnnualFee) VALUES (?, ?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			InvoiceData.addProduct(productCode, name);
			String query_get_product_id = "(SELECT LAST_INSERT_ID())";
			
			PreparedStatement ps = conn.prepareStatement(query_add_service);
			
			ps.setString(1, query_get_product_id);
			ps.setDouble(2, activationFee);
			ps.setDouble(3, annualFee);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Adds an consultation record to the database with the provided data.
	 */
	public static void addConsultation(String productCode, String name, String consultantPersonCode, Double hourlyFee) {
		String query_add_consultation = "INSERT INTO Consultation (ProductID, PersonID, HourlyCost) VALUES (?, ?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			InvoiceData.addProduct(productCode, name);
			String query_get_product_id = "(SELECT LAST_INSERT_ID())";
			String query_get_person_id = "(SELECT P.PersonID FROM Person AS P"
					+ "JOIN Contact AS C ON P.ContactID = C.ContactID WHERE C.ContactCode = '"+consultantPersonCode+"')";
			
			PreparedStatement ps = conn.prepareStatement(query_add_consultation);
			
			ps.setString(1, query_get_product_id);
			ps.setString(2, query_get_person_id);
			ps.setDouble(3, hourlyFee);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Removes all invoice records from the database. 
	 */
	public static void removeAllInvoices() {
		String query_delete_all_invoices = "DELETE FROM Invoice;"
				+ "ALTER TABLE Invoice AUTO_INCREMENT = 1";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query_delete_all_invoices);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String invoiceDate, String salesPersonCode) {
		String query_add_invoice = "INSERT INTO Invoice (CustomerID, PersonID, InvoiceCode, InvoiceDate) VALUES (?, ?, ?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			String query_get_customer_id = "(SELECT Cust.CustomerID FROM Customer AS Cust "
					+ "JOIN Person AS P ON Cust.PersonID = P.PersonID "
					+ "JOIN Contact AS C ON P.ContactID = C.ContactID WHERE C.ContactCode = '"+customerCode+"')";
			String query_get_person_id = "(SELECT P.PersonID FROM Person AS P"
					+ "JOIN Contact AS C ON P.ContactID = C.ContactID WHERE C.ContactCode = '"+salesPersonCode+"')";
			
			PreparedStatement ps = conn.prepareStatement(query_add_invoice);
			
			ps.setString(1, query_get_customer_id);
			ps.setString(2, query_get_person_id);
			ps.setString(3, invoiceCode);
			ps.setString(4, invoiceDate);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**Adds a particular equipment (corresponding to productCode to an 
	 * invoice corresponding to the provided invoiceCode with the given
	 * number of units)
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String productCode, int numUnits) {
		String query_add_equipment_to_invoice = "INSERT INTO ProductList (InvoiceID, SoldProductID) VALUES (?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			String query_get_invoice_id = "(SELECT InvoiceID FROM Invoice WHERE InvoiceCode = '"+invoiceCode+"')";
			String query_get_product_id = "(SELECT ProductID FROM Product WHERE ProductCode = '"+productCode+"' )";
			String query_add_sold_product = "INSERT INTO SoldProduct (ProductID, NumUnits) "
					+ "VALUES (?, ?);";
			String query_get_sold_product_id = "(SELECT LAST_INSERT_ID())";
			
			PreparedStatement ps = conn.prepareStatement(query_add_sold_product);
			
			ps.setString(1, query_get_product_id);
			ps.setDouble(2, numUnits);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement(query_add_equipment_to_invoice);
			
			ps.setString(1, query_get_invoice_id);
			ps.setString(2, query_get_sold_product_id);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Adds a particular service (corresponding to productCode to an 
	 * invoice corresponding to the provided invoiceCode with the given
	 * begin/end dates)
	 */
	public static void addServiceToInvoice(String invoiceCode, String productCode, String startDate, String endDate) {
		String query_add_service_to_invoice = "INSERT INTO ProductList (InvoiceID, SoldProductID) VALUES (?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			String query_get_invoice_id = "(SELECT InvoiceID FROM Invoice WHERE InvoiceCode = '"+invoiceCode+"')";
			String query_get_product_id = "(SELECT ProductID FROM Product WHERE ProductCode = '"+productCode+"' )";
			String query_add_sold_product = "INSERT INTO SoldProduct (ProductID, StartDate, EndDate) "
					+ "VALUES (?, ?, ?);";
			String query_get_sold_product_id = "(SELECT LAST_INSERT_ID())";
			
			PreparedStatement ps = conn.prepareStatement(query_add_sold_product);
			
			ps.setString(1, query_get_product_id);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement(query_add_service_to_invoice);
			
			ps.setString(1, query_get_invoice_id);
			ps.setString(2, query_get_sold_product_id);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**Adds a particular consultation (corresponding to productCode to an 
	 * invoice corresponding to the provided invoiceCode with the given
	 * number of billable hours.)
	 */
	public static void addConsultationToInvoice(String invoiceCode, String productCode, double numHours) {
		String query_add_consultation_to_invoice = "INSERT INTO ProductList (InvoiceID, SoldProductID) VALUES (?, ?);";
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			String query_get_invoice_id = "(SELECT InvoiceID FROM Invoice WHERE InvoiceCode = '"+invoiceCode+"')";
			String query_get_product_id = "(SELECT ProductID FROM Product WHERE ProductCode = '"+productCode+"' )";
			String query_add_sold_product = "INSERT INTO SoldProduct (ProductID, NumHours) "
					+ "VALUES (?, ?);";
			String query_get_sold_product_id = "(SELECT LAST_INSERT_ID())";
			
			PreparedStatement ps = conn.prepareStatement(query_add_sold_product);
			
			ps.setString(1, query_get_product_id);
			ps.setDouble(2, numHours);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement(query_add_consultation_to_invoice);
			
			ps.setString(1, query_get_invoice_id);
			ps.setString(2, query_get_sold_product_id);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}