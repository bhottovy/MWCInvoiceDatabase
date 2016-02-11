package com.github.bhottovy.dataconverter.person;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("persons")
public class Persons {

	//Contains list of Person objects.
	@XStreamImplicit
	private ArrayList<Person> persons;
	
	public void setList(ArrayList<Person> persons) {
		this.persons = persons;
	}
	
	public ArrayList<Person> getList() {
		return this.persons;
	}
	
	public Person getPersonFromCode(String code) {
		for(Person person : this.persons) {
			if(person.getCode().equalsIgnoreCase(code)) {
				return person;
			}
		}
		
		return null;
	}
}
