package com.vaj.shoppingcart;

import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import com.vaj.shoppingcart.model.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserAccountTest {

  User user;
  Name test_bot_name;
  Address test_bot_address;
  String passwordSalt;

  @BeforeEach
  public void initEach() {
    this.test_bot_name = new Name("Test", "Bot");
    this.test_bot_address = new Address("20 Testing Street", "Test", "Test", "1337", "USA");
    this.passwordSalt = AccountHelper.generateRandomPasswordSalt().get();
    this.user = new User(this.test_bot_name, this.test_bot_address, "test", AccountHelper.generateEncryptedPassword("test", this.passwordSalt).get(), this.passwordSalt, "test@test.com", AccountType.USER);
  }

  @Test
  public void testUserName() {
    assertThat(this.user.getName()).isEqualTo(this.test_bot_name);
  }

  @Test
  public void testUserAddress() {
    assertThat(this.user.getAddress()).isEqualTo(this.test_bot_address);
  }

  @Test
  public void testUserPasswordSalt() {
    assertThat(this.user.getPasswordSalt()).isEqualTo(this.passwordSalt);
  }

  @Test
  public void testUserPassword() {
    assertThat(this.user.getPassword()).isEqualTo(AccountHelper.generateEncryptedPassword("test", this.passwordSalt).get());
  }

}
