package com.vaj.shoppingcart.model.order;

import com.vaj.shoppingcart.model.database.InvoiceDatabase;

public class Invoice {

  private final int invoiceIdentifier;
  private final int orderNumber;
  private final String username;

  public Invoice(int orderNumber, String username) {
    this.invoiceIdentifier = InvoiceDatabase.INVOICE_ID++;
    this.orderNumber = orderNumber;
    this.username = username;
  }

  /**
   * Gets the invoice numbers.
   *
   * @return the invoice number
   */
  public int getInvoiceNumber() {
    return this.invoiceIdentifier;
  }

  /**
   * Gets the order numbers.
   *
   * @return the order number
   */
  public int getOrderNumber() {
    return this.orderNumber;
  }

  /**
   * Gets the username.
   *
   * @return the invoice username
   */
  public String getUserName() {
    return this.username;
  }
}
