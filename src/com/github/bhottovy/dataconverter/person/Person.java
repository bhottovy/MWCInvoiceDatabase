package com.github.bhottovy.dataconverter.person;

import java.util.ArrayList;

import com.github.bhottovy.dataconverter.information.Address;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("person")
public class Person extends Contact implements PersonCode {
    
	//A Person's name is split into a first and last part.
    private String firstName;
    private String lastName;
    
    //A Person also has a list of emails.
    private ArrayList<String> email;
    
    public Person(String code, Address address, ArrayList<String> email, String firstName, String lastName) {
    	super(code, address);
    	
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String getName() {
    	return this.firstName + " " + this.lastName;
    }
    
    public String getFirstName() {
    	return this.firstName;
    }
    
    public String getLastName() {
    	return this.lastName;
    }
    
    public ArrayList<String> getEmails() {
    	return this.email;
    }

	@Override
	public String getCode() {
		return this.code;
	}
}