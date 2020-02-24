package com.vaj.shoppingcart.controller.home;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.controller.warehouse.AdminPanelController;
import com.vaj.shoppingcart.model.account.AccountType;
import com.vaj.shoppingcart.model.account.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends GenericController implements Initializable {

  @FXML
  private Button btnCart;
  @FXML
  private Button btnDatabase;
  @FXML
  private Button btnOrderHistory;
  @FXML
  private Button btnFinancialHistory;
  @FXML
  private Button btnAdminPowers;
  @FXML
  private Label lblInformation;
  @FXML
  public Button btnLogout;
  @FXML
  public Button btnShop;

  @FXML
  void handleCart(MouseEvent event) {
    switchToCart(event);
  }

  @FXML
  void handleAdminPowers(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/warehouse/adminpanel.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      AdminPanelController controller = loader.<AdminPanelController>getController();
      controller.initializeData(ShoppingCart.getInstance().getHome(), stage);

      stage.show();
    } catch (IOException ex) {
      System.err.println("Handle Cart: " + ex.getMessage());
    }
  }

  @FXML
  void handleOrderHistory(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/home/orderhistory.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      OrderHistoryController controller = loader.<OrderHistoryController>getController();
      controller.initializeData(stage);

      stage.show();
    } catch (Exception ex) {
      System.err.println("Handle Cart: " + ex.getMessage());
    }
  }

  @FXML
  void handleLogout(MouseEvent event) {
    ShoppingCart.getInstance().getHome().setCurrentUserLoggedIn(null);
    returnToLogin(event);
  }

  @FXML
  void handleShop(MouseEvent event) {
    switchToSearch(event, false);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    User user = ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn();

    if (user != null) {
      if (user.getAccountType() != AccountType.ADMIN) {
        btnAdminPowers.setVisible(false);
      } else {
        btnAdminPowers.setVisible(true);
      }
    }
  }


}
