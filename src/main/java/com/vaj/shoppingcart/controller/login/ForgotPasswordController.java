package com.vaj.shoppingcart.controller.login;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.login.ResetStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {

  @FXML
  private TextField txtUsername;
  @FXML
  private Label lblErrors;
  @FXML
  private Button btnSubmit;
  @FXML
  private Button btnCancel;

  @FXML
  void handleSubmit(MouseEvent event) {
    String username = txtUsername.getText();

    if (username.isEmpty()) {
      this.setErrorText(Color.TOMATO, "Please enter a username.");
    } else {
      Pair<ResetStatus, String> forgotPassword = ShoppingCart.getInstance().getLoginAndRegister().forgotPassword(username);

      switch (forgotPassword.getKey()) {
        case INVALID_USER:
          this.setErrorText(Color.TOMATO, "Invalid username.");
          break;
        case ERROR_ENCRYPTING_PASSWORD:
          this.setErrorText(Color.TOMATO, "Error encrypting password.");
          break;
        case ERROR_GENERATING_PASSWORD_SALT:
          this.setErrorText(Color.TOMATO, "Error generating a password hash.");
          break;
        case ACCOUNT_DISABLED:
          this.setErrorText(Color.TOMATO, "This account has been disabled.");
          break;
        case SUCCESS:
          this.setErrorText(Color.GREEN, "Your new Password is: " + forgotPassword.getValue());
          break;
        case ERROR:
          this.setErrorText(Color.TOMATO, "Unknown error.");
          break;
      }
    }
  }

  @FXML
  void handleCancel(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  private void setErrorText(Color color, String text) {
    lblErrors.setTextFill(color);
    lblErrors.setText(text);
    System.out.println("Forgot Password: " + text);
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

}
