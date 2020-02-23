package com.vaj.shoppingcart.controller.warehouse;

import com.vaj.shoppingcart.model.product.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewProductController implements Initializable {

  @FXML
  private Label txtName;
  @FXML
  private Label txtDescription;
  @FXML
  private Label txtPrice;
  @FXML
  private Label txtOnHands;

  @FXML
  private ImageView productImage;

  @FXML
  private Button btnClose;

  @FXML
  void handleClose(MouseEvent event) {
    // TODO
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  void initializeData(Product product) {
    txtName.setText(txtName.getText().replace("%s", product.getName()));
    txtDescription.setText(txtDescription.getText().replace("%s", product.getDescription()));
    txtPrice.setText(txtPrice.getText().replace("%s", String.valueOf(product.getPrice())));
    txtOnHands.setText(txtOnHands.getText().replace("%s", String.valueOf(product.getOnHands())));
    Image image = new Image("/assets/vaj/shoppingcart/images/" + product.getPhotoPath());

    productImage.setImage(image);
  }
}
