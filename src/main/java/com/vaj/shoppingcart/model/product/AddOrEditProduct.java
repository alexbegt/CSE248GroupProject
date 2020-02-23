package com.vaj.shoppingcart.model.product;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.helper.ProductHelper;

public class AddOrEditProduct {

  private final ShoppingCart shoppingCart;

  public AddOrEditProduct(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  /**
   * Adds a product to the database if does not exist.
   *
   * @param shortName the products short name
   * @param name the products name
   * @param description the products description
   * @param priceIn the products price
   * @param onHandsIn the products on hands
   * @param filePath the products photo path
   * @return AddProductStatus the status returned (ITEM_EXISTS, INVALID_PRICE, INVALID_ON_HANDS, SUCCESSFUL, ERROR)
   */
  public AddProductStatus addProduct(String shortName, String name, String description, String priceIn, String onHandsIn, String filePath) {
    if (this.shoppingCart.getProductDatabase().findProduct(shortName)) {
      return AddProductStatus.ITEM_EXISTS;
    }
    if (!ProductHelper.validatePrice(priceIn)) {
      return AddProductStatus.INVALID_PRICE;
    }
    if (!ProductHelper.validateOnHands(onHandsIn)) {
      return AddProductStatus.INVALID_ON_HANDS;
    }

    double price = Double.parseDouble(priceIn);
    int onHands = Integer.parseInt(onHandsIn);

    Product product = new Product(shortName, name, description, price, onHands, !filePath.equalsIgnoreCase("") ? filePath : "missing_image.png");

    if (this.shoppingCart.getProductDatabase().addProduct(product)) {
      return AddProductStatus.SUCCESSFUL;
    } else {
      return AddProductStatus.ERROR;
    }
  }

  /**
   * Edits a product if it exists.
   *
   * @param shortName the products short name
   * @param name the products name
   * @param description the products description
   * @param priceIn the products price
   * @param onHandsIn the products on hands
   * @param productStatus the product status
   * @param filePath the products photo path
   * @return EditProductStatus the status returned (ITEM_DOESNT_EXIST, INVALID_PRICE, INVALID_ON_HANDS, SUCCESSFUL, ERROR)
   */
  public EditProductStatus editProduct(String shortName, String name, String description, String priceIn, String onHandsIn, ProductStatus productStatus, String filePath) {
    if (!this.shoppingCart.getProductDatabase().findProduct(shortName)) {
      return EditProductStatus.ITEM_DOESNT_EXIST;
    }
    if (!ProductHelper.validatePrice(priceIn)) {
      return EditProductStatus.INVALID_PRICE;
    }
    if (!ProductHelper.validateOnHands(onHandsIn)) {
      return EditProductStatus.INVALID_ON_HANDS;
    }

    double price = Double.parseDouble(priceIn);
    int onHands = Integer.parseInt(onHandsIn);

    Product product = this.shoppingCart.getProductDatabase().getProduct(shortName);

    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setOnHands(onHands);
    product.setProductStatus(productStatus);
    product.setPhotoPath(!filePath.equalsIgnoreCase("") ? filePath : "missing_image.png");

    if (this.shoppingCart.getProductDatabase().replaceProduct(product)) {
      return EditProductStatus.SUCCESSFUL;
    } else {
      return EditProductStatus.ERROR;
    }
  }
}
