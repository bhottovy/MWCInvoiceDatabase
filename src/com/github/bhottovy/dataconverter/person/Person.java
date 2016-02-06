package com.github.bhottovy.dataconverter.person;

import java.util.ArrayList;

import com.github.bhottovy.dataconverter.information.Address;

public class Person {

    private String code;
    
    private String firstName;
    private String lastName;
    private Address address;
    
    private ArrayList<String> emailList = new ArrayList<String>();
    
    public Person(String code, String firstName, String lastName, Address address) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    //Changed
    public String getCode() {
        return this.code;
    }
    
    public String getName() {
        return firstName + " " + lastName;
    }
    
    public Address getAddress() {
        return this.address;
    }
}