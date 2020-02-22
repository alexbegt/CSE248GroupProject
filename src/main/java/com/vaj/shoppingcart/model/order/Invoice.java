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

  public int getInvoiceNumber() {
    return this.invoiceIdentifier;
  }

  public int getOrderNumber() {
    return this.orderNumber;
  }

  public String getUserName() {
    return this.username;
  }
}
