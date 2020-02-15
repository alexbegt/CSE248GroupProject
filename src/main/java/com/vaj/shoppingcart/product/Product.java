package com.vaj.shoppingcart.product;

public class Product {

  private final int productIdentifier;
  private final String name;
  private final String description;
  private double price;
  private double salePrice;
  private int onHands;
  private ProductStatus productStatus;

  public Product(String name, String description, double price, int onHands) {
    this.productIdentifier = Database.PRODUCTID++;
    this.name = name;
    this.description = description;
    this.price = price;
    this.salePrice = 0.00;
    this.onHands = onHands;
    this.productStatus = ProductStatus.SELLABLE;
  }

  /*
   * Get's the product ID of the current product.
   *
   * @return the product ID.
   */
  public int getProductIdentifier() {
    return this.productIdentifier;
  }

  /*
   * Get's the name of the current product.
   *
   * @return the product name.
   */
  public String getName() {
    return this.name;
  }

  /*
   * Get's the description of the current product.
   *
   * @return the product description.
   */
  public String getDescription() {
    return this.description;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  /*
   * Get's the price of the current product.
   *
   * @return the product price.
   */
  public double getPrice() {
    return this.price;
  }

  /*
   * Set's the current sale price of the selected item.
   *
   * @param salePrice the current sale price
   * @return TRUE if it passes or FALSE when the price is negative or 0.00;
   */
  public boolean putOnSale(double salePrice) {
    if (salePrice == 0.00) {
      this.takeOffSale();
      return false;
    } else if (salePrice < 0.00) {
      return false;
    } else {
      this.salePrice = salePrice;
      return true;
    }
  }

  /*
   * Take's the current selected item off sale.
   *
   * @return always true to respond back to the controller
   */
  public boolean takeOffSale() {
    this.salePrice = 0.00;
    return true;
  }

  /*
   * Get's the current sale price of the selected item.
   * Note this can be 0.00 which means the item is not on sale.
   *
   * @return the product sale price.
   */
  public double getSalePrice() {
    return this.salePrice;
  }

  /*
   * Sets the current on hands
   *
   * @param onHandsIn the new on hands.
   */
  public void setOnHands(int onHandsIn) {
    this.onHands = onHandsIn;
  }

  /*
   * Get's the current on hands of the selected product.
   *
   * @return the product on hands.
   */
  public int getOnHands() {
    return this.onHands;
  }

  /*
   * Sets the current product status
   *
   * @param productStatusIn the new on hands.
   */
  public void setProductStatus(ProductStatus productStatusIn) {
    this.productStatus = productStatusIn;

    if (productStatusIn == ProductStatus.SALVAGE) {
      this.setPrice(0.00);
      this.setOnHands(0);
    }
  }

  /*
   * Get's the current product status linked to the selected item.
   *
   * @return The product status. It will either be SELLABLE, DISCONTINUED, CLEARANCE, or SALVAGE.
   */
  public ProductStatus getProductStatus() {
    return this.productStatus;
  }
}
