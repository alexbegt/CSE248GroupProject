package com.vaj.shoppingcart.model.database;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.User;

import java.util.TreeMap;

public class Database {

  public static int ACCOUNT_ID = 1;
  public static int PRODUCT_ID = 1;
  public TreeMap<String, User> userTreeMap;
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
   * @return If the user is found, returns true. If false, not found in the TreeMap.
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
}
