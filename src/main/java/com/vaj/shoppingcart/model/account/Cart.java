package com.vaj.shoppingcart.model.account;

import java.util.List;

import com.vaj.shoppingcart.model.product.Product;

public class Cart {
	
	private List<Product> currentItemsInCart;
	private double currentCartSubTotal;
	private double currentTax;
	private double currentTotal;
	
	public Cart() {
		
	}
	
	public void switchToProcessOrder() {
		//Need to write the switch from screens or whatever we're doing with this
	}
	
	public void clearCart() {
		currentItemsInCart.clear();
	}
	
	public void addItemToCart(Product product) {
		product.getPrice();
		currentCartSubTotal += product.getPrice();
		currentTax += (product.getPrice() * 0.08625);
		currentTotal = (currentCartSubTotal + currentTax);
		currentItemsInCart.add(product);
	}
	
	public void removeItemFromCart(Product product) {
		currentCartSubTotal -= product.getPrice();
		currentTax -= (product.getPrice() * 0.08625);
		currentTotal = (currentCartSubTotal + currentTax);
		currentItemsInCart.remove(product);
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
	
	public List<Product> getCurrentItemsInCart() {
		return currentItemsInCart;
	}
}
