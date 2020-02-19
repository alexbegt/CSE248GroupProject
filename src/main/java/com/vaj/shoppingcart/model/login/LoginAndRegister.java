package com.vaj.shoppingcart.model.login;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.AccountStatus;
import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import com.vaj.shoppingcart.model.account.User;
import javafx.util.Pair;

import java.util.Optional;

public class LoginAndRegister {

  private final ShoppingCart shoppingCart;

  public LoginAndRegister(ShoppingCart mainClassIn) {
    this.shoppingCart = mainClassIn;
  }

  public LoginStatus logUserIn(String username, String password) {
    if (!this.shoppingCart.getDatabase().findUser(username)) {
      return LoginStatus.INVALID_USER;
    }
    if (this.shoppingCart.getDatabase().getUser(username).getAccountStatus() == AccountStatus.DISABLED) {
      return LoginStatus.ACCOUNT_DISABLED;
    }

    User user = this.shoppingCart.getDatabase().getUser(username);

    if (user == null) {
      return LoginStatus.INVALID_USER;
    }
    if (!AccountHelper.verifyPassword(password, user.getPassword(), user.getPasswordSalt())) {
      return LoginStatus.INCORRECT_PASSWORD;
    }

    return LoginStatus.SUCCESS;
  }

  public RegisterStatus registerUser(Name name, Address address, String username, String passwordIn, String confirmPasswordIn, String email) {
    if (AccountHelper.validateEmail(email)) {
      return RegisterStatus.INVALID_EMAIL;
    }

    Optional<String> passwordSalt = AccountHelper.generateRandomPasswordSalt();
    if (passwordSalt.isEmpty()) {
      return RegisterStatus.ERROR_GENERATING_PASSWORD_HASH;
    }
    Optional<String> password = AccountHelper.generateEncryptedPassword(passwordIn, passwordSalt.get());
    if (password.isEmpty()) {
      return RegisterStatus.ERROR_ENCRYPTING_PASSWORD;
    }
    if (!AccountHelper.verifyPassword(confirmPasswordIn, password.get(), passwordSalt.get())) {
      return RegisterStatus.PASSWORDS_DO_NOT_MATCH;
    }
    if (this.shoppingCart.getDatabase().findUser(username)) {
      return RegisterStatus.USERNAME_IN_USE;
    }
    if (this.shoppingCart.getDatabase().findAnyUserWithEmail(email)) {
      return RegisterStatus.EMAIL_IN_USE;
    }

    User user = new User(name, address, username, password.get(), passwordSalt.get(), email, AccountType.USER);

    if (this.shoppingCart.getDatabase().addUser(user)) {
      return RegisterStatus.SUCCESSFUL;
    } else {
      return RegisterStatus.ERROR;
    }
  }

  public Pair<ResetStatus, String> forgotPassword(String username) {
    if (!this.shoppingCart.getDatabase().findUser(username)) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }
    if (this.shoppingCart.getDatabase().getUser(username).getAccountStatus() == AccountStatus.DISABLED) {
      return new Pair<>(ResetStatus.ACCOUNT_DISABLED, "");
    }

    User user = this.shoppingCart.getDatabase().getUser(username);
    if (user == null) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }

    String randomPassword = AccountHelper.generateSecureRandomPassword();
    Optional<String> passwordSalt = AccountHelper.generateRandomPasswordSalt();
    if (passwordSalt.isEmpty()) {
      return new Pair<>(ResetStatus.ERROR_GENERATING_PASSWORD_SALT, "");
    }
    Optional<String> password = AccountHelper.generateEncryptedPassword(randomPassword, passwordSalt.get());
    if (password.isEmpty()) {
      return new Pair<>(ResetStatus.ERROR_ENCRYPTING_PASSWORD, "");
    }
    user.setPasswordSalt(passwordSalt.get());
    user.setPassword(password.get());

    return new Pair<>(ResetStatus.SUCCESS, randomPassword);
  }

  public Pair<ResetStatus, String> forgotUsername(String email) {
    if (!this.shoppingCart.getDatabase().findAnyUserWithEmail(email)) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }
    if (this.shoppingCart.getDatabase().getAnyUserWithEmail(email).getAccountStatus() == AccountStatus.DISABLED) {
      return new Pair<>(ResetStatus.ACCOUNT_DISABLED, "");
    }
    User user = this.shoppingCart.getDatabase().getAnyUserWithEmail(email);
    if (user == null) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }

    return new Pair<>(ResetStatus.SUCCESS, user.getUsername());
  }
}
