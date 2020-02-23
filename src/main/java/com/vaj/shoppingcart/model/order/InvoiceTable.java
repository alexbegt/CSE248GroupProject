package com.vaj.shoppingcart.model.order;

public class InvoiceTable {
  private int productNumber;
  private String productName;
  private int productQuantity;
  private String productPrice;
  private String totalProductPrice;

  public InvoiceTable(int productNumber, String itemName, int productQuantity, String productPrice, String totalProductPrice) {
    this.productNumber = productNumber;
    this.productName = itemName;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
    this.totalProductPrice = totalProductPrice;
  }

  public String getProductPrice() {
    return this.productPrice;
  }

  public int getProductNumber() {
    return this.productNumber;
  }

  public int getProductQuantity() {
    return this.productQuantity;
  }

  public String getProductName() {
    return this.productName;
  }

  public String getTotalProductPrice() {
    return this.totalProductPrice;
  }
}
