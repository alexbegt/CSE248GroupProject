package com.vaj.shoppingcart.model.order;

import com.vaj.shoppingcart.model.database.OrderDatabase;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Order {

  private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

  private final double subTotal;
  private final double tax;
  private final double total;
  private HashMap<String, Integer> cartProducts;
  private final int orderNumber;
  private OrderStatus orderStatus;
  private final String username;


  public Order(double subTotalIn, double taxIn, double totalIn, HashMap<String, Integer> cartProducts, String username) {
    this.orderNumber = OrderDatabase.ORDER_ID++;
    this.subTotal = Double.parseDouble(decimalFormat.format(subTotalIn));
    this.tax = Double.parseDouble(decimalFormat.format(taxIn));
    this.total = Double.parseDouble(decimalFormat.format(totalIn));
    this.cartProducts = cartProducts;
    this.orderStatus = OrderStatus.PENDING;
    this.username = username;
  }

  /**
   * retrieves the subtotal
   * 
   * @return double, returns the total.
   */
  public double getSubTotal() {
    return this.subTotal;
  }

  /**
   * retrieves the tax
   * 
   * @return double, returns the tax.
   */
  public double getTax() {
    return this.tax;
  }

  /**
   * retrieves the total
   * 
   * @return double, returns total.
   */
  public double getTotal() {
    return this.total;
  }

  /**
   * retrieves the order number
   * 
   * @return int, retrieves order number.
   */
  public int getOrderNumber() {
    return this.orderNumber;
  }

  /**
   * retrieves the order status
   * 
   * @return OrderStatus, returns the status of the order in the from of OrderStatus Interface.
   */
  public OrderStatus getOrderStatus() {
    return this.orderStatus;
  }

  /**
   * sets the order status of the order
   * 
   */
  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * retrieves the products from cart
   * 
   * @return HashMap, returns the hashmap of products
   */
  public HashMap<String, Integer> getProducts() {
    return this.cartProducts;
  }

  /**
   * sets products to products in cart
   * 
   */
  public void setProducts(HashMap<String, Integer> products) {
    this.cartProducts = products;
  }
  
  /**
   * checks if cart is empty
   * 
   * @return boolean, if cart is empty returns true, if cart has items, returns false.
   */
  public boolean isEmpty() {
	  return this.cartProducts.isEmpty();
  }
  
  /**
   * retrieves the username
   * 
   * @return String, returns the username;
   */
  public String getUsername() {
	  return username;
  }
}
