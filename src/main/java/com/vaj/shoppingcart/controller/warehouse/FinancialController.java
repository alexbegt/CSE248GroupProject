package com.vaj.shoppingcart.controller.warehouse;

import com.vaj.shoppingcart.helper.AccountHelper;
import com.vaj.shoppingcart.model.warehouse.Financials;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinancialController implements Initializable {

  @FXML
  private Label txtProfit;
  @FXML
  private Label txtTaxesPaid;
  @FXML
  private Label txtProfitWithTax;
  @FXML
  private Label txtTotalOrders;
  @FXML
  private Label txtTotalInvoices;

  @FXML
  private PieChart pieChart;

  @FXML
  private Button btnClose;

  /**
   * Handles closing of the financials screen.
   *
   * @param event the mouse event
   */
  @FXML
  void handleClose(MouseEvent event) {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    returnToWarehouseHome(stage);
  }

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

  /**
   * Sets the data to be shown on the display.
   *
   * @param financials the financials class
   * @param stage the current stage
   */
  public void initializeData(Financials financials, Stage stage) {
    double profit = financials.getProfit();
    double taxes = financials.getTaxes();
    double profitWithTax = financials.getProfitWithTax();
    int orderNumbers = financials.getTotalOrders();
    int invoiceNumbers = financials.getTotalInvoices();

    if (profit == 0.00 || taxes == 0.00 || profitWithTax == 0.00) {
      AccountHelper.createDialog("Unable to initialize data... Returning home.", "Financials Error");
      returnToWarehouseHome(stage);
    } else {
      txtProfit.setText(txtProfit.getText().replace("%s", String.valueOf(profit)));
      txtTaxesPaid.setText(txtTaxesPaid.getText().replace("%s", String.valueOf(taxes)));
      txtProfitWithTax.setText(txtProfitWithTax.getText().replace("%s", String.valueOf(profitWithTax)));
      txtTotalOrders.setText(txtTotalOrders.getText().replace("%s", String.valueOf(orderNumbers)));
      txtTotalInvoices.setText(txtTotalInvoices.getText().replace("%s", String.valueOf(invoiceNumbers)));

      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Profit", profit), new PieChart.Data("Taxes", taxes));

      pieChart.setData(pieChartData);
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

  }
}
