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

  /**
   * Gets the product price
   *
   * @return String the product price.
   */
  public String getProductPrice() {
    return this.productPrice;
  }

  /**
   * Gets the product number
   *
   * @return String the product number.
   */
  public int getProductNumber() {
    return this.productNumber;
  }

  /**
   * Gets the product quantity
   *
   * @return String the product quantity.
   */
  public int getProductQuantity() {
    return this.productQuantity;
  }

  /**
   * Gets the product name
   *
   * @return String the product name.
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * Gets the product total price
   *
   * @return String the product total price.
   */
  public String getTotalProductPrice() {
    return this.totalProductPrice;
  }
}
