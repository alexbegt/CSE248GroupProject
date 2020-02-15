package com.vaj.shoppingcart.user;

import java.util.ArrayList;

public class OrderHistory {
	private ArrayList<Order> orders;

	public ArrayList<Order> getSpecificOrder(int orderNumber) {
		//retrieves order number from order list
		//gets specific order
		
	}

	public void setOrder(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
	
}
