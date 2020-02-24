package com.vaj.shoppingcart.controller.home;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.order.Invoice;
import com.vaj.shoppingcart.model.order.Order;
import com.vaj.shoppingcart.model.order.OrderTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderHistoryController extends GenericController implements Initializable {

  @FXML
  private TableView<OrderTable> orders;
  @FXML
  private TableColumn<OrderTable, Integer> orderNumber;
  @FXML
  private TableColumn<OrderTable, Integer> invoiceNumber;
  @FXML
  private TableColumn<OrderTable, String> subTotal;
  @FXML
  private TableColumn<OrderTable, String> tax;
  @FXML
  private TableColumn<OrderTable, String> total;

  @FXML
  private Label lblErrors;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    orderNumber.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("orderNumber"));
    invoiceNumber.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("invoiceNumber"));
    subTotal.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("subTotal"));
    tax.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("tax"));
    total.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("total"));
  }

  public void initializeData(Stage stage) {
    List<OrderTable> orderTables = new ArrayList<>();

    if (ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn() == null || ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn().getInvoiceHistory().isEmpty() || ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn().getOrderHistory().isEmpty()) {
      AccountHelper.createDialog("You have no orders, please place an order.", "Order History Error");
      handleSwitchingToSearch(stage, false);
    } else {
      for (Integer integer : ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn().getInvoiceHistory()) {
        Invoice invoice = ShoppingCart.getInstance().getInvoiceDatabase().getInvoice(integer);
        Order order = ShoppingCart.getInstance().getOrderDatabase().getOrder(invoice.getOrderNumber());

        orderTables.add(new OrderTable(invoice.getOrderNumber(), invoice.getInvoiceNumber(), "$" + order.getSubTotal(), "$" + order.getTax(), "$" + order.getTotal()));
      }

      orderNumber.setSortType(TableColumn.SortType.ASCENDING);
      invoiceNumber.setSortType(TableColumn.SortType.ASCENDING);

      orders.getItems().setAll(orderTables);
      orders.getSortOrder().add(orderNumber);
      orders.getSortOrder().add(invoiceNumber);
    }
  }

  @FXML
  void handleCancel(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    this.returnToHome(stage);
  }

  @FXML
  void handleSubmit(MouseEvent event) {
    OrderTable selectedOrders = orders.getSelectionModel().getSelectedItem();

    if (selectedOrders != null) {
      this.switchToInvoice(event, ShoppingCart.getInstance().getInvoiceDatabase().getInvoice(selectedOrders.getInvoiceNumber()));
    } else {
      this.setErrorText(Color.TOMATO, "Please select an order from the list above");
    }
  }

  /**
   * Sets the text of the label on the screen
   *
   * @param color the color.
   * @param text  the actual text
   */
  private void setErrorText(Color color, String text) {
    this.lblErrors.setTextFill(color);
    this.lblErrors.setText(text);
    System.out.println("Search: " + text);
  }
}
