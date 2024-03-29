package com.vaj.shoppingcart.model.product;

import com.vaj.shoppingcart.model.database.ProductDatabase;

public class Product {

  private final int productIdentifier;
  private final String shortName;
  private String name;
  private String description;
  private double price;
  private double salePrice;
  private int onHands;
  private ProductStatus productStatus;
  private String photoPath;

  public Product(String shortName, String name, String description, double price, int onHands, String photoPath) {
    this.productIdentifier = ProductDatabase.PRODUCT_ID++;
    this.shortName = shortName;
    this.name = name;
    this.description = description;
    this.price = price;
    this.salePrice = 0.00;
    this.onHands = onHands;
    this.productStatus = ProductStatus.SELLABLE;
    this.photoPath = photoPath;
  }

  /*
   * Gets the short name of the current product.
   *
   * @return the product short name.
   */
  public String getShortName() {
    return this.shortName;
  }

  /*
   * Gets the product ID of the current product.
   *
   * @return the product ID.
   */
  public int getProductIdentifier() {
    return this.productIdentifier;
  }

  /*
   * Sets the new name
   *
   * @param nameIn the new name.
   */
  public void setName(String nameIn) {
    this.name = nameIn;
  }

  /*
   * Gets the name of the current product.
   *
   * @return the product name.
   */
  public String getName() {
    return this.name;
  }

  /*
   * Sets the new description
   *
   * @param descriptionIn the new name.
   */
  public void setDescription(String descriptionIn) {
    this.description = descriptionIn;
  }

  /*
   * Gets the description of the current product.
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
   * Gets the price of the current product.
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
   * Gets the current sale price of the selected item.
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
   * Gets the current on hands of the selected product.
   *
   * @return the product on hands.
   */
  public int getOnHands() {
    return this.onHands;
  }

  /*
   * Sets the current product status
   *
   * @param productStatusIn the new product status.
   */
  public void setProductStatus(ProductStatus productStatusIn) {
    this.productStatus = productStatusIn;

    if (productStatusIn == ProductStatus.SALVAGE) {
      this.setPrice(0.00);
      this.setOnHands(0);
    }
  }

  /*
   * Gets the current product status linked to the selected item.
   *
   * @return The product status. It will either be SELLABLE, DISCONTINUED, CLEARANCE, or SALVAGE.
   */
  public ProductStatus getProductStatus() {
    return this.productStatus;
  }

  /*
   * Sets the current product photo
   *
   * @param photoPathIn the new photo.
   */
  public void setPhotoPath(String photoPathIn) {
    this.photoPath = photoPathIn;
  }

  /*
   * Gets the current product photo linked to the selected item.
   *
   * @return The path of the current photo to display for the selected item.
   */
  public String getPhotoPath() {
    return this.photoPath;
  }
}
