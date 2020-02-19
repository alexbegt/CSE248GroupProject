package com.vaj.shoppingcart.model.account;

import java.util.ArrayList;

import com.vaj.shoppingcart.model.order.Order;
import com.vaj.shoppingcart.model.order.OrderStatus;

public class AccountInformation {
	
	private User currentUser;
	private User selectedUser;
	
	
	public AccountInformation() {
		
	}
	
	public void changePassword(String currentPassword, String newPassword) {
		if(!(newPassword.equals(currentPassword))) {
			selectedUser.setPassword(newPassword);
		}
	}
	
	public void changeEmail(String currentEmail, String newEmail) {
		if(!(selectedUser.getEmail().equals(currentEmail))) {
			selectedUser.setEmail(newEmail);
		}
	}
	
	public OrderStatus getOrderStatus(int orderId) {
		for(int i=0; i<(selectedUser.getOrderHistory().getOrders().size()); i++) {
			if(orderId == (selectedUser.getOrderHistory().getOrders().get(i).getInvoiceNumber()))
				return selectedUser.getOrderHistory().getOrders().get(i).getOrderStatus();
		}
		return null;
		
	}
	
	public ArrayList<Order> getAllOrders() {
		return selectedUser.getOrderHistory().getOrders();
		}
	
	public void selectAUser(User user) {
		selectedUser = user;
	}
}
