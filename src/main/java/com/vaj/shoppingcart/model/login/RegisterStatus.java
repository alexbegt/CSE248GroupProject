package com.vaj.shoppingcart.model.login;

public enum RegisterStatus {
  SUCCESSFUL,
  INVALID_EMAIL,
  USERNAME_IN_USE,
  EMAIL_IN_USE,
  PASSWORDS_DO_NOT_MATCH,
  ERROR_GENERATING_PASSWORD_HASH,
  ERROR_ENCRYPTING_PASSWORD,
  ERROR
}
