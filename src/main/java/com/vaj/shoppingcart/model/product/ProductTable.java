package com.vaj.shoppingcart.model.product;

public class ProductTable {

  private int productNumber;
  private String productName;
  private String productShortName;
  private int productQuantity;
  private String productPrice;
  private String totalProductPrice;

  public ProductTable(int productNumber, String productName, String productShortName, int productQuantity, String productPrice, String totalProductPrice) {
    this.productNumber = productNumber;
    this.productName = productName;
    this.productShortName = productShortName;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
    this.totalProductPrice = totalProductPrice;
  }

  public ProductTable(int productNumber, String productName, String productShortName, int productOnHands, String productPrice) {
    this(productNumber, productName, productShortName, productOnHands, productPrice, "0.00");
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

  /**
   * Gets the product name
   *
   * @return String the product name.
   */
  public String getProductShortName() {
    return this.productShortName;
  }
}
