package com.vaj.shoppingcart.model.order;

import java.util.HashMap;

public class Order {
	private final double subTotal;
	private final double tax;
	private final double total;
	private HashMap<String, Integer> products;
	public static int INVOICE_NUMBER = 0;
	private OrderStatus orderStatus;

	
	public Order(double subTotal, HashMap<String, Integer> products) {
		super();
		this.subTotal = subTotal;
		this.tax = subTotal * (8.25/100);
		this.total = subTotal + tax;
		this.products = products;
		this.orderStatus = OrderStatus.PENDING;
	}

	public float generateInvoiceNumber() {
		return INVOICE_NUMBER++;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public double getTotal() {
		return total;
	}

	public HashMap<String, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<String, Integer> products) {
		this.products = products;
	}

	public int getInvoiceNumber() {
		return INVOICE_NUMBER;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.INVOICE_NUMBER = invoiceNumber;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	
	
}
