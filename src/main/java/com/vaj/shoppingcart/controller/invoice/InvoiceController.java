package com.vaj.shoppingcart.controller.invoice;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.helper.FileHelper;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.order.Invoice;
import com.vaj.shoppingcart.model.order.InvoiceTable;
import com.vaj.shoppingcart.model.order.Order;
import com.vaj.shoppingcart.model.product.Product;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

  private static DecimalFormat df = new DecimalFormat("0.00");

  @FXML
  private Label txtDear;
  @FXML
  private Label txtOrderNumber;
  @FXML
  private Label txtInvoiceNumber;
  @FXML
  private Label txtSubTotal;
  @FXML
  private Label txtTax;
  @FXML
  private Label txtTotal;
  @FXML
  private TableView<InvoiceTable> cartProducts;
  @FXML
  private TableColumn<InvoiceTable, Integer> productNumber;
  @FXML
  private TableColumn<InvoiceTable, String> productName;
  @FXML
  private TableColumn<InvoiceTable, Integer> productQuantity;
  @FXML
  private TableColumn<InvoiceTable, String> productPrice;
  @FXML
  private TableColumn<InvoiceTable, String> productTotalPrice;

  @FXML
  private Button btnClose;

  @FXML
  void handleClose(MouseEvent event) {
    FileHelper.loadAllDatabases(ShoppingCart.getInstance(), "files/users.json", "files/products.json", "files/orders.json", "files/invoices.json");

    try {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/invoice/invoice.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);

      User user = ShoppingCart.getInstance().getUserDatabase().getUser("Test_Bot");
      Invoice invoice = ShoppingCart.getInstance().getInvoiceDatabase().getInvoice(user.getCurrentInvoiceNumber());

      InvoiceController controller = loader.<InvoiceController>getController();
      controller.initializeData(invoice, stage);

      stage.show();
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void initializeData(Invoice invoice, Stage stage) {
    User user = ShoppingCart.getInstance().getUserDatabase().getUser(invoice.getUserName());
    Order order = ShoppingCart.getInstance().getOrderDatabase().getOrder(user.getCurrentOrderNumber());

    if (user == null || order == null || order.isEmpty() || !order.getUsername().equalsIgnoreCase(user.getUsername())) {
      AccountHelper.createDialog("Unable to initialize data... Returning home.", "Invoice Error");
      try {
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/login/login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    } else {
      txtDear.setText(txtDear.getText().replace("%s", user.getName().getFullName()));
      txtOrderNumber.setText(txtOrderNumber.getText().replace("%s", String.valueOf(invoice.getOrderNumber())));
      txtInvoiceNumber.setText(txtInvoiceNumber.getText().replace("%s", String.valueOf(invoice.getInvoiceNumber())));
      txtSubTotal.setText(txtSubTotal.getText().replace("%s", df.format(order.getSubTotal())));
      txtTax.setText(txtTax.getText().replace("%s", df.format(order.getTax())));
      txtTotal.setText(txtTotal.getText().replace("%s", df.format(order.getTotal())));

      List<InvoiceTable> invoiceTable = new ArrayList<>();

      for (String productKey : order.getProducts().keySet()) {
        Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(productKey);
        int quantity = order.getProducts().get(productKey);
        double price = product.getPrice();
        String totalPrice = df.format(price * quantity);

        invoiceTable.add(new InvoiceTable(product.getProductIdentifier(), product.getName(), quantity, "$" + price, "$" + totalPrice));
      }

      productNumber.setSortType(TableColumn.SortType.ASCENDING);

      cartProducts.getItems().setAll(invoiceTable);
      cartProducts.getSortOrder().add(productNumber);
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
    productNumber.setCellValueFactory(new PropertyValueFactory<InvoiceTable, Integer>("productNumber"));
    productName.setCellValueFactory(new PropertyValueFactory<InvoiceTable, String>("productName"));
    productQuantity.setCellValueFactory(new PropertyValueFactory<InvoiceTable, Integer>("productQuantity"));
    productPrice.setCellValueFactory(new PropertyValueFactory<InvoiceTable, String>("productPrice"));
    productTotalPrice.setCellValueFactory(new PropertyValueFactory<InvoiceTable, String>("totalProductPrice"));
  }
}
