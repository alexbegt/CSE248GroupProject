package com.vaj.shoppingcart;

import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import com.vaj.shoppingcart.model.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTestor {

  User user;
  Name test_bot_name;
  Address test_bot_address;
  String passwordSalt;

  ShoppingCart cart = new ShoppingCart();

  @BeforeEach
  public void initEach() {
    this.test_bot_name = new Name("Test", "Bot");
    this.test_bot_address = new Address("20 Testing Street", "Test", "Test", "1337");
    this.passwordSalt = AccountHelper.generateRandomPasswordSalt().get();
    this.user = new User(this.test_bot_name, this.test_bot_address, "test", AccountHelper.generateEncryptedPassword("test", this.passwordSalt).get(), this.passwordSalt, "test@test.com", AccountType.USER);

    cart.getDatabase().addUser(this.user);
  }

  @Test
  public void testUserName() {
    assertThat(this.user.getName()).isEqualTo(this.test_bot_name);
  }

  @Test
  public void findUser() {
    assertThat(ShoppingCart.getInstance().getDatabase().findUser("test")).isTrue();
  }
}
