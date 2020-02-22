package com.vaj.shoppingcart.model.order;

import java.util.ArrayList;

public class OrderHistory {

  private ArrayList<Integer> orders;

  public OrderHistory() {
    this.orders = new ArrayList<>();
  }

  public Integer getSpecificOrder(int orderNumber) {
    return this.orders.get(orderNumber);
  }

  public void setOrder(ArrayList<Integer> orders) {
    this.orders = orders;
  }

  public void addOrder(int order) {
    orders.add(order);
  }

  public ArrayList<Integer> getOrders() {
    return orders;
  }

}
