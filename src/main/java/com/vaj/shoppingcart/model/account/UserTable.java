package com.vaj.shoppingcart.model.account;

public class UserTable {
  private int userIdentifier;
  private String username;
  private String fullName;
  private int totalOrders;

  public UserTable(int userIdentifier, String username, String fullName, int totalOrders) {
    this.userIdentifier = userIdentifier;
    this.username = username;
    this.fullName = fullName;
    this.totalOrders = totalOrders;
  }

  public int getUserIdentifier() {
    return this.userIdentifier;
  }

  public String getUsername() {
    return this.username;
  }

  public String getFullName() {
    return this.fullName;
  }

  public int getTotalOrders() {
    return this.totalOrders;
  }
}
