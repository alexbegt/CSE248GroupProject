package com.vaj.shoppingcart.model.order;

import java.util.HashMap;

public class Order {
	private final double subTotal;
	private final double tax;
	private final double total;
	private HashMap<String, Integer> cartProducts;
	private final int orderNumber = 0;
	private OrderStatus orderStatus;

	
	public Order(double subTotal, HashMap<String, Integer> cartProducts) {
		this.subTotal = subTotal;
		this.tax = subTotal * (8.25/100);
		this.total = subTotal + tax;
		this.cartProducts = cartProducts;
		this.orderStatus = OrderStatus.PENDING;
	}


	public double getSubTotal() {
		return subTotal;
	}

	public double getTotal() {
		return total;
	}

	public HashMap<String, Integer> getProducts() {
		return cartProducts;
	}

	public void setProducts(HashMap<String, Integer> products) {
		this.cartProducts = products;
	}

	public int getOrderNumber() {
		return orderNumber;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	
	
}
