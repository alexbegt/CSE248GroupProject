package com.vaj.shoppingcart.controller.warehouse;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GenericWarehouseController {
  /**
   * Returns the admin back to the warehouse home screen.
   *
   * @param stage the current stage
   */
  void returnToWarehouseHome(Stage stage) {
    try {
      stage.close();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/vaj/shoppingcart/warehouse/warehouse.fxml"));
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
