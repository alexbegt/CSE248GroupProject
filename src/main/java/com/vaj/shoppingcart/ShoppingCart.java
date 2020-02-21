package com.vaj.shoppingcart;

import com.vaj.shoppingcart.helper.FileHelper;
import com.vaj.shoppingcart.model.database.Database;
import com.vaj.shoppingcart.model.login.LoginAndRegister;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ShoppingCart extends Application {

  public static ShoppingCart instance;
  public final LoginAndRegister loginAndRegister;
  public final Database database;

  public ShoppingCart() {
    instance = this;

    this.loginAndRegister = new LoginAndRegister(this);
    this.database = new Database(this);
  }

  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   * @throws Exception if something goes wrong
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/login.fxml"));

    primaryStage.initStyle(StageStyle.DECORATED);
    primaryStage.setTitle("Shopping Cart");

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
    
    primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

    FileHelper.loadUsersAndProducts(this, "files/users.json", "files/products.json");
  }
  
  /**
   * When window is closed, save the user and products info.
   *
   * @param event the mouse event.
   */
  private void closeWindowEvent(WindowEvent event) {
	    FileHelper.saveUsersAndProducts(this, "files/users.json", "files/products.json");
	  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  public static ShoppingCart getInstance() {
    return instance;
  }

  /*
   * Returns the database class to be used with a controller.
   */
  public Database getDatabase() {
    return this.database;
  }

  /*
  * Returns the Login and Register class to be used with a controller.
   */
  public LoginAndRegister getLoginAndRegister() {
    return this.loginAndRegister;
  }
}
