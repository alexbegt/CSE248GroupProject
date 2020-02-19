package com.vaj.shoppingcart.model.order;

import java.util.ArrayList;

public class OrderHistory {

  private ArrayList<Order> orders;

  public Order getSpecificOrder(int orderNumber) {
    //retrieves order number from order list
    //gets specific order
    return this.orders.get(orderNumber);
  }

  public void setOrder(ArrayList<Order> orders) {
    this.orders = orders;
  }

  public ArrayList<Order> getOrders() {
    return orders;
  }

}
