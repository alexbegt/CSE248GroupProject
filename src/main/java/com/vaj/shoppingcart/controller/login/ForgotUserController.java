package com.vaj.shoppingcart.controller.login;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.login.ResetStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgotUserController extends GenericController implements Initializable {

  @FXML
  private TextField txtEmail;
  @FXML
  private Label lblErrors;
  @FXML
  private Button btnSubmit;
  @FXML
  private Button btnCancel;

  /**
   * Handles when a user presses the submit button.
   *
   * @param event the mouse event
   */
  @FXML
  void handleSubmit(MouseEvent event) {
    String emailText = txtEmail.getText();

    if (emailText.isEmpty()) {
      this.setErrorText(Color.TOMATO, "Please enter an email");
    } else {
      Pair<ResetStatus, String> forgotUsername = ShoppingCart.getInstance().getLoginAndRegister().forgotUsername(emailText);

      switch (forgotUsername.getKey()) {
        case INVALID_USER:
          this.setErrorText(Color.TOMATO, "Invalid email.");
          break;
        case ACCOUNT_DISABLED:
          this.setErrorText(Color.TOMATO, "This account has been disabled.");
          break;
        case SUCCESS:
          this.setErrorText(Color.GREEN, "Your account name is: " + forgotUsername.getValue());
          AccountHelper.createDialog("Your account name is: " + forgotUsername.getValue(), "Forgot Username");
          returnToLogin(event);
          break;
        case ERROR:
          this.setErrorText(Color.TOMATO, "Unknown error.");
          break;
      }
    }
  }

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
    System.out.println("Forgot User: " + text);
  }
}
