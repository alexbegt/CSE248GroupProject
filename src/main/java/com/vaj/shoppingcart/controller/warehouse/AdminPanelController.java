package com.vaj.shoppingcart.controller.warehouse;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.model.home.Home;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanelController extends GenericController {

  @FXML
  private Label txtHello;
  @FXML
  private Label txtTotalUsers;
  @FXML
  private Label txtTotalProducts;
  @FXML
  private Label txtTotalOrders;
  @FXML
  private Label txtTotalInvoices;

  @FXML
  private Button btnViewAndEditUsers;
  @FXML
  private Button btnCreateUser;
  @FXML
  private Button btnViewAndEditProducts;
  @FXML
  private Button btnReturnToHome;
  @FXML
  private Button btnViewFinancials;

  /**
   * Sets the data to be shown on the display.
   *
   * @param home  the home class
   * @param stage the current stage
   */
  public void initializeData(Home home, Stage stage) {
    int userNumbers = home.getTotalUsers();
    int totalProducts = home.getTotalProducts();
    int orderNumbers = home.getTotalOrders();
    int invoiceNumbers = home.getTotalInvoices();

    txtHello.setText(txtHello.getText().replace("%s", String.valueOf(home.getCurrentUserLoggedIn().getName().getFullName())));
    txtTotalUsers.setText(txtTotalUsers.getText().replace("%s", String.valueOf(userNumbers)));
    txtTotalProducts.setText(txtTotalProducts.getText().replace("%s", String.valueOf(totalProducts)));
    txtTotalOrders.setText(txtTotalOrders.getText().replace("%s", String.valueOf(orderNumbers)));
    txtTotalInvoices.setText(txtTotalInvoices.getText().replace("%s", String.valueOf(invoiceNumbers)));
  }

  @FXML
  void handleCreateProduct(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/warehouse/product/addproduct.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  @FXML
  void handleViewAndEditProducts(MouseEvent event) {
    this.switchToSelectProduct(event);
  }

  @FXML
  void handleCreateUser(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/warehouse/user/adduser.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  @FXML
  void handleViewAndEditUsers(MouseEvent event) {
    this.switchToSelectUser(event);
  }

  @FXML
  void handleReturnToHome(MouseEvent event) {
    switchToHome(event);
  }

  @FXML
  void handleViewFinancials(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/warehouse/financialhistory.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      FinancialController controller = loader.<FinancialController>getController();
      controller.initializeData(ShoppingCart.getInstance().getFinancials(), stage);

      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
