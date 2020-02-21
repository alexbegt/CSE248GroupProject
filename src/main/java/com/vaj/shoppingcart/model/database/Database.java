package com.vaj.shoppingcart.model.database;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.product.Product;

import java.util.TreeMap;

public class Database {

  public static int ACCOUNT_ID = 1;
  public static int PRODUCT_ID = 1;
  public TreeMap<String, User> userTreeMap;
  public TreeMap<String, Product> productTreeMap;
  private final ShoppingCart shoppingCart;

  public Database(ShoppingCart shoppingCartIn) {
    this.shoppingCart = shoppingCartIn;

    this.userTreeMap = new TreeMap<String, User>();
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Adds a user to the database if it doesn't exist.
   *
   * @param user - User object passed
   * @return boolean, if true added successfully. If false added unsuccessfully.
   */
  public boolean addUser(User user) {
    if (this.findUser(user.getUsername())) {
      return false;
    } else {
      this.userTreeMap.put(user.getUsername(), user);
      return true;
    }
  }

  /**
   * removes a user with the given username.
   *
   * @param username - key value to search for
   * @return If the user is found, removes user and returns true. If false, not found in the TreeMap.
   */
  public boolean removeUser(String username) {
    User user = this.userTreeMap.remove(username);

    return user != null;
  }

  /**
   * Finds a user with the given username.
   *
   * @param username - key value to search for
   * @return If the user is found, returns true. If false, not found in the TreeMap.
   */
  public boolean findUser(String username) {
    return this.userTreeMap.containsKey(username);
  }
  
  /**
   * Replaces a user with the given username.
   *
   * @param User - searches through users
   * @return If the user is found, returns true and replaces. If false, not found in the TreeMap.
   */
  public boolean replaceUser(User user) {
	  if(!this.findUser(user.getUsername())) {
		  return false;
	  }
	  else {
		  this.userTreeMap.replace(user.getUsername(), user);
		  return true;
	  }
  }
  
  /**
   * Finds the product with the given product name.
   *
   * @param String - searches through product names
   * @return If the product is found, returns true. If false, product is not found in the TreeMap.
   */
  public boolean findProduct(String productName) {
	    return this.productTreeMap.containsKey(productName);
	  }
  
  /**
   * Adds a product to the database if it doesn't exist.
   *
   * @param product - Product object passed
   * @return boolean, if true added successfully. If false added unsuccessfully.
   */
  public boolean addProduct(Product product) {
	    if (this.findProduct(product.getName())) {
	      return false;
	    } else {
	      this.productTreeMap.put(product.getName(), product);
	      return true;
	    }
	  }
  
  /**
   * removes a product with the given name.
   *
   * @param name - key value to search for
   * @return If the product is found, removes product and returns true. If false, not found in the TreeMap.
   */
  public boolean removeProduct(String name) {
    Product product = this.productTreeMap.remove(name);

    return product != null;
  }

  /**
   * Replaces a product with the given product name.
   * FOR ADMIN ONLY.
   * @param Product - searches through products
   * @return If the user is found, returns true and replaces. If false, not found in the TreeMap.
   */
  public boolean replaceProduct(Product product) {
	  if(!this.findProduct(product.getName())) {
		  return false;
	  }
	  else {
		  this.productTreeMap.replace(product.getName(), product);
		  return true;
	  }
  }
  
  /**
   * Finds any user with the given email.
   *
   * @param email - key value to search for
   * @return If the user is found, returns true. If false, not found in the TreeMap.
   */
  public boolean findAnyUserWithEmail(String email) {
    for (User user : this.userTreeMap.values()) {
      if (user.getEmail().equalsIgnoreCase(email)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Finds any user with the given email.
   *
   * @param email - key value to search for
   * @return If the user is found, returns true. If they are not found, it returns a null.
   */
  public User getAnyUserWithEmail(String email) {
    for (User user : this.userTreeMap.values()) {
      if (user.getEmail().equalsIgnoreCase(email)) {
        return user;
      }
    }

    return null;
  }

  /**
   * Gets a user with the given username
   *
   * @param username - key value to search for
   * @return If the user is found, returns that user. If they are not found, it returns a null.
   */
  public User getUser(String username) {
    return this.userTreeMap.getOrDefault(username, null);
  }
  
  /**
   * Gets a product with the given name
   *
   * @param name - key value to search for
   * @return If the product is found, returns that product. If they are not found, it returns a null.
   */
  public Product getProduct(String name) {
    return this.productTreeMap.getOrDefault(name, null);
  }

  public void setUserTreeMap(TreeMap userTreeMap) {
	this.userTreeMap = userTreeMap;
}

public void setProductTreeMap(TreeMap productTreeMap) {
	this.productTreeMap = productTreeMap;
}

public TreeMap getUserTreeMap() {
	return userTreeMap;
}

public TreeMap getProductTreeMap() {
	return productTreeMap;
}
}
