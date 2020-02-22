package com.vaj.shoppingcart.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.database.ProductDatabase;
import com.vaj.shoppingcart.model.database.UserDatabase;
import com.vaj.shoppingcart.model.product.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.TreeMap;

public class FileHelper {

  static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  static final Type userTreeMapType = new TypeToken<TreeMap<String, User>>() {}.getType();
  static final Type productTreeMapType = new TypeToken<TreeMap<String, Product>>() {}.getType();

  public static void saveUsersAndProducts(ShoppingCart cart, String usersFilePath, String productsFilePath) {
    saveProducts(cart, productsFilePath);
    saveUsers(cart, usersFilePath);
  }

  public static void loadUsersAndProducts(ShoppingCart cart, String usersFilePath, String productsFilePath) {
    loadProducts(cart, productsFilePath);
    loadUsers(cart, usersFilePath);
  }

  public static void loadUsers(ShoppingCart cart, String filePath) {
    try {
      JsonObject json = gson.fromJson(new FileReader(filePath), JsonObject.class);

      UserDatabase.ACCOUNT_ID = gson.fromJson(json.get("current_id"), Integer.class);
      cart.getUserDatabase().setUserTreeMap(gson.fromJson(json.get("accounts"), userTreeMapType));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveUsers(ShoppingCart cart, String filePath) {
    try {
      Writer writer = new FileWriter(filePath);
      JsonObject rootObject = new JsonObject();

      rootObject.add("current_id", gson.toJsonTree(UserDatabase.ACCOUNT_ID));
      rootObject.add("accounts", gson.toJsonTree(cart.getUserDatabase().getUserTreeMap(), userTreeMapType));

      gson.toJson(rootObject, writer);

      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void loadProducts(ShoppingCart cart, String filePath) {
    try {
      JsonObject json = gson.fromJson(new FileReader(filePath), JsonObject.class);

      ProductDatabase.PRODUCT_ID = gson.fromJson(json.get("current_id"), Integer.class);
      cart.getProductDatabase().setProductTreeMap(gson.fromJson(json.get("products"), productTreeMapType));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveProducts(ShoppingCart cart, String filePath) {
    try {
      Writer writer = new FileWriter(filePath);
      JsonObject rootObject = new JsonObject();

      rootObject.add("current_id", gson.toJsonTree(ProductDatabase.PRODUCT_ID));
      rootObject.add("products", gson.toJsonTree(cart.getProductDatabase().getProductTreeMap(), productTreeMapType));

      gson.toJson(rootObject, writer);

      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
