package com.github.bhottovy.dataconverter;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

public class FileExport {
	
	public static void exportJSON(Object object, String fileName) {
		
		//Using GSON library, convert the list of objects into a json file.
		//First attempt to open/create the requested file.
		try {
			Writer out = new FileWriter(fileName + ".json");
			
			//Create the GsonBuilder. Set to pretty printing to add indentations.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(object, out);
			
			//Finally close the file.
			out.close();
		} catch(IOException e) {
			//Could not open file, print stack trace.
			e.printStackTrace();
		}
	}
	
	public static void exportXML(Object object, String fileName) {
		
		//Using XStream library, convert object to xml, then write to file.
		XStream xstream = new XStream();
		
		//Process annotations in list objects.
		xstream.autodetectAnnotations(true);
		String xml = xstream.toXML(object);

		//Attempt to write to file.
		try {
			FileOutputStream out = new FileOutputStream(fileName + ".xml");
			
			out.write("<?xml version=\"1.0\" ?>\n".getBytes("UTF-8"));
			byte[] xmlText = xml.getBytes("UTF-8");
			out.write(xmlText);
			
			out.close();
		} catch(Exception e) {
			//Failed to write to file. Print the stack trace.
			e.printStackTrace();
		}
	}
}