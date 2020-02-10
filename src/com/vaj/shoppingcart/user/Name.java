package com.vaj.shoppingcart.user;

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
    return this.firstName + " " + this.middleInitial + " " + this.lastName;
  }
}
