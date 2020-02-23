package com.vaj.shoppingcart.controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CartController implements Initializable {

	@FXML
	private Button btnBackToHome;
	@FXML
	private Button btnAddItems;
	@FXML
	private Button btnRemoveFromCart;
	@FXML
	private Button btnCheckout;
	@FXML
	private TableColumn<?, String> tblProduct;
	@FXML
	private TableColumn<?, Integer> tblQuantity;
	
	@FXML
	void handleBackToHome(MouseEvent event) {
	    try {
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.close();

	        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/home.fxml")));
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();

	      } catch (IOException ex) {
	        System.err.println("Handle Forgot Password: " + ex.getMessage());
	      }
	}
	
	@FXML
	void handleAddItems(MouseEvent event) {
	    try {
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.close();

	        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/login/search.fxml")));
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();

	      } catch (IOException ex) {
	        System.err.println("Handle Forgot Password: " + ex.getMessage());
	      }

	}
	
	@FXML
	void handleRemoveFromCart(MouseEvent event) {
		String selectedItem = (String) tblProduct.getTableView().getSelectionModel().getSelectedItem();
		tblProduct.getTableView().getItems().remove(selectedItem);
	}
	
	@FXML
	void handleCheckout(MouseEvent event) {
	    try {
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.close();

	        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/checkout.fxml")));
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();

	      } catch (IOException ex) {
	        System.err.println("Handle Forgot Password: " + ex.getMessage());
	      }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
}
