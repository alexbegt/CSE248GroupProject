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
		while(!currentItemsInCart.isEmpty()) {
			currentItemsInCart.remove(0);
			}
		}
	
	public void addItemToCart(String product) {
		if(ShoppingCart.getInstance().getDatabase().getProduct(product) != null) {
			ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice();
			currentCartSubTotal += ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice();
			currentTax += ((ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice()) * 0.08625);
			currentTotal = (currentCartSubTotal + currentTax);
			currentItemsInCart.add(product);
		}
	}
	
	public void removeItemFromCart(String product) {
		if(ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice()) {
			ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice();
			currentCartSubTotal -= ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice();
			currentTax -= (ShoppingCart.getInstance().getDatabase().getProduct(product).getPrice() * 0.08625);
			currentTotal = (currentCartSubTotal + currentTax);
			currentItemsInCart.remove(product);
		}
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
