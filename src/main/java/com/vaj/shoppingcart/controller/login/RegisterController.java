package com.vaj.shoppingcart.controller.login;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

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

  @FXML
  void handleCancel(MouseEvent event) {
    this.returnToLogin(event);
  }

  private void returnToLogin(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/login.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

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
          this.setErrorText(Color.GREEN, "Successful! Logging in...");
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

  private void setErrorText(Color color, String text) {
    lblErrors.setTextFill(color);
    lblErrors.setText(text);
    System.out.println("Register: " + text);
  }
}
