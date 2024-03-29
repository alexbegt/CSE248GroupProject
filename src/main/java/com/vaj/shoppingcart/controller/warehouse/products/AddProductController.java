package com.vaj.shoppingcart.controller.warehouse.products;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.controller.GenericController;
import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.product.ProductStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController extends GenericController implements Initializable {

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
  private ImageView productImage;

  @FXML
  private ChoiceBox<ProductStatus> productStatus;

  /**
   * Handles when a user presses the cancel button.
   *
   * @param event the mouse Event
   */
  @FXML
  void handleCancel(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    returnToWarehouseHome(stage);
  }

  /**
   * Handles when a user presses the submit button.
   *
   * @param event the mouse Event
   */
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
          AccountHelper.createDialog("Product added Successfully!", "Admin Panel");
          Node node = (Node) event.getSource();
          Stage stage = (Stage) node.getScene().getWindow();

          returnToWarehouseHome(stage);
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

  /**
   * Sets the text of the label on the screen
   *
   * @param color the color.
   * @param text  the actual text
   */
  private void setErrorText(Color color, String text) {
    lblErrors.setTextFill(color);
    lblErrors.setText(text);
    System.out.println("Add Product: " + text);
  }

  @FXML
  public void userTyped(KeyEvent event) {
    String text = txtPhotoPath.getText();

    Image image = null;

    if (!text.equalsIgnoreCase("")) {
      try {
        image = new Image("/assets/vaj/shoppingcart/images/" + text);
      } catch (Exception e) {
        image = new Image("/assets/vaj/shoppingcart/images/missing_image.png");
      }

      if (image != null)
        productImage.setImage(image);
    }
  }
}
