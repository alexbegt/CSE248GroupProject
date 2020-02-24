package com.vaj.shoppingcart.model.home;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.Cart;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.order.Invoice;
import com.vaj.shoppingcart.model.order.Order;

import java.util.HashMap;

public class Home {

  private User currentUserLoggedIn;
  private final ShoppingCart shoppingCart;

  public Home(ShoppingCart cart) {
    this.shoppingCart = cart;
    this.currentUserLoggedIn = null;
  }

  public ShoppingCart getShoppingCart() {
    return this.shoppingCart;
  }

  public void setCurrentUserLoggedIn(User currentUserLoggedIn) {
    this.currentUserLoggedIn = currentUserLoggedIn;
  }

  public User getCurrentUserLoggedIn() {
    return this.currentUserLoggedIn;
  }

  /**
   * Gets the total number of users.
   *
   * @return int, returns the total amount of orders
   */
  public int getTotalUsers() {
    return this.getShoppingCart().getUserDatabase().getUserTreeMap().size();
  }


  /**
   * Gets the total number of products
   *
   * @return int, the total number of products.
   */
  public int getTotalProducts() {
    return this.getShoppingCart().getProductDatabase().getProductTreeMap().size();
  }

  /**
   * Gets the total number of invoices.
   *
   * @return int, returns the total amount of invoices
   */
  public int getTotalInvoices() {
    return this.getShoppingCart().getInvoiceDatabase().getInvoiceTreeMap().size();
  }

  /**
   * Gets the total number of orders.
   *
   * @return int, returns the total amount of orders
   */
  public int getTotalOrders() {
    return this.getShoppingCart().getOrderDatabase().getOrderTreeMap().size();
  }

  public void processOrder() {
    Cart cart = this.getCurrentUserLoggedIn().getCart();
    Order order = new Order(cart.getCurrentCartSubTotal(), cart.getCurrentTax(), cart.getCurrentTotal(), new HashMap<>(cart.getCurrentItemsInCart()), getCurrentUserLoggedIn().getUsername());

    this.getCurrentUserLoggedIn().setCurrentOrderNumber(order.getOrderNumber());
    this.getShoppingCart().getOrderDatabase().addOrder(order);
    this.getCurrentUserLoggedIn().addOrderToHistory(order.getOrderNumber());

    this.createInvoice();
  }

  private void createInvoice() {
    Invoice invoice = new Invoice(this.getCurrentUserLoggedIn().getCurrentOrderNumber(), this.getCurrentUserLoggedIn().getUsername());

    this.getShoppingCart().getInvoiceDatabase().addInvoice(invoice);

    this.getCurrentUserLoggedIn().setCurrentInvoiceNumber(invoice.getInvoiceNumber());
    this.getCurrentUserLoggedIn().getCart().clearCart();
    this.getCurrentUserLoggedIn().addInvoiceToHistory(invoice.getInvoiceNumber());
  }
}
