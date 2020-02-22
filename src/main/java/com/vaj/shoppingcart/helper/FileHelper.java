package com.vaj.shoppingcart.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.database.InvoiceDatabase;
import com.vaj.shoppingcart.model.database.OrderDatabase;
import com.vaj.shoppingcart.model.database.ProductDatabase;
import com.vaj.shoppingcart.model.database.UserDatabase;
import com.vaj.shoppingcart.model.order.Invoice;
import com.vaj.shoppingcart.model.order.Order;
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
  static final Type orderTreeMapType = new TypeToken<TreeMap<Integer, Order>>() {}.getType();
  static final Type invoiceTreeMapType = new TypeToken<TreeMap<Integer, Invoice>>() {}.getType();

  public static void saveAllDatabases(ShoppingCart cart, String usersFilePath, String productsFilePath, String ordersFilePath, String invoicesFilePath) {
    saveProducts(cart, productsFilePath);
    saveUsers(cart, usersFilePath);
    saveOrders(cart, ordersFilePath);
    saveInvoices(cart, invoicesFilePath);
  }

  public static void loadAllDatabases(ShoppingCart cart, String usersFilePath, String productsFilePath, String ordersFilePath, String invoicesFilePath) {
    loadProducts(cart, productsFilePath);
    loadUsers(cart, usersFilePath);
    loadOrders(cart, ordersFilePath);
    loadInvoices(cart, invoicesFilePath);
  }

  /**
   * load the users from the saved json file.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
  public static void loadUsers(ShoppingCart cart, String filePath) {
    try {
      JsonObject json = gson.fromJson(new FileReader(filePath), JsonObject.class);

      UserDatabase.ACCOUNT_ID = gson.fromJson(json.get("current_id"), Integer.class);
      cart.getUserDatabase().setUserTreeMap(gson.fromJson(json.get("accounts"), userTreeMapType));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Save the users to json file provided.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
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

  /**
   * load the products from the saved json file.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
  public static void loadProducts(ShoppingCart cart, String filePath) {
    try {
      JsonObject json = gson.fromJson(new FileReader(filePath), JsonObject.class);

      ProductDatabase.PRODUCT_ID = gson.fromJson(json.get("current_id"), Integer.class);
      cart.getProductDatabase().setProductTreeMap(gson.fromJson(json.get("products"), productTreeMapType));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Save the products to json file provided.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
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

  /**
   * load the orders from the saved json file.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
  public static void loadOrders(ShoppingCart cart, String filePath) {
    try {
      JsonObject json = gson.fromJson(new FileReader(filePath), JsonObject.class);

      OrderDatabase.ORDER_ID = gson.fromJson(json.get("current_id"), Integer.class);
      cart.getOrderDatabase().setOrderTreeMap(gson.fromJson(json.get("orders"), orderTreeMapType));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Save the orders to json file provided.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
  public static void saveOrders(ShoppingCart cart, String filePath) {
    try {
      Writer writer = new FileWriter(filePath);
      JsonObject rootObject = new JsonObject();

      rootObject.add("current_id", gson.toJsonTree(OrderDatabase.ORDER_ID));
      rootObject.add("orders", gson.toJsonTree(cart.getOrderDatabase().getOrderTreeMap(), orderTreeMapType));

      gson.toJson(rootObject, writer);

      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * load the invoices from the saved json file.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
  public static void loadInvoices(ShoppingCart cart, String filePath) {
    try {
      JsonObject json = gson.fromJson(new FileReader(filePath), JsonObject.class);

      InvoiceDatabase.INVOICE_ID = gson.fromJson(json.get("current_id"), Integer.class);
      cart.getInvoiceDatabase().setInvoiceTreeMap(gson.fromJson(json.get("invoices"), invoiceTreeMapType));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Save the invoices to json file provided.
   *
   * @param cart     the main cart class.
   * @param filePath the path of the json holding the users.
   */
  public static void saveInvoices(ShoppingCart cart, String filePath) {
    try {
      Writer writer = new FileWriter(filePath);
      JsonObject rootObject = new JsonObject();

      rootObject.add("current_id", gson.toJsonTree(InvoiceDatabase.INVOICE_ID));
      rootObject.add("invoices", gson.toJsonTree(cart.getInvoiceDatabase().getInvoiceTreeMap(), invoiceTreeMapType));

      gson.toJson(rootObject, writer);

      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
