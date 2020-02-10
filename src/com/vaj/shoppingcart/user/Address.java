package com.vaj.shoppingcart.user;

public class Address {
  private final int houseNumber;
  private final String streetName;
  private final String state;
  private final String zipCode;
  private final String country;

  public Address(int houseNumberIn, String streetNameIn, String stateIn, String zipCodeIn, String countryIn) {
    this.houseNumber = houseNumberIn;
    this.streetName = streetNameIn;
    this.state = stateIn;
    this.zipCode = zipCodeIn;
    this.country = countryIn;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public String getFullAddress() {
    return this.houseNumber + " " + this.streetName  + " " + this.state + " , " + this.country + " " + this.zipCode;
  }
}
