package com.vaj.shoppingcart.helper;

public class ProductHelper {

  public static boolean validatePrice(String price) {
    try {
      Double.parseDouble(price);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  public static boolean validateOnHands(String onHands) {
    try {
      Integer.parseInt(onHands);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
}
