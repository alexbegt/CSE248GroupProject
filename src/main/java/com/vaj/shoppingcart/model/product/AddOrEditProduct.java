package com.vaj.shoppingcart.model.product;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.helper.ProductHelper;

public class AddOrEditProduct {

  private final ShoppingCart shoppingCart;

  public AddOrEditProduct(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

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
