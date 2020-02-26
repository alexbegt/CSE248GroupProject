package com.vaj.shoppingcart;

import com.vaj.shoppingcart.model.product.ProductTable;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.testfx.framework.junit5.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest extends ApplicationTest {

  @BeforeAll
  static void setUpClass() throws Exception {
    ApplicationTest.launch(ShoppingCart.class);
  }

  @Test
  @Order(1)
  void testSignUpButton() {
    Button signUpButton = lookup("#btnSignUp").queryButton();
    clickOn(signUpButton);
  }

  @Test
  @Order(2)
  void testRegister() {
    TextField usernameField = (TextField) lookup("#txtUsername").queryTextInputControl();
    TextField passwordField = (TextField) lookup("#txtPassword").queryTextInputControl();
    TextField confirmPasswordField = (TextField) lookup("#txtConfirmPassword").queryTextInputControl();
    TextField emailField = (TextField) lookup("#txtEmail").queryTextInputControl();

    TextField firstNameField = (TextField) lookup("#txtFirstName").queryTextInputControl();
    TextField lastNameField = (TextField) lookup("#txtLastName").queryTextInputControl();
    TextField addressField = (TextField) lookup("#txtAddress").queryTextInputControl();
    TextField cityField = (TextField) lookup("#txtCity").queryTextInputControl();
    TextField stateField = (TextField) lookup("#txtState").queryTextInputControl();
    TextField zipCodeField = (TextField) lookup("#txtZipCode").queryTextInputControl();

    Button signUpButton = lookup("#btnSignUp").queryButton();

    typeTest(usernameField);
    typeTest(passwordField);
    typeTest(confirmPasswordField);

    clickOn(emailField);
    write("test@test.com");

    typeTest(firstNameField);
    typeTest(lastNameField);
    typeTest(addressField);
    typeTest(cityField);
    typeTest(stateField);

    clickOn(zipCodeField);
    write("111111");

    clickOn(signUpButton);

    clickOn("Close");
  }

  void typeTest(TextField fieldIn) {
    clickOn(fieldIn);
    type(KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T);
  }

  @Test
  @Order(3)
  void testForgotUsernameButton() {
    Label forgotUsernameButton = (Label) lookup("#btnForgotUsername").queryLabeled();

    clickOn(forgotUsernameButton);
  }

  @Test
  @Order(4)
  void testForgotUsername() {
    Label errorLabel = (Label) lookup("#lblErrors").queryLabeled();
    TextField emailField = (TextField) lookup("#txtEmail").queryTextInputControl();
    Button submitButton = lookup("#btnSubmit").queryButton();

    clickOn(emailField);
    write("test@test.com");

    clickOn(submitButton);

    assertThat(errorLabel.getText()).isEqualTo("Your account name is: test");

    clickOn("Close");
  }

  @Test
  @Order(5)
  void testForgotPasswordButton() {
    Label forgotPasswordButton = (Label) lookup("#btnForgotPassword").queryLabeled();

    clickOn(forgotPasswordButton);
  }

  @Test
  @Order(6)
  void testForgotPassword() {
    Label errorLabel = (Label) lookup("#lblErrors").queryLabeled();
    Label newPasswordLabel = (Label) lookup("#lblNewPassword").queryLabeled();
    TextField usernameField = (TextField) lookup("#txtUsername").queryTextInputControl();
    Button submitButton = lookup("#btnSubmit").queryButton();

    clickOn(usernameField);
    write("test");

    clickOn(submitButton);

    TestConstraints.newPassword = newPasswordLabel.getText();
    assertThat(errorLabel.getText()).contains("Your new Password is: ");

    clickOn("Close");
  }

  @Test
  @Order(7)
  void testActualLogin() {
    TextField usernameField = (TextField) lookup("#txtUsername").queryTextInputControl();
    TextField passwordField = (TextField) lookup("#txtPassword").queryTextInputControl();
    Button signInButton = lookup("#btnSignIn").queryButton();

    typeTest(usernameField);

    clickOn(passwordField);
    write(TestConstraints.newPassword);

    clickOn(signInButton);
  }

  @Test
  @Order(8)
  void testSigningOut() {
    Button logOutButton = lookup("#btnLogout").queryButton();

    clickOn(logOutButton);
  }


  @Test
  @Order(8)
  void testLoginAgain() {
    TextField usernameField = (TextField) lookup("#txtUsername").queryTextInputControl();
    TextField passwordField = (TextField) lookup("#txtPassword").queryTextInputControl();
    Button signInButton = lookup("#btnSignIn").queryButton();

    typeTest(usernameField);

    clickOn(passwordField);
    write(TestConstraints.newPassword);

    clickOn(signInButton);
  }

  @Test
  @Order(9)
  void testSearchingForItems() {
    Button shopButton = lookup("#btnShop").queryButton();
    clickOn(shopButton);
  }

  @Test
  @Order(10)
  void testSelectAnItemAndAddIt() {
    TextField quantityField = (TextField) lookup("#txtQuantity").queryTextInputControl();
    Button addToCart = lookup("#btnSubmit").queryButton();

    Node row = lookup(".table-row-cell").nth(0).query();
    clickOn(row);

    clickOn(quantityField);
    write("1");
    clickOn(addToCart);
  }

  @Test
  @Order(11)
  void testCart() {
    Button switchToCart = lookup("#btnSwitchToCart").queryButton();
    clickOn(switchToCart);
  }

  @Test
  @Order(12)
  void testCheckoutAndViewingOrderHistory() {
    Button checkout = lookup("#btnCheckout").queryButton();
    clickOn(checkout);

    sleep(1000);

    Button close = lookup("#btnClose").queryButton();
    clickOn(close);

    Button viewOrderHistory = lookup("#btnOrderHistory").queryButton();

    clickOn(viewOrderHistory);

    Node row = lookup(".table-row-cell").nth(0).query();
    clickOn(row);

    Button viewInvoice = lookup("#btnSubmit").queryButton();
    clickOn(viewInvoice);

    sleep(1000);

    close = lookup("#btnClose").queryButton();
    clickOn(close);
  }

  @Test
  @Order(14)
  void testShopAgain() {
    Button shopButton = lookup("#btnShop").queryButton();
    clickOn(shopButton);

    TableView<ProductTable> products = lookup("#products").queryTableView();
    for (int i = 0; i <= products.getItems().size() - 1; i++) {
      ProductTable product = products.getItems().get(i);
      handleShoppingTest(i, product.getProductQuantity());
    }

    Button switchToCart = lookup("#btnSwitchToCart").queryButton();
    clickOn(switchToCart);

    Button checkout = lookup("#btnCheckout").queryButton();
    clickOn(checkout);

    sleep(10000);

    Button close = lookup("#btnClose").queryButton();
    clickOn(close);
  }

  void handleShoppingTest(int index, int onHands) {
    TableView<ProductTable> products = lookup("#products").queryTableView();
    products.scrollTo(index - 1);

    TextField quantityField = (TextField) lookup("#txtQuantity").queryTextInputControl();
    Button addToCart = lookup("#btnSubmit").queryButton();

    products.getSelectionModel().clearAndSelect(index);

    clickOn(quantityField);
    write(String.valueOf(onHands));
    clickOn(addToCart);

  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.show();
  }
}
