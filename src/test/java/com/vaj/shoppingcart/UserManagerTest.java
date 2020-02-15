package com.vaj.shoppingcart;

import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import com.vaj.shoppingcart.model.account.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserManagerTest {

  @Test
  public void testUser() {
    Name test_bot_name = new Name("Test", 'C', "Bot");
    Address test_bot_address = new Address(20, "Testing Street", "Test", "1337", "USA");

    User user = new User(test_bot_name, test_bot_address, "test", "test", "test@test.com", AccountType.USER);

    assertThat(user.getName()).isEqualTo(test_bot_name);
    assertThat(user.getAddress()).isEqualTo(test_bot_address);
    assertThat(user.getAccountType()).isEqualByComparingTo(AccountType.ADMIN);
  }

}
