package com.vaj.shoppingcart.user;

import java.util.HashMap;

public class Order {
	private double subTotal;
	private double tax;
	private double total;
	private HashMap<String, Integer> products;
	private int invoiceNumber;
	private OrderStatus orderStatus;
	private DateAndTime orderDateAndTime;
	
	public Order(double subTotal, double tax, double total, HashMap<String, Integer> products, int invoiceNumber,
			OrderStatus orderStatus, DateAndTime orderDateAndTime) {
		super();
		this.subTotal = subTotal;
		this.tax = tax;
		this.total = total;
		this.products = products;
		this.invoiceNumber = invoiceNumber;
		this.orderStatus = orderStatus;
		this.orderDateAndTime = orderDateAndTime;
	}

	public float generateInvoiceNumber() {
		//this method will generate a unique random number x numbers long
		return invoiceNumber;
		
	}
	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public HashMap<String, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<String, Integer> products) {
		this.products = products;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public DateAndTime getOrderDateAndTime() {
		return orderDateAndTime;
	}

	public void setOrderDateAndTime(DateAndTime orderDateAndTime) {
		this.orderDateAndTime = orderDateAndTime;
	}
	
	
}
