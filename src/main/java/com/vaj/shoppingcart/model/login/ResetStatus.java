package com.vaj.shoppingcart.model.login;

public enum ResetStatus {
  INVALID_USER,
  ERROR_ENCRYPTING_PASSWORD,
  ERROR_GENERATING_PASSWORD_SALT,
  ACCOUNT_DISABLED,
  SUCCESS,
  ERROR
}
