package com.vaj.shoppingcart.model.account;

import java.util.ArrayList;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.product.Product;

public class Cart {
	
	private ArrayList<String> currentItemsInCart;
	private double currentCartSubTotal;
	private double currentTax;
	private double currentTotal;
	
	public Cart() {
	    this.currentItemsInCart = new ArrayList<String>();
	    this.currentCartSubTotal = 0.00;
	    this.currentTax = 0.00;
	    this.currentTotal = 0.0;
	}
	
	public void clearCart() {
		currentItemsInCart.clear();
	}
	
	public boolean addItemToCart(String name) {
		Product product = ShoppingCart.getInstance().getDatabase().getProduct(name);
		if(product != null) {
			currentCartSubTotal += product.getPrice();
			currentTax += product.getPrice() * 0.08625;
			currentTotal = (currentCartSubTotal + currentTax);
			currentItemsInCart.add(name);
			return true;
		}
		return false;
	}
	
	public boolean removeItemFromCart(String name) {
		Product product = ShoppingCart.getInstance().getDatabase().getProduct(name);
		if(product != null && currentItemsInCart.contains(name)) {
			currentCartSubTotal -= product.getPrice();
			currentTax -= product.getPrice() * 0.08625;
			currentTotal = (currentCartSubTotal + currentTax);
			currentItemsInCart.remove(name);
			return true;
		}
		return false;
	}
	
	public double getCurrentCartSubTotal() {
		return currentCartSubTotal;
	}
	
	public double getCurrentTax() {
		return currentTax;
	}
	
	public double getCurrentTotal() {
		return currentTotal;
	}
	
	public ArrayList<String> getCurrentItemsInCart() {
		return currentItemsInCart;
	}
}
