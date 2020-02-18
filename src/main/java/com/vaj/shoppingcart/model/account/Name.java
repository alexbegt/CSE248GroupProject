package com.vaj.shoppingcart.model.account;

public class Name {

  private final String firstName;
  private final String lastName;

  public Name(String firstNameIn, String lastNameIn) {
    this.firstName = firstNameIn;
    this.lastName = lastNameIn;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }
}
