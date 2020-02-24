package com.vaj.shoppingcart.controller;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.home.CartController;
import com.vaj.shoppingcart.controller.home.SearchController;
import com.vaj.shoppingcart.controller.invoice.InvoiceController;
import com.vaj.shoppingcart.controller.warehouse.AdminPanelController;
import com.vaj.shoppingcart.model.order.Invoice;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GenericController  {

  public void switchToCart(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/home/cart.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      CartController controller = loader.<CartController>getController();
      controller.initializeData(stage);

      stage.show();
    } catch (IOException ex) {
      System.err.println("Handle Cart: " + ex.getMessage());
    }
  }

  public void switchToHome(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/home/home.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println("Handle Forgot Password: " + ex.getMessage());
    }
  }

  public void switchToSearch(MouseEvent event, boolean isFromCart) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    stage.close();

    handleSwitchingToSearch(stage, isFromCart);
  }

  public void handleSwitchingToSearch(Stage stage, boolean isFromCart) {
    try {
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/home/search.fxml"));

      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);
      SearchController searchController = loader.<SearchController>getController();
      searchController.initializeData(stage, isFromCart);

      stage.show();
    } catch (IOException ex) {
      System.err.println("Generic Home: " + ex.getMessage());
    }
  }

  /**
   * Returns the user back to the login pane.
   *
   * @param event the mouse event
   */
  public void returnToLogin(MouseEvent event) {
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

  /**
   * Returns the admin back to the warehouse home screen.
   *
   * @param stage the current stage
   */
  public void returnToWarehouseHome(Stage stage) {
    try {
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/warehouse/adminpanel.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      AdminPanelController controller = loader.<AdminPanelController>getController();
      controller.initializeData(ShoppingCart.getInstance().getHome(), stage);

      stage.show();
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void switchToSelectUser(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/warehouse/user/selectauser.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void switchToSelectProduct(MouseEvent event) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/warehouse/product/selectaproduct.fxml")));
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void returnToHome(Stage stage) {
    try {
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/home/home.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      stage.show();
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void switchToInvoice(MouseEvent event, Invoice invoice) {
    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/invoice/invoice.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

      InvoiceController controller = loader.<InvoiceController>getController();
      controller.initializeData(invoice, stage);

    } catch (IOException ex) {
      System.err.println("Cart: " + ex.getMessage());
    }
  }
}
