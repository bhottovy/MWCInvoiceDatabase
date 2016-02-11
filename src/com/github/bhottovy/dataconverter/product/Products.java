package com.github.bhottovy.dataconverter.product;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("products")
public class Products {
	
	//Contains list of Product objects.
		@XStreamImplicit
		private ArrayList<Product> products;
		
		public void setList(ArrayList<Product> products) {
			this.products = products;
		}
		
		public ArrayList<Product> getList() {
			return this.products;
		}
}
