package com.vaj.shoppingcart.model.warehouse;

import java.text.DecimalFormat;
import java.util.TreeMap;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.order.Order;

public class Financials {
	
	private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	private final ShoppingCart shoppingCart;
	
	public Financials(ShoppingCart shoppingCartIn) {
	    this.shoppingCart = shoppingCartIn;
	  }
	
	/**
	   * Retrieves the total profit from all orders.
	   *
	   *
	   * @return double, returns net total of all orders.
	   */
	public double getProfitWithTax() {
	    double localNetTotal = 0.00;

	    for (int i = 0; i < shoppingCart.getOrderDatabase().getOrderTreeMap().size(); i++) {
	      Order temp = shoppingCart.getOrderDatabase().getOrderTreeMap().get(i);
	      localNetTotal += temp.getTotal();
	    }

	    return Double.parseDouble(decimalFormat.format(localNetTotal));
	  }
	
	/**
	   * Retrieves the total tax from all orders.
	   *
	   * 
	   * @return double, returns net taxes of all orders.
	   */
	public double getTaxes() {
	    double localNetTax = 0.00;

	    for (int i = 0; i < shoppingCart.getOrderDatabase().getOrderTreeMap().size(); i++) {
	      Order temp = shoppingCart.getOrderDatabase().getOrderTreeMap().get(i);
	      localNetTax += temp.getTax();
	    }

	    return Double.parseDouble(decimalFormat.format(localNetTax));
	  }
	
	/**
	   * Retrieves the total profit from all orders.
	   *
	   * 
	   * @return double, returns the net  profit(subtotal) of all  orders
	   */
	public double getProfit() {
	    double localNetProfit = 0.00;

	    for (int i = 0; i < shoppingCart.getOrderDatabase().getOrderTreeMap().size(); i++) {
	      Order temp = shoppingCart.getOrderDatabase().getOrderTreeMap().get(i);
	      localNetProfit += temp.getSubTotal();
	    }

	    return Double.parseDouble(decimalFormat.format(localNetProfit));
	  }
	
	/**
	   * Adds an order to the database if it doesn't exist.
	   *
	   * 
	   * @return int, returns the total amount of invoices
	   */
	public int getTotalInvoices() {
		int numberOfInvoices = shoppingCart.getInvoiceDatabase().getInvoiceTreeMap().size();
		return numberOfInvoices;
	}
	
	/**
	   * Adds an order to the database if it doesn't exist.
	   *
	   * @param order - Order object passed
	   * @return boolean, if true added successfully. If false added unsuccessfully.
	   */
	public int getTotalOrders() {
		int numberOfOrders = shoppingCart.getOrderDatabase().getOrderTreeMap().size();
		return numberOfOrders;
	}
	
}
