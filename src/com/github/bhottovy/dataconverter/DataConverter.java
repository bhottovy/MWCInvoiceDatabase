package com.github.bhottovy.dataconverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Persons;

public class DataConverter {
	public static void main (String args[]) throws FileNotFoundException{
		
		File file1 = new File("data/Customers.dat");
		File file2 = new File("data/Persons.dat");
		File file3 = new File("data/Products.dat");
		Scanner FS = new Scanner(file1);
		Scanner FS2 = new Scanner(file2);
		Scanner FS3 = new Scanner(file3);
		
		Map<Integer, Persons> map = new HashMap<Integer, Persons>();
		
		for (int i=0; i<map.size(); ++i){
			map.put(Persons., value)
		}

			   
	}
}
