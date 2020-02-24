package com.vaj.shoppingcart.model.warehouse;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.order.Order;

import java.text.DecimalFormat;

public class Financials {

  private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

  private final ShoppingCart shoppingCart;

  public Financials(ShoppingCart shoppingCartIn) {
    this.shoppingCart = shoppingCartIn;
  }

  /**
   * Retrieves the total profit from all orders.
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
   * @return double, returns the net profit(subtotal) of all orders
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
   * Gets the total number of invoices.
   *
   * @return int, returns the total amount of invoices
   */
  public int getTotalInvoices() {
    return shoppingCart.getInvoiceDatabase().getInvoiceTreeMap().size();
  }

  /**
   * Gets the total number of orders.
   *
   * @return int, returns the total amount of orders
   */
  public int getTotalOrders() {
    return shoppingCart.getOrderDatabase().getOrderTreeMap().size();
  }

}
