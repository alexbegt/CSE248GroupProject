package com.vaj.shoppingcart.controller.home;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.helper.ProductHelper;
import com.vaj.shoppingcart.model.account.Cart;
import com.vaj.shoppingcart.model.product.Product;
import com.vaj.shoppingcart.model.product.ProductTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends GenericController implements Initializable {

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
  private TextField txtQuantity;

  @FXML
  private Button btnSubmit;
  @FXML
  private Button btnCancel;

  @FXML
  private Label lblErrors;

  private boolean isFromCart;

  @FXML
  void handleCancel(MouseEvent event) {
    if (this.isFromCart) {
      switchToCart(event);
    } else {
      switchToHome(event);
    }
  }

  @FXML
  void handleSubmit(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    ProductTable selectedProductNumber = products.getSelectionModel().getSelectedItem();
    String quantityText = txtQuantity.getText();

    if (selectedProductNumber == null) {
      this.setErrorText(Color.TOMATO, "Please select a product!");
    } else if (quantityText.isEmpty()) {
      this.setErrorText(Color.TOMATO, "Please enter a quantity!");
    } else if (!ProductHelper.validateQuantity(quantityText)) {
      this.setErrorText(Color.TOMATO, "Please ensure your quantity is a number (I.E. 1)");
    } else {
      int quantity = Integer.parseInt(quantityText);

      if (quantity > selectedProductNumber.getProductQuantity()) {
        this.setErrorText(Color.TOMATO, "That quantity is too big, it must be between 1 and " + selectedProductNumber.getProductQuantity());
      } else if (quantity < 1) {
        this.setErrorText(Color.TOMATO, "That quantity is too small, it must be between 1 and " + selectedProductNumber.getProductQuantity());
      } else {
        Cart cart = ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn().getCart();

        if (cart.hasProductInCart(selectedProductNumber.getProductShortName())) {
          cart.updateQuantity(selectedProductNumber.getProductShortName(), quantity);
        } else {
          cart.addItemToCart(selectedProductNumber.getProductShortName(), quantity);
        }

        AccountHelper.createDialog("Product added to your cart successfully", "Add product to cart");

        this.handleSwitchingToSearch(stage, false);
      }
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.productNumber.setCellValueFactory(new PropertyValueFactory<ProductTable, Integer>("productNumber"));
    this.productName.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productName"));
    this.productShortName.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productShortName"));
    this.productOnHands.setCellValueFactory(new PropertyValueFactory<ProductTable, Integer>("productQuantity"));
    this.productPrice.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productPrice"));
  }

  public void initializeData(Stage stage, boolean isFromCart) {
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
    this.isFromCart = isFromCart;
  }

  @FXML
  void handleSwitchToCart(MouseEvent event) {
    this.switchToCart(event);
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
