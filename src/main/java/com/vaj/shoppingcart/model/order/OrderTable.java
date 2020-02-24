package com.vaj.shoppingcart.model.order;

public class OrderTable {

  private int orderNumber;
  private int invoiceNumber;
  private String subTotal;
  private String tax;
  private String total;

  public OrderTable(int orderNumber, int invoiceNumber, String subTotal, String tax, String total) {
    this.orderNumber = orderNumber;
    this.invoiceNumber = invoiceNumber;
    this.subTotal = subTotal;
    this.tax = tax;
    this.total = total;
  }

  public int getOrderNumber() {
    return this.orderNumber;
  }

  public int getInvoiceNumber() {
    return this.invoiceNumber;
  }

  public String getSubTotal() {
    return this.subTotal;
  }

  public String getTax() {
    return this.tax;
  }

  public String getTotal() {
    return this.total;
  }
}
