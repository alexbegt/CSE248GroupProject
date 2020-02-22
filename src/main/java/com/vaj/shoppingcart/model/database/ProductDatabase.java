package com.vaj.shoppingcart.model.database;

import java.util.TreeMap;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.product.Product;

public class ProductDatabase {
	
	  public static int PRODUCT_ID = 0;

	  public TreeMap<String, Product> productTreeMap;

	  private final ShoppingCart shoppingCart;

	  public ProductDatabase(ShoppingCart shoppingCartIn) {
	    this.shoppingCart = shoppingCartIn;

	    this.productTreeMap = new TreeMap<String, Product>();
	  }

	  public ShoppingCart getShoppingCart() {
	    return shoppingCart;
	  }

	 
	  /**
	   * Finds the product with the given product name.
	   *
	   * @param String - searches through product names
	   * @return If the product is found, returns true. If false, product is not found in the TreeMap.
	   */
	  public boolean findProduct(String productName) {
		    return this.productTreeMap.containsKey(productName);
		  }
	  
	  /**
	   * Adds a product to the database if it doesn't exist.
	   *
	   * @param product - Product object passed
	   * @return boolean, if true added successfully. If false added unsuccessfully.
	   */
	  public boolean addProduct(Product product) {
		    if (this.findProduct(product.getShortName())) {
		      return false;
		    } else {
		      this.productTreeMap.put(product.getShortName(), product);
		      return true;
		    }
		  }
	  
	  /**
	   * removes a product with the given name.
	   *
	   * @param name - key value to search for
	   * @return If the product is found, removes product and returns true. If false, not found in the TreeMap.
	   */
	  public boolean removeProduct(String name) {
	    Product product = this.productTreeMap.remove(name);

	    return product != null;
	  }

	  /**
	   * Replaces a product with the given product name.
	   * FOR ADMIN ONLY.
	   * @param Product - searches through products
	   * @return If the user is found, returns true and replaces. If false, not found in the TreeMap.
	   */
	  public boolean replaceProduct(Product product) {
		  if(!this.findProduct(product.getShortName())) {
			  return false;
		  }
		  else {
			  this.productTreeMap.replace(product.getShortName(), product);
			  return true;
		  }
	  }
	  
	  /**
	   * Gets a product with the given name
	   *
	   * @param name - key value to search for
	   * @return If the product is found, returns that product. If they are not found, it returns a null.
	   */
	  public Product getProduct(String name) {
	    return this.productTreeMap.getOrDefault(name, null);
	  }
	  
	  

	public void setProductTreeMap(TreeMap productTreeMap) {
		this.productTreeMap = productTreeMap;
	}


	public TreeMap<String, Product> getProductTreeMap() {
		return productTreeMap;
	}
	
	
}


