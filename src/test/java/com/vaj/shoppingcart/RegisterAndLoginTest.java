package com.vaj.shoppingcart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.testfx.framework.junit5.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterAndLoginTest extends ApplicationTest {

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
  @DisplayName("Register User")
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
  }

  void typeTest(TextField fieldIn) {
    clickOn(fieldIn);
    type(KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T);
  }

  @Test
  @Order(3)
  void testActualLogin() {
    Label errorLabel = (Label) lookup("#lblErrors").queryLabeled();
    TextField usernameField = (TextField) lookup("#txtUsername").queryTextInputControl();
    TextField passwordField = (TextField) lookup("#txtPassword").queryTextInputControl();
    Button signInButton = lookup("#btnSignIn").queryButton();

    typeTest(usernameField);
    typeTest(passwordField);
    clickOn(signInButton);

    assertThat(errorLabel.getText()).isEqualTo("Successful! Logging in...");
  }

  @Test
  @Order(4)
  void testForgotUsernameButton() {
    Label forgotUsernameButton = (Label) lookup("#btnForgotUsername").queryLabeled();

    clickOn(forgotUsernameButton);
  }

  @Test
  @Order(5)
  void testForgotUsername() {
    Label errorLabel = (Label) lookup("#lblErrors").queryLabeled();
    TextField emailField = (TextField) lookup("#txtEmail").queryTextInputControl();
    Button submitButton = lookup("#btnSubmit").queryButton();
    Button cancelButton = lookup("#btnCancel").queryButton();

    clickOn(emailField);
    write("test@test.com");

    clickOn(submitButton);

    assertThat(errorLabel.getText()).isEqualTo("Your account name is: test");

    clickOn(cancelButton);
  }

  @Test
  @Order(6)
  void testForgotPasswordButton() {
    Label forgotPasswordButton = (Label) lookup("#btnForgotPassword").queryLabeled();

    clickOn(forgotPasswordButton);
  }

  @Test
  @Order(7)
  void testForgotPassword() {
    Label errorLabel = (Label) lookup("#lblErrors").queryLabeled();
    TextField usernameField = (TextField) lookup("#txtUsername").queryTextInputControl();
    Button submitButton = lookup("#btnSubmit").queryButton();
    Button cancelButton = lookup("#btnCancel").queryButton();

    clickOn(usernameField);
    write("test");

    clickOn(submitButton);

    assertThat(errorLabel.getText()).contains("Your new Password is: ");

    clickOn(cancelButton);
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.show();
  }
}
