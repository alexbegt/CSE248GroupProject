package com.vaj.shoppingcart.model.database;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.order.Order;

import java.util.TreeMap;

public class OrderDatabase {

  public static int ORDER_ID = 0;
  public TreeMap<Integer, Order> orderTreeMap;
  private final ShoppingCart shoppingCart;

  public OrderDatabase(ShoppingCart shoppingCartIn) {
    this.shoppingCart = shoppingCartIn;

    this.orderTreeMap = new TreeMap<Integer, Order>();
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Adds an order to the database if it doesn't exist.
   *
   * @param order - Order object passed
   * @return boolean, if true added successfully. If false added unsuccessfully.
   */
  public boolean addOrder(Order order) {
    if (this.findOrder(order.getOrderNumber())) {
      return false;
    } else {
      this.orderTreeMap.put(order.getOrderNumber(), order);
      return true;
    }
  }

  /**
   * removes an order with the given order number.
   *
   * @param orderNumber number - key value to search for
   * @return If the order is found, removes order and returns true. If false, not found in the TreeMap.
   */
  public boolean removeOrder(int orderNumber) {
    Order order = this.orderTreeMap.remove(orderNumber);

    return order != null;
  }

  /**
   * Finds an order with the given order number.
   *
   * @param orderNumber - key value to search for
   * @return If the order is found, returns true. If false, not found in the TreeMap.
   */
  public boolean findOrder(int orderNumber) {
    return this.orderTreeMap.containsKey(orderNumber);
  }

  /**
   * Retrieves an order with the given order number.
   *
   * @param orderNumber - key value to search for
   * @return If the order is found, returns order. If false, null
   */
  public Order getOrder(int orderNumber) {
    return this.orderTreeMap.getOrDefault(orderNumber, null);
  }

  public void setOrderTreeMap(TreeMap<Integer, Order> orderTreeMap) {
    this.orderTreeMap = orderTreeMap;
  }

  public TreeMap<Integer, Order> getOrderTreeMap() {
    return orderTreeMap;
  }
}
