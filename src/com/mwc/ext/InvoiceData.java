package com.mwc.ext;

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
}
	
	/**Method to add a person record to the database with the provided data. 
	 */
	public static void addPerson(String personCode, String firstName, String lastName, 
			String street, String city, String state, String zip, String country) {}
	
	/**Method to add an email record to the database with the associated personCode. 
	 */
	public static void addEmail(String personCode, String email) {
	}
	
	/**Method that removes every customer record from the database. 
	 */
	public static void removeAllCustomers() {
	}
	
	/**Method to add a customer record to the database with the provided data
	 */
	public static void addCustomer(String customerCode, String type, String primaryContactPersonCode, String name, 
			String street, String city, String state, String zip, String country) {
	}
	
	/**Removes all product records from the database. 
	 */
	public static void removeAllProducts() {
	}
	
	/**Adds an equipment record to the database with the provided data.
	 */
	public static void addEquipment(String productCode, String name, Double pricePerUnit) {
	}

	
	/**Adds a service record to the database with the provided data.
	 */
	public static void addService(String productCode, String name, double activationFee, double annualFee) {
	}
	
	/**Adds an consultation record to the database with the provided data.
	 */
	public static void addConsultation(String productCode, String name, String consultantPersonCode, Double hourlyFee) {
	}
	
	/**Removes all invoice records from the database. 
	 */
	public static void removeAllInvoices() {
	}
	
	/**Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String invoiceDate, String salesPersonCode) {
	}
	
	
	/**Adds a particular equipment (corresponding to productCode to an 
	 * invoice corresponding to the provided invoiceCode with the given
	 * number of units)
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String productCode, int numUnits) {
	}
	
	/**Adds a particular service (corresponding to productCode to an 
	 * invoice corresponding to the provided invoiceCode with the given
	 * begin/end dates)
	 */
	public static void addServiceToInvoice(String invoiceCode, String productCode, String startDate, String endDate) {
		}

	/**Adds a particular consultation (corresponding to productCode to an 
	 * invoice corresponding to the provided invoiceCode with the given
	 * number of billable hours.)
	 */
	public static void addConsultationToInvoice(String invoiceCode, String productCode, double numHours) {
		}
}