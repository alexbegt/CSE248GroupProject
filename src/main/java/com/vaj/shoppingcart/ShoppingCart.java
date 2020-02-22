package com.vaj.shoppingcart;

import com.vaj.shoppingcart.helper.FileHelper;
import com.vaj.shoppingcart.model.database.InvoiceDatabase;
import com.vaj.shoppingcart.model.database.OrderDatabase;
import com.vaj.shoppingcart.model.database.ProductDatabase;
import com.vaj.shoppingcart.model.database.UserDatabase;
import com.vaj.shoppingcart.model.login.LoginAndRegister;
import com.vaj.shoppingcart.model.product.AddOrEditProduct;

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
  public final UserDatabase userDatabase;
  public final ProductDatabase productDatabase;
  public final InvoiceDatabase invoiceDatabase;
  public final OrderDatabase orderDatabase;
  public final AddOrEditProduct addOrEditProduct;

  public ShoppingCart() {
    instance = this;

    this.loginAndRegister = new LoginAndRegister(this);
    this.userDatabase = new UserDatabase(this);
    this.productDatabase = new ProductDatabase(this);
    this.invoiceDatabase = new InvoiceDatabase(this);
    this.orderDatabase = new OrderDatabase(this);
    this.addOrEditProduct = new AddOrEditProduct(this);
    
    
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
   * Returns the invoiceDatabase class to be used with a controller.
   */
  public InvoiceDatabase getInvoiceDatabase() {
    return this.invoiceDatabase;
  }
  
  /*
   * Returns the userDatabase class to be used with a controller.
   */
  public UserDatabase getUserDatabase() {
    return this.userDatabase;
  }
  
  /*
   * Returns the productDatabase class to be used with a controller.
   */
  public ProductDatabase getProductDatabase() {
    return this.productDatabase;
  }
  
  /*
   * Returns the orderDatabase class to be used with a controller.
   */
  public OrderDatabase getOrderDatabase() {
    return this.orderDatabase;
  }

  /*
  * Returns the Login and Register class to be used with a controller.
   */
  public LoginAndRegister getLoginAndRegister() {
    return this.loginAndRegister;
  }
  /*
   * Returns the Add or Edit Product class to be used with a controller.
   */
  public AddOrEditProduct getAddOrEditProduct() {
    return this.addOrEditProduct;
  }
}
