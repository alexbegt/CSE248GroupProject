package com.vaj.shoppingcart.controller.warehouse.users;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.AccountStatus;
import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController extends GenericController implements Initializable {

  @FXML
  private Label txtHello;
  @FXML
  private Label txtUsername;

  @FXML
  private Button btnSaveChanges;
  @FXML
  private Button btnCancel;

  @FXML
  private ChoiceBox<AccountStatus> choiceBoxAccountStatus;

  @FXML
  private ChoiceBox<AccountType> choiceBoxAccountType;

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

  public void initializeData(User user) {
    txtHello.setText(txtHello.getText().replace("%s", String.valueOf(user.getName().getFullName())).replace("%i", String.valueOf(user.getAccountId())).replace("%t", user.getAccountType().name().toLowerCase()));
    choiceBoxAccountStatus.setValue(user.getAccountStatus());
    choiceBoxAccountType.setValue(user.getAccountType());

    txtUsername.setText(user.getUsername());
  }

  @FXML
  void handleCancel(MouseEvent event) {
    this.switchToSelectUser(event);
  }

  @FXML
  void handleSaveChanges(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    User user = ShoppingCart.getInstance().getUserDatabase().getUser(txtUsername.getText());

    user.setAccountStatus(choiceBoxAccountStatus.getValue());
    user.setAccountType(choiceBoxAccountType.getValue());

    if (ShoppingCart.getInstance().getUserDatabase().replaceUser(user)) {
      AccountHelper.createDialog("User edited Successfully", "User edited");
    } else {
      AccountHelper.createDialog("User edited failed, try again later.", "User edited");
    }

    this.returnToWarehouseHome(stage);
  }
}
