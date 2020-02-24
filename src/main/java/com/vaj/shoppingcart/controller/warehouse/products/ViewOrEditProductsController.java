package com.vaj.shoppingcart.controller.warehouse.products;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.model.product.Product;
import com.vaj.shoppingcart.model.product.ProductTable;
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

public class ViewOrEditProductsController extends GenericController implements Initializable {

  @FXML
  private TableView<ProductTable> products;
  @FXML
  private TableColumn<ProductTable, Integer> productNumber;
  @FXML
  private TableColumn<ProductTable, String> productName;
  @FXML
  private TableColumn<ProductTable, Integer> productOnHands;
  @FXML
  private TableColumn<ProductTable, String> productPrice;
  @FXML
  private TableColumn<ProductTable, String> productShortName;

  @FXML
  private Button btnSubmit;
  @FXML
  private Button btnCancel;

  @FXML
  private Label lblErrors;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<ProductTable> productTables = new ArrayList<>();

    for (String productKey : ShoppingCart.getInstance().getProductDatabase().getProductTreeMap().keySet()) {
      Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(productKey);
      int onHands = product.getOnHands();
      double price = product.getPrice();

      productTables.add(new ProductTable(product.getProductIdentifier(), product.getName(), product.getShortName(), onHands, "$" + price));
    }

    this.productNumber.setSortType(TableColumn.SortType.ASCENDING);

    this.products.getItems().setAll(productTables);
    this.products.getSortOrder().add(productNumber);

    this.productNumber.setCellValueFactory(new PropertyValueFactory<ProductTable, Integer>("productNumber"));
    this.productName.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productName"));
    this.productShortName.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productShortName"));
    this.productOnHands.setCellValueFactory(new PropertyValueFactory<ProductTable, Integer>("productQuantity"));
    this.productPrice.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productPrice"));
  }

  @FXML
  void handleCancel(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    returnToWarehouseHome(stage);
  }

  @FXML
  void handleSubmit(MouseEvent event) {
    ProductTable selectedProduct = products.getSelectionModel().getSelectedItem();

    if (selectedProduct != null) {
      Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(selectedProduct.getProductShortName());

      try {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/warehouse/product/editproduct.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setResizable(false);

        EditProductController controller = loader.<EditProductController>getController();
        controller.initializeData(product);

        stage.show();
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    } else {
      this.setErrorText(Color.TOMATO, "Please select a product from the list above");
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
    System.out.println("Edit Product: " + text);
  }
}
