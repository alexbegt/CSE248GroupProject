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

  /**
   * Logs the user in with the given username and password if the user exists, and their password matches.
   *
   * @param username the entered username by the user
   * @param password the entered password by the user
   * @return LoginStatus the status returned (INVALID_USER, ACCOUNT_DISABLED, INVALID_USER, INCORRECT_PASSWORD, SUCCESS)
   */
  public Pair<LoginStatus, User> logUserIn(String username, String password) {
    if (!this.shoppingCart.getUserDatabase().findUser(username)) {
      return new Pair<>(LoginStatus.INVALID_USER, null);
    }
    if (this.shoppingCart.getUserDatabase().getUser(username).getAccountStatus() == AccountStatus.DISABLED) {
      return new Pair<>(LoginStatus.ACCOUNT_DISABLED, null);
    }

    User user = this.shoppingCart.getUserDatabase().getUser(username);

    if (user == null) {
      return new Pair<>(LoginStatus.INVALID_USER, null);
    }
    if (!AccountHelper.verifyPassword(password, user.getPassword(), user.getPasswordSalt())) {
      return new Pair<>(LoginStatus.INCORRECT_PASSWORD, null);
    }

    return new Pair<>(LoginStatus.SUCCESS, user);
  }

  /**
   * Registers a user if it does not exist.
   *
   * @param name              the entered name by the user
   * @param address           the entered address by the user
   * @param username          the entered username by the user
   * @param passwordIn        the entered password by the user
   * @param confirmPasswordIn the entered password by the user
   * @param email             the entered email by the user
   * @return RegisterStatus the status returned (INVALID_EMAIL, ERROR_GENERATING_PASSWORD_HASH, ERROR_ENCRYPTING_PASSWORD, PASSWORDS_DO_NOT_MATCH, USERNAME_IN_USE, EMAIL_IN_USE, SUCCESSFUL, ERROR)
   */
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
    if (this.shoppingCart.getUserDatabase().findUser(username)) {
      return RegisterStatus.USERNAME_IN_USE;
    }
    if (this.shoppingCart.getUserDatabase().findAnyUserWithEmail(email)) {
      return RegisterStatus.EMAIL_IN_USE;
    }

    User user = new User(name, address, username, password.get(), passwordSalt.get(), email, AccountType.USER);

    if (this.shoppingCart.getUserDatabase().addUser(user)) {
      return RegisterStatus.SUCCESSFUL;
    } else {
      return RegisterStatus.ERROR;
    }
  }

  /**
   * Resets the user's passwords
   *
   * @param username the entered username by the user
   * @return the status returned (INVALID_EMAIL, ACCOUNT_DISABLED, INVALID_USER, ERROR_GENERATING_PASSWORD_SALT, ERROR_ENCRYPTING_PASSWORD, SUCCESS) along with the username.
   */
  public Pair<ResetStatus, String> forgotPassword(String username) {
    if (!this.shoppingCart.getUserDatabase().findUser(username)) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }
    if (this.shoppingCart.getUserDatabase().getUser(username).getAccountStatus() == AccountStatus.DISABLED) {
      return new Pair<>(ResetStatus.ACCOUNT_DISABLED, "");
    }

    User user = this.shoppingCart.getUserDatabase().getUser(username);
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

  /**
   * Gets the user's username if its exists
   *
   * @param email the entered email by the user
   * @return the status returned (INVALID_USER, ACCOUNT_DISABLED, INVALID_USER, SUCCESS) along with the username.
   */
  public Pair<ResetStatus, String> forgotUsername(String email) {
    if (!this.shoppingCart.getUserDatabase().findAnyUserWithEmail(email)) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }
    if (this.shoppingCart.getUserDatabase().getAnyUserWithEmail(email).getAccountStatus() == AccountStatus.DISABLED) {
      return new Pair<>(ResetStatus.ACCOUNT_DISABLED, "");
    }
    User user = this.shoppingCart.getUserDatabase().getAnyUserWithEmail(email);
    if (user == null) {
      return new Pair<>(ResetStatus.INVALID_USER, "");
    }

    return new Pair<>(ResetStatus.SUCCESS, user.getUsername());
  }

  /**
   * Registers a user if it does not exist.
   *
   * @param name              the entered name by the user
   * @param address           the entered address by the user
   * @param username          the entered username by the user
   * @param passwordIn        the entered password by the user
   * @param confirmPasswordIn the entered password by the user
   * @param email             the entered email by the user
   * @param accountStatus     the selected account status
   * @param accountType       the selected account type
   * @return RegisterStatus the status returned (INVALID_EMAIL, ERROR_GENERATING_PASSWORD_HASH, ERROR_ENCRYPTING_PASSWORD, PASSWORDS_DO_NOT_MATCH, USERNAME_IN_USE, EMAIL_IN_USE, SUCCESSFUL, ERROR)
   */
  public RegisterStatus registerUser(Name name, Address address, String username, String passwordIn, String confirmPasswordIn, String email, AccountStatus accountStatus, AccountType accountType) {
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
    if (this.shoppingCart.getUserDatabase().findUser(username)) {
      return RegisterStatus.USERNAME_IN_USE;
    }
    if (this.shoppingCart.getUserDatabase().findAnyUserWithEmail(email)) {
      return RegisterStatus.EMAIL_IN_USE;
    }

    User user = new User(name, address, username, password.get(), passwordSalt.get(), email, accountType);
    user.setAccountStatus(accountStatus);

    if (this.shoppingCart.getUserDatabase().addUser(user)) {
      return RegisterStatus.SUCCESSFUL;
    } else {
      return RegisterStatus.ERROR;
    }
  }
}
