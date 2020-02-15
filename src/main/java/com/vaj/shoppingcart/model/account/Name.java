package com.vaj.shoppingcart.model.account;

public class Name {

  private final String firstName;
  private final char middleInitial;
  private final String lastName;

  public Name(String firstNameIn, char middleInitialIn, String lastNameIn) {
    this.firstName = firstNameIn;
    this.middleInitial = middleInitialIn;
    this.lastName = lastNameIn;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public char getMiddleInitial() {
    return this.middleInitial;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getFullName() {
    if (this.middleInitial == Character.UNASSIGNED) {
      return this.firstName + " " + this.lastName;
    } else {
      return this.firstName + " " + this.middleInitial + " " + this.lastName;
    }
  }
}
