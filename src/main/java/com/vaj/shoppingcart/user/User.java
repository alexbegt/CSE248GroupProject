package com.vaj.shoppingcart.user;

public class User {

  private final int accountId;
  private final Name name;
  private final Address address;
  private final String username;
  private String password;
  private String email;
  private OrderHistory orderHistory;
  private Cart cart;
  private AccountStatus accountStatus;
  private AccountType accountType;

  public User(Name nameIn, Address addressIn, String usernameIn, String passwordIn, String emailIn, AccountType accountTypeIn) {
    this.accountId = Database.ACCOUNTID++;
    this.name = nameIn;
    this.address = addressIn;
    this.username = usernameIn;
    this.password = passwordIn;
    this.email = emailIn;
    this.orderHistory = new OrderHistory();
    this.cart = new Cart();
    this.accountStatus = AccountStatus.ACTIVE;
    this.accountType = accountTypeIn;
  }

  /*
   * Get's the account ID saved to the user account.
   *
   * @return the account ID associated with the user's account.
   */
  public int getAccountId() {
    return this.accountId;
  }

  /*
   * Get's the name saved to the user account.
   *
   * @return the name associated with the user's account.
   */
  public Name getName() {
    return this.name;
  }

  /*
   * Get's the address saved to the user account.
   *
   * @return the address associated with the user's account.
   */
  public Address getAddress() {
    return this.address;
  }

  /*
   * Get's the username of the selected user.
   *
   * @return the username associated with the user's account.
   */
  public String getUsername() {
    return this.username;
  }

  /*
   * Sets the user's encrypted password to a new encrypted Password.
   *
   * @param passwordIn the new password.
   */
  public void setPassword(String passwordIn) {
    this.password = passwordIn;
  }

  /*
   * Get's the encrypted password saved to the user account.
   *
   * @return the encrypted password.
   */
  public String getPassword() {
    return this.password;
  }

  /*
   * Sets the user's email to a new email.
   *
   * @param emailIn the new email.
   */
  public void setEmail(String emailIn) {
    this.email = emailIn;
  }

  /*
   * Get's the email saved to the user account.
   *
   * @return the email associated with the user's account.
   */
  public String getEmail() {
    return this.email;
  }

  /*
   * Sets the user's order history to the new order history.
   *
   * @param orderHistoryIn the order history.
   */
  public void setOrderHistory(OrderHistory orderHistoryIn) {
    this.orderHistory = orderHistoryIn;
  }

  /*
   * Get's the order history of the account
   *
   * @return The order history.
   */
  public OrderHistory getOrderHistory() {
    return this.orderHistory;
  }

  /*
   * Sets the cart set on the user's account
   *
   * @param cartIn the cart used.
   */
  public void setCart(Cart cartIn) {
    this.cart = cartIn;
  }

  /*
   * Get's the current cart linked to the user
   *
   * @returns the users current cart.
   */
  public Cart getCart() {
    return this.cart;
  }

  /*
   * Sets the account status of the selected user
   *
   * @param accountStatusIn the account status.
   */
  public void setAccountStatus(AccountStatus accountStatusIn) {
    this.accountStatus = accountStatusIn;
  }

  /*
   * Get's the status of the current account
   *
   * @return The account status. It will either be ACTIVE, INACTIVE, OR CLOSED.
   */
  public AccountStatus getAccountStatus() {
    return this.accountStatus;
  }

  /*
   * Sets the account type of the selected user
   *
   * @param accountTypeIn the account type.
   */
  public void setAccountType(AccountType accountTypeIn) {
    this.accountType = accountTypeIn;
  }

  /*
   * Get's the account type of the current account
   *
   * @return The account type. It will either be USER or ADMIN.
   */
  public AccountType getAccountType() {
    return this.accountType;
  }
}

