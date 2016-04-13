package com.github.bhottovy.invoice;

import com.github.bhottovy.dataconverter.information.Invoice;

public class InvoiceListNode {

	private InvoiceListNode next;
    private Invoice invoice;

    public InvoiceListNode(Invoice invoice) {
        this.invoice = invoice;
        this.next = null;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public InvoiceListNode getNext() {
        return next;
    }

    public void setNext(InvoiceListNode next) {
        this.next = next;
    }
	
}
