package com.vaj.shoppingcart.model.database;

import com.vaj.shoppingcart.ShoppingCart;
import com.vaj.shoppingcart.model.order.Invoice;

import java.util.TreeMap;

public class InvoiceDatabase {

  public static int INVOICE_ID = 0;
  public TreeMap<Integer, Invoice> invoiceTreeMap;
  private final ShoppingCart shoppingCart;

  public InvoiceDatabase(ShoppingCart shoppingCartIn) {
    this.shoppingCart = shoppingCartIn;

    this.invoiceTreeMap = new TreeMap<Integer, Invoice>();
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Adds an invoice to the database if it doesn't exist.
   *
   * @param invoice - Invoice object passed
   * @return boolean, if true added successfully. If false added unsuccessfully.
   */
  public boolean addInvoice(Invoice invoice) {
    if (this.findInvoice(invoice.getInvoiceNumber())) {
      return false;
    } else {
      this.invoiceTreeMap.put(invoice.getInvoiceNumber(), invoice);
      return true;
    }
  }

  /**
   * Replaces an invoice with the new invoice (same invoice numbers)
   *
   * @param invoice - searches through invoices
   * @return If the invoice is found, returns true and replaces. If false, not found in the TreeMap.
   */
  public boolean replaceInvoice(Invoice invoice) {
    if (!this.findInvoice(invoice.getInvoiceNumber())) {
      return false;
    } else {
      this.invoiceTreeMap.replace(invoice.getInvoiceNumber(), invoice);
      return true;
    }
  }

  /**
   * Finds the invoice with the given invoice id.
   *
   * @param invoiceId - searches through all invoices to match invoice id
   * @return If the invoice is found, returns true. If false, invoice is not found in the TreeMap.
   */
  public boolean findInvoice(Integer invoiceId) {
    return this.invoiceTreeMap.containsKey(invoiceId);
  }

  public void setInvoiceTreeMap(TreeMap<Integer, Invoice> invoiceTreeMap) {
    this.invoiceTreeMap = invoiceTreeMap;
  }

  public TreeMap<Integer, Invoice> getInvoiceTreeMap() {
    return invoiceTreeMap;
  }
}
