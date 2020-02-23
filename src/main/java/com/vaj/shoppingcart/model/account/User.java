package com.vaj.shoppingcart.model.account;

import com.vaj.shoppingcart.model.database.UserDatabase;

import java.util.ArrayList;

public class User {

  private final int accountId;
  private final Name name;
  private final Address address;
  private final String username;
  private String password;
  private String email;
  private ArrayList<Integer> orderHistory;
  private Cart cart;
  private int currentOrderNumber;
  private int currentInvoiceNumber;
  private AccountStatus accountStatus;
  private AccountType accountType;
  private String passwordSalt;

  public User(Name nameIn, Address addressIn, String usernameIn, String passwordIn, String passwordSaltIn, String emailIn, AccountType accountTypeIn) {
    this.accountId = UserDatabase.ACCOUNT_ID++;
    this.name = nameIn;
    this.address = addressIn;
    this.username = usernameIn;
    this.password = passwordIn;
    this.email = emailIn;
    this.orderHistory = new ArrayList<Integer>();
    this.cart = new Cart();
    this.accountStatus = AccountStatus.ACTIVE;
    this.accountType = accountTypeIn;
    this.passwordSalt = passwordSaltIn;
    this.currentOrderNumber = -1;
    this.currentInvoiceNumber = -1;
  }

  /**
   * Gets the account ID saved to the user account.
   *
   * @return the account ID associated with the user's account.
   */
  public int getAccountId() {
    return this.accountId;
  }

  /**
   * Gets the name saved to the user account.
   *
   * @return the name associated with the user's account.
   */
  public Name getName() {
    return this.name;
  }

  /**
   * Gets the address saved to the user account.
   *
   * @return the address associated with the user's account.
   */
  public Address getAddress() {
    return this.address;
  }

  /**
   * Gets the username of the selected user.
   *
   * @return the username associated with the user's account.
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Sets the user's encrypted password to a new encrypted Password.
   *
   * @param passwordIn the new password.
   */
  public void setPassword(String passwordIn) {
    this.password = passwordIn;
  }

  /**
   * Gets the encrypted password saved to the user account.
   *
   * @return the encrypted password.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the user's password salt to a new password salt.
   *
   * @param passwordSaltIn the new password salt.
   */
  public void setPasswordSalt(String passwordSaltIn) {
    this.passwordSalt = passwordSaltIn;
  }

  /**
   * Gets the password salt saved to the user account.
   *
   * @return the password salt.
   */
  public String getPasswordSalt() {
    return this.passwordSalt;
  }

  /**
   * Sets the user's email to a new email.
   *
   * @param emailIn the new email.
   */
  public void setEmail(String emailIn) {
    this.email = emailIn;
  }

  /**
   * Gets the email saved to the user account.
   *
   * @return the email associated with the user's account.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Sets the user's order history to the new order history.
   *
   * @param orderHistoryIn the order history.
   */
  public void setOrderHistory(ArrayList<Integer> orderHistoryIn) {
    this.orderHistory = orderHistoryIn;
  }

  /**
   * Gets the order history of the account
   *
   * @return The order history.
   */
  public ArrayList<Integer> getOrderHistory() {
    return this.orderHistory;
  }

  /**
   * Sets the cart set on the user's account
   *
   * @param cartIn the cart used.
   */
  public void setCart(Cart cartIn) {
    this.cart = cartIn;
  }

  /**
   * Gets the current cart linked to the user
   *
   * @returns the users current cart.
   */
  public Cart getCart() {
    return this.cart;
  }

  /**
   * Sets the account status of the selected user
   *
   * @param accountStatusIn the account status.
   */
  public void setAccountStatus(AccountStatus accountStatusIn) {
    this.accountStatus = accountStatusIn;
  }

  /**
   * Gets the status of the current account
   *
   * @return The account status. It will either be ACTIVE, INACTIVE, OR CLOSED.
   */
  public AccountStatus getAccountStatus() {
    return this.accountStatus;
  }

  /**
   * Sets the account type of the selected user
   *
   * @param accountTypeIn the account type.
   */
  public void setAccountType(AccountType accountTypeIn) {
    this.accountType = accountTypeIn;
  }

  /**
   * Gets the account type of the current account
   *
   * @return The account type. It will either be USER or ADMIN.
   */
  public AccountType getAccountType() {
    return this.accountType;
  }

  /**
   * Sets the current order number.
   *
   * @param currentOrderNumber the current order number
   */
  public void setCurrentOrderNumber(int currentOrderNumber) {
    this.currentOrderNumber = currentOrderNumber;
  }

  /**
   * Gets the current order number of the current account.
   *
   * @return the current order number
   */
  public int getCurrentOrderNumber() {
    return this.currentOrderNumber;
  }

  /**
   * Sets the current invoice number.
   *
   * @param currentInvoiceNumber the current invoice number
   */
  public void setCurrentInvoiceNumber(int currentInvoiceNumber) {
    this.currentInvoiceNumber = currentInvoiceNumber;
  }

  /**
   * Gets the current invoice number of the current account.
   *
   * @return the current invoice number
   */
  public int getCurrentInvoiceNumber() {
    return this.currentInvoiceNumber;
  }
}

