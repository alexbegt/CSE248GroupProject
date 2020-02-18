package com.vaj.shoppingcart.model.account;

public class Address {

  private final String address;
  private final String city;
  private final String state;
  private final String zipCode;

  public Address(String addressIn, String cityIn, String stateIn, String zipCodeIn) {
    this.address = addressIn;
    this.city = cityIn;
    this.state = stateIn;
    this.zipCode = zipCodeIn;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public String getFullAddress() {
    return this.address + " " + this.city + ", " +  this.state + " " + this.zipCode;
  }
}
