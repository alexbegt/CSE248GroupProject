package com.vaj.shoppingcart.controller.login;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.login.LoginStatus;
import com.vaj.shoppingcart.model.login.ResetStatus;
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
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private TextField txtUsername;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private Button btnSignIn;
  @FXML
  private Label btnForgotUsername;
  @FXML
  private Label btnForgotPassword;
  @FXML
  private Button btnSignUp;
  @FXML
  private Label lblErrors;

  /**
   * Handles when a user presses the forgot password button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleForgotPassword(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/forgotpassword.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println("Handle Forgot Password: " + ex.getMessage());
    }
  }

  /**
   * Handles when a user presses the forgot username button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleForgotUsername(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/forgotusername.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println("Handle Forgot Username: " + ex.getMessage());
    }
  }

  /**
   * Handles when a user presses the sign in button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleSignIn(MouseEvent event) {
    String username = txtUsername.getText();
    String password = txtPassword.getText();

    if (username.isEmpty() || password.isEmpty()) {
      this.setErrorText(Color.TOMATO, "Empty credentials");
    } else {
      Pair<LoginStatus, User> logUserIn = ShoppingCart.getInstance().getLoginAndRegister().logUserIn(username, password);
      switch (logUserIn.getKey()) {
        case SUCCESS:
          this.setErrorText(Color.GREEN, "Successful! Logging in...");
          ShoppingCart.getInstance().getHome().setCurrentUserLoggedIn(logUserIn.getValue());
          try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/home/home.fxml")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

          } catch (IOException ex) {
            System.err.println("Login: " + ex.getMessage());
          }
          break;
        case INVALID_USER:
          this.setErrorText(Color.TOMATO, "Invalid username.");
          break;
        case INCORRECT_PASSWORD:
          this.setErrorText(Color.TOMATO, "Incorrect password.");
          break;
        case ACCOUNT_DISABLED:
          this.setErrorText(Color.TOMATO, "This account has been disabled.");
          break;
        case ERROR:
          this.setErrorText(Color.TOMATO, "Unknown error.");
          break;
      }
    }
  }

  /**
   * Handles when a user presses the sign up button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleSignUp(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/register.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
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
    System.out.println("Login: " + text);
  }
}
