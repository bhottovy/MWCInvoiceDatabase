package com.github.bhottovy.dataconverter.person;

import java.util.ArrayList;

import com.github.bhottovy.dataconverter.information.Address;

public class Person {

    private String personCode;
    
    private String name;
    private Address address;
    
    private ArrayList<String> email = new ArrayList<String>();
    
    public Person(String code, String name, Address address) {
        this.personCode = code;
        this.name = name;
        this.address = address;
    }

    public String getCode() {
        return this.personCode;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Address getAddress() {
        return this.address;
    }
}