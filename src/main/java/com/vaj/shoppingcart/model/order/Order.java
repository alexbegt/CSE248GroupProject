package com.vaj.shoppingcart.model.order;

import com.vaj.shoppingcart.model.database.OrderDatabase;

import java.util.HashMap;

public class Order {

  private final double subTotal;
  private final double tax;
  private final double total;
  private HashMap<String, Integer> cartProducts;
  private final int orderNumber;
  private OrderStatus orderStatus;

  public Order(double subTotalIn, double taxIn, double totalIn, HashMap<String, Integer> cartProducts) {
    this.orderNumber = OrderDatabase.ORDER_ID++;
    this.subTotal = subTotalIn;
    this.tax = taxIn;
    this.total = totalIn;
    this.cartProducts = cartProducts;
    this.orderStatus = OrderStatus.PENDING;
  }

  public double getSubTotal() {
    return this.subTotal;
  }

  public double getTax() {
    return this.tax;
  }

  public double getTotal() {
    return this.total;
  }

  public int getOrderNumber() {
    return this.orderNumber;
  }

  public OrderStatus getOrderStatus() {
    return this.orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public HashMap<String, Integer> getProducts() {
    return this.cartProducts;
  }

  public void setProducts(HashMap<String, Integer> products) {
    this.cartProducts = products;
  }
}
