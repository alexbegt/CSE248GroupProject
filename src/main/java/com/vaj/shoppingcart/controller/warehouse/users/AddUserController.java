package com.vaj.shoppingcart.controller.warehouse.users;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.AccountStatus;
import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.Address;
import com.vaj.shoppingcart.model.account.Name;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController extends GenericController implements Initializable {

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
  private ChoiceBox<AccountStatus> choiceBoxAccountStatus;

  @FXML
  private ChoiceBox<AccountType> choiceBoxAccountType;

  @FXML
  void handleCancel(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    returnToWarehouseHome(stage);
  }

  @FXML
  void handleSubmit(MouseEvent event) {
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

    AccountType accountType = choiceBoxAccountType.getValue();
    AccountStatus accountStatus = choiceBoxAccountStatus.getValue();

    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || zipCode.isEmpty() || accountType == null || accountStatus == null) {
      this.setErrorText(Color.TOMATO, "Please ensure you have entered all the required information.");
    } else {
      switch (ShoppingCart.getInstance().getLoginAndRegister().registerUser(new Name(firstName, lastName), new Address(address, city, state, zipCode), username, password, confirmPassword, email, accountStatus, accountType)) {
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
          this.setErrorText(Color.GREEN, "Successful!");
          AccountHelper.createDialog("User added Successfully!", "Admin Panel");
          Node node = (Node) event.getSource();
          Stage stage = (Stage) node.getScene().getWindow();

          returnToWarehouseHome(stage);
          break;
      }
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<AccountStatus> accountStatuses = FXCollections.observableArrayList();
    ObservableList<AccountType> accountTypes = FXCollections.observableArrayList();

    accountStatuses.addAll(AccountStatus.ACTIVE, AccountStatus.DISABLED);
    accountTypes.addAll(AccountType.USER, AccountType.ADMIN);

    choiceBoxAccountStatus.setItems(accountStatuses);
    choiceBoxAccountStatus.setValue(AccountStatus.ACTIVE);

    choiceBoxAccountType.setItems(accountTypes);
    choiceBoxAccountType.setValue(AccountType.USER);
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
    System.out.println("Add User: " + text);
  }
}
