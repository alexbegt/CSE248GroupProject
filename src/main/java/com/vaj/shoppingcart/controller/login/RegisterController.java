package com.vaj.shoppingcart.controller.login;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends GenericController implements Initializable {

  @FXML
  private TextField txtFirstName;
  @FXML
  private TextField txtLastName;
  @FXML
  private TextField txtAddress;
  @FXML
  private TextField txtCity;
  @FXML
  private TextField txtState;
  @FXML
  private TextField txtZipCode;

  @FXML
  private TextField txtUsername;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private PasswordField txtConfirmPassword;
  @FXML
  private TextField txtEmail;

  @FXML
  private Label lblErrors;
  @FXML
  private Button btnSignUp;
  @FXML
  private Button btnCancel;

  /**
   * Handles when a user presses the cancel button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleCancel(MouseEvent event) {
    returnToLogin(event);
  }

  /**
   * Handles when a user presses the complete button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleCompleteSignUp(MouseEvent event) {
    String username = txtUsername.getText();
    String password = txtPassword.getText();
    String confirmPassword = txtConfirmPassword.getText();
    String email = txtEmail.getText();

    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String address = txtAddress.getText();
    String city = txtCity.getText();
    String state = txtState.getText();
    String zipCode = txtZipCode.getText();

    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || zipCode.isEmpty()) {
      this.setErrorText(Color.TOMATO, "Please ensure you have entered all the required information.");
    } else {
      switch (ShoppingCart.getInstance().getLoginAndRegister().registerUser(new Name(firstName, lastName), new Address(address, city, state, zipCode), username, password, confirmPassword, email)) {
        case INVALID_EMAIL:
          this.setErrorText(Color.TOMATO, "Invalid email entered.");
          break;
        case USERNAME_IN_USE:
          this.setErrorText(Color.TOMATO, "Sorry, that username is in use.");
          break;
        case EMAIL_IN_USE:
          this.setErrorText(Color.TOMATO, "Sorry, that email is in use.");
          break;
        case PASSWORDS_DO_NOT_MATCH:
          this.setErrorText(Color.TOMATO, "Password and confirm password do not match.");
          break;
        case ERROR_GENERATING_PASSWORD_HASH:
          this.setErrorText(Color.TOMATO, "Error generating a password hash.");
          break;
        case ERROR_ENCRYPTING_PASSWORD:
          this.setErrorText(Color.TOMATO, "Error encrypting password.");
          break;
        case ERROR:
          this.setErrorText(Color.TOMATO, "Unknown error.");
          break;
        case SUCCESSFUL:
          this.setErrorText(Color.GREEN, "Successful! Returning to login...");
          AccountHelper.createDialog("Account created, please log in.", "Registration");
          this.returnToLogin(event);
          break;
      }
    }
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  /**
   * Sets the text of the label on the screen
   *
   * @param color the color.
   * @param text  the actual text
   */
  private void setErrorText(Color color, String text) {
    lblErrors.setTextFill(color);
    lblErrors.setText(text);
    System.out.println("Register: " + text);
  }
}
