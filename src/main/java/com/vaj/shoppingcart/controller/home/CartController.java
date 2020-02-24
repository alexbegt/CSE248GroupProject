package com.vaj.shoppingcart.controller.home;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.account.Cart;
import com.vaj.shoppingcart.model.account.User;
import com.vaj.shoppingcart.model.product.Product;
import com.vaj.shoppingcart.model.product.ProductTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController extends GenericController implements Initializable {

  private static DecimalFormat df = new DecimalFormat("0.00");

  @FXML
  private Button btnBackToHome;
  @FXML
  private Button btnAddItems;
  @FXML
  private Button btnRemoveFromCart;
  @FXML
  private Button btnCheckout;

  @FXML
  private Label txtSubTotal;
  @FXML
  private Label txtTax;
  @FXML
  private Label txtTotal;

  @FXML
  private TableView<ProductTable> cartProducts;
  @FXML
  private TableColumn<ProductTable, Integer> productNumber;
  @FXML
  private TableColumn<ProductTable, String> productName;
  @FXML
  private TableColumn<ProductTable, Integer> productQuantity;
  @FXML
  private TableColumn<ProductTable, String> productPrice;
  @FXML
  private TableColumn<ProductTable, String> productTotalPrice;
  @FXML
  private TableColumn<ProductTable, String> productShortName;

  private final String SUBTOTAL_TEXT = "Subtotal: $%s";
  private final String TAX_TEXT = "Tax: $%s";
  private final String TOTAL_TEXT = "Total: $%s";

  @FXML
  void handleBackToHome(MouseEvent event) {
    switchToHome(event);
  }

  @FXML
  void handleAddItems(MouseEvent event) {
    switchToSearch(event, true);
  }

  @FXML
  void handleRemoveFromCart(MouseEvent event) {
    ProductTable selectedProductNumber = cartProducts.getSelectionModel().getSelectedItem();

    Cart cart = ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn().getCart();

    if (selectedProductNumber != null) {
      cartProducts.getItems().remove(selectedProductNumber);
      cart.removeItemFromCart(selectedProductNumber.getProductShortName(), selectedProductNumber.getProductQuantity());
      setTotals(cart);
    }
  }

  @FXML
  void handleCheckout(MouseEvent event) {
    ShoppingCart.getInstance().getHome().processOrder();

    switchToInvoice(event, ShoppingCart.getInstance().getInvoiceDatabase().getInvoice(ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn().getCurrentInvoiceNumber()));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    productNumber.setCellValueFactory(new PropertyValueFactory<ProductTable, Integer>("productNumber"));
    productName.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productName"));
    productShortName.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productShortName"));
    productQuantity.setCellValueFactory(new PropertyValueFactory<ProductTable, Integer>("productQuantity"));
    productPrice.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productPrice"));
    productTotalPrice.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("totalProductPrice"));
  }

  public void initializeData(Stage stage) {
    User user = ShoppingCart.getInstance().getHome().getCurrentUserLoggedIn();

    if (user == null || user.getCart().getCurrentItemsInCart().isEmpty()) {
      AccountHelper.createDialog("Your current cart is empty, Please add an item to view your cart.", "Cart Error");
      handleSwitchingToSearch(stage, false);
    } else {
      Cart cart = user.getCart();

      setTotals(cart);

      List<ProductTable> productTables = new ArrayList<>();

      for (String productKey : cart.getCurrentItemsInCart().keySet()) {
        Product product = ShoppingCart.getInstance().getProductDatabase().getProduct(productKey);
        int quantity = cart.getCurrentItemsInCart().get(productKey);
        double price = product.getPrice();
        String totalPrice = df.format(price * quantity);

        productTables.add(new ProductTable(product.getProductIdentifier(), product.getName(), product.getShortName(), quantity, "$" + price, "$" + totalPrice));
      }

      productNumber.setSortType(TableColumn.SortType.ASCENDING);

      cartProducts.getItems().setAll(productTables);
      cartProducts.getSortOrder().add(productNumber);
    }
  }

  public void setTotals(Cart cart) {
    txtSubTotal.setText(SUBTOTAL_TEXT);
    txtTax.setText(TAX_TEXT);
    txtTotal.setText(TOTAL_TEXT);

    txtSubTotal.setText(txtSubTotal.getText().replace("%s", df.format(Math.abs(cart.getCurrentCartSubTotal()))));
    txtTax.setText(txtTax.getText().replace("%s", df.format(Math.abs(cart.getCurrentTax()))));
    txtTotal.setText(txtTotal.getText().replace("%s", df.format(Math.abs(cart.getCurrentTotal()))));
  }
}
