package com.github.bhottovy.dataconverter;

import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.github.bhottovy.dataconverter.person.Customer;
import com.github.bhottovy.dataconverter.person.Person;
import com.github.bhottovy.dataconverter.product.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileExport {

	private static final String FILE_NAME = "OUTPUT";
	
	public static void exportJSON(Map<String, Person> persons, Map<String, Customer> customers, Map<String, Product> products) {
		
		try {
			Writer out = new FileWriter(FILE_NAME + ".json");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			gson.toJson(persons.values(), out);
			
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void exportXML(Map<String, Person> persons, Map<String, Customer> customers, Map<String, Product> products) {
		
	}
}