package com.vaj.shoppingcart.model.account;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.product.Product;

import java.util.HashMap;

public class Cart {

  private HashMap<String, Integer> currentItemsInCart;
  private double currentCartSubTotal;
  private double currentTax;
  private double currentTotal;

  public Cart() {
    this.currentItemsInCart = new HashMap<>();
    this.currentCartSubTotal = 0.00;
    this.currentTax = 0.00;
    this.currentTotal = 0.0;
  }

  public void clearCart() {
    this.currentItemsInCart.clear();
    this.currentCartSubTotal = 0.00;
    this.currentTax = 0.00;
    this.currentTotal = 0.0;
  }

  public boolean addItemToCart(String name) {
    return this.addItemToCart(name, 1);
  }

  public boolean addItemToCart(String name, int quantity) {
    Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(name);

    if (product != null) {
      this.currentCartSubTotal += (product.getPrice() * quantity);
      this.currentTax += (product.getPrice() * quantity) * 0.08625;
      this.currentTotal = (currentCartSubTotal + currentTax);

      this.currentItemsInCart.put(name, quantity);
      return true;
    }

    return false;
  }

  public boolean updateQuantity(String name, int quantity) {
    Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(name);

    if (product != null && this.currentItemsInCart.containsKey(name)) {
      this.handleUpdatingTotalsWithQuantity(product, name);

      this.currentCartSubTotal += (product.getPrice() * quantity);
      this.currentTax += (product.getPrice() * quantity) * 0.08625;
      this.currentTotal = (currentCartSubTotal + currentTax);

      this.currentItemsInCart.replace(name, quantity);

      return true;
    }

    return false;
  }

  public boolean removeItemFromCart(String name) {
    return this.removeItemFromCart(name, 1);
  }

  public boolean removeItemFromCart(String name, int quantity) {
    Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(name);

    if (product != null && this.currentItemsInCart.containsKey(name)) {
      this.currentCartSubTotal -= (product.getPrice() * quantity);
      this.currentTax -= (product.getPrice() * quantity) * 0.08625;
      this.currentTotal = (currentCartSubTotal + currentTax);

      this.currentItemsInCart.remove(name);

      return true;
    }

    return false;
  }

  public void handleUpdatingTotalsWithQuantity(Product product, String name) {
    int oldQuantity = this.currentItemsInCart.get(name);
    double oldPrice = product.getPrice() * oldQuantity;
    double oldTax = oldPrice * 0.08625;

    this.currentCartSubTotal -= oldPrice;
    this.currentTax -= oldTax;
  }

  public boolean hasProductInCart(String shortName) {
    return this.currentItemsInCart.containsKey(shortName);
  }

  public double getCurrentCartSubTotal() {
    return this.currentCartSubTotal;
  }

  public double getCurrentTax() {
    return this.currentTax;
  }

  public double getCurrentTotal() {
    return this.currentTotal;
  }

  public HashMap<String, Integer> getCurrentItemsInCart() {
    return this.currentItemsInCart;
  }
}
