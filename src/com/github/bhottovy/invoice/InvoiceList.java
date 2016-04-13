package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.information.Invoice;

public class InvoiceList {

	private InvoiceListNode start;
	private int size=0;
	private InvoiceListNode end;

	public void clear() {
	}

	public void addToStart(Invoice invoice) {
		InvoiceListNode a = new InvoiceListNode(invoice);
		size++;
		if(start == null){
			start = a;
			end = start;
		}
		else {
			a.setNext(start);
			start = a;
		}
	}

	public void addToEnd(Invoice invoice) {
		InvoiceListNode a = new InvoiceListNode(invoice);
		size++;
		if(start == null){
			start = a;
			end = start;
		}
		else {
			end.setNext(a);
			end = a;
		}
	}

	public void remove(int position) {
		InvoiceListNode n;
		if(position != 0 && position != size) {
			n = getInvoiceListNode(position);
			n.setNext(n.getNext().getNext());
		}
		else if(position == 0) {
			n  = getInvoiceListNode(position);
			start = n.getNext();
		}
		else if(position == size){
			n  = getInvoiceListNode(position-1);
			end = n;
		}
		size--;
	}

	private InvoiceListNode getInvoiceListNode(int position) {
		InvoiceListNode a = start;
		if (position <= size){
			for(int i = 0; i < position; i++){
				a = a.getNext();
			}
		}
		else {
			throw new UnsupportedOperationException("Position is larger than size");
		}
		return a;
	}

	public Invoice getInvoice(int position) {
		InvoiceListNode a = start;
		Invoice t;
		if (position <= size){
			for(int i = 0; i < position; i++){
				a = a.getNext();
			}
			t = a.getInvoice();
		}
		else {
			throw new UnsupportedOperationException("Position is larger than size");
		}		
		return t;
	}

	public void print() {
		for(int i = 0; i<size;i++){
			System.out.println(getInvoice(i).toString());
		}
	}
	
	public int size() {
		return this.size;
	}
}