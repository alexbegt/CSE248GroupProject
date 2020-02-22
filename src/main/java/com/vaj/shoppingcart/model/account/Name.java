package com.vaj.shoppingcart.model.account;

public class Name {

  private final String firstName;
  private final String lastName;

  public Name(String firstNameIn, String lastNameIn) {
    this.firstName = firstNameIn;
    this.lastName = lastNameIn;
  }

  /*
   * Gets the first name of the given Name class.
   *
   * @return String, the first name
   */
  public String getFirstName() {
    return this.firstName;
  }

  /*
   * Gets the last name of the given Name class.
   *
   * @return String, the last name
   */
  public String getLastName() {
    return this.lastName;
  }

  /*
   * Gets the full name of the given Name class.
   *
   * @return String, the full name
   */
  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }
}
