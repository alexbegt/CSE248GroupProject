package com.vaj.shoppingcart.controller.warehouse;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.product.Product;
import com.vaj.shoppingcart.model.product.ProductStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

  @FXML
  private TextField txtShortName;
  @FXML
  private TextField txtName;
  @FXML
  private TextField txtDescription;
  @FXML
  private TextField txtPrice;
  @FXML
  private TextField txtAvailableAmount;
  @FXML
  private TextField txtPhotoPath;

  @FXML
  private Button btnSubmit;
  @FXML
  private Button btnCancel;

  @FXML
  private Label lblErrors;

  @FXML
  private ChoiceBox<ProductStatus> productStatus;

  @FXML
  void handleCancel(MouseEvent event) {

  }

  @FXML
  void handleSubmit(MouseEvent event) {
    String shortName = txtShortName.getText();
    String name = txtName.getText();
    String description = txtDescription.getText();
    String price = txtPrice.getText();
    String availableAmount = txtAvailableAmount.getText();
    String photoPath = txtPhotoPath.getText();

    if (shortName.isEmpty() || name.isEmpty() || description.isEmpty() || price.isEmpty() || availableAmount.isEmpty()) {
      this.setErrorText(Color.TOMATO, "Please ensure you have entered all the required information.");
    } else {
      switch (ShoppingCart.getInstance().getAddOrEditProduct().addProduct(shortName, name, description, price, availableAmount, photoPath)) {
        case ITEM_EXISTS:
          this.setErrorText(Color.TOMATO, "An item exists already with that short name.");
          break;
        case INVALID_PRICE:
          this.setErrorText(Color.TOMATO, "Please enter price in double format (I.E. 10.00).");
          break;
        case INVALID_ON_HANDS:
          this.setErrorText(Color.TOMATO, "Please enter on hands in integer format (I.E. 1).");
          break;
        case SUCCESSFUL:
          this.setErrorText(Color.GREEN, "Product added!");
          break;
        case ERROR:
          this.setErrorText(Color.TOMATO, "Unknown error has occurred.");
          break;
      }
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
    txtPrice.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
        txtPrice.setText(oldValue);
      }
    });
  }

  private void setErrorText(Color color, String text) {
    lblErrors.setTextFill(color);
    lblErrors.setText(text);
    System.out.println("Add Product: " + text);
  }
}
