package com.vaj.shoppingcart.controller.warehouse.users;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.account.UserTable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrEditUsersController extends GenericController implements Initializable {

  @FXML
  private TableView<UserTable> users;

  @FXML
  private TableColumn<UserTable, Integer> userId;

  @FXML
  private TableColumn<UserTable, String> username;

  @FXML
  private TableColumn<UserTable, String> fullName;

  @FXML
  private TableColumn<UserTable, Integer> totalOrders;

  @FXML
  private Button btnSubmit;
  @FXML
  private Button btnCancel;
  @FXML
  private Label lblErrors;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<UserTable> userTables = new ArrayList<>();

    for (String userName : ShoppingCart.getInstance().getUserDatabase().getUserTreeMap().keySet()) {
      User user = ShoppingCart.getInstance().getUserDatabase().getUserTreeMap().get(userName);

      userTables.add(new UserTable(user.getAccountId(), user.getUsername(), user.getName().getFullName(), user.getOrderHistory().size()));
    }

    userId.setSortType(TableColumn.SortType.ASCENDING);

    users.getItems().setAll(userTables);
    users.getSortOrder().add(userId);

    userId.setCellValueFactory(new PropertyValueFactory<UserTable, Integer>("userIdentifier"));
    username.setCellValueFactory(new PropertyValueFactory<UserTable, String>("username"));
    fullName.setCellValueFactory(new PropertyValueFactory<UserTable, String>("fullName"));
    totalOrders.setCellValueFactory(new PropertyValueFactory<UserTable, Integer>("totalOrders"));
  }

  @FXML
  void handleCancel(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    returnToWarehouseHome(stage);
  }

  @FXML
  void handleSubmit(MouseEvent event) {
    UserTable selectedUser = users.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
      User user = ShoppingCart.getInstance().getUserDatabase().getUser(selectedUser.getUsername());

      try {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/warehouse/user/edituser.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setResizable(false);

        EditUserController controller = loader.<EditUserController>getController();
        controller.initializeData(user);

        stage.show();
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    } else {
      this.setErrorText(Color.TOMATO, "Please select a user from the list above");
    }
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
    System.out.println("Find User: " + text);
  }
}
