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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	
	@FXML
	private Button btnCart;
	@FXML
	private Button btnDatabase;
	@FXML
	private Button btnOrderHistory;
	@FXML
	private Button btnFinancialHistory;
	@FXML
	private Button btnAdminPowers;
	@FXML
	private Label lblPrompt;
	
	@FXML
	void handleCart(MouseEvent event) {
		try {
			Node node = (Node)event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			
			
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/cartcheckout.fxml")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException ex) {
			System.err.println("Handle Cart: " + ex.getMessage());
		}
	}
	
	@FXML
	void handleAdminPowers(MouseEvent event) {
		//if(AccountInformation.currentUser.AccountType.ADMIN)
		//lblPrompt.setText("Sorry, not an admin :(");
		//
		try {
			Node node = (Node)event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/home/homeadmins.fxml")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException ex) {
			System.err.println("Handle Cart: " + ex.getMessage());
		}
	}
	
	@FXML
	void handleOrderHistory(MouseEvent event) {
		try {
			Node node = (Node)event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/assets/vaj/shoppingcart/orderhistory.fxml")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException ex) {
			System.err.println("Handle Cart: " + ex.getMessage());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	
}
