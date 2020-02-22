package com.vaj.shoppingcart.model.order;

import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.database.InvoiceDatabase;

public class Invoice {
	
	private final int invoiceIdentifier;
	public  Order order;
	public User user;
	
	
	public Invoice(Order order, User user) {
		super();
		this.order = order;
		this.invoiceIdentifier = InvoiceDatabase.INVOICE_ID++;
		this.user = user;
	}
	

	public int getInvoiceIdentifier() {
		return invoiceIdentifier;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}
}
