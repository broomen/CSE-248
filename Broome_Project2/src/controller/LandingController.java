package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import util.ConnectionUtil;

public class LandingController implements Initializable{
	
	@FXML private AnchorPane landingPane;
	
	@FXML private Button coffeeBtn1;
	@FXML private Button coffeeBtn2;
	@FXML private Button coffeeBtn3;
	@FXML private Button coffeeBtn4;
	@FXML private Button coffeeBtn5;
	@FXML private Button coffeeBtn6;
	@FXML private Button coffeeBtn7;
	@FXML private Button coffeeBtn8;
	
	@FXML private Button condBtn1;
	@FXML private Button condBtn2;
	@FXML private Button condBtn3;
	@FXML private Button condBtn4;
	@FXML private Button condBtn5;
	@FXML private Button condBtn6;
	@FXML private Button condBtn7;
	@FXML private Button condBtn8;
	
	@FXML private Button foodBtn1;
	@FXML private Button foodBtn2;
	@FXML private Button foodBtn3;
	@FXML private Button foodBtn4;
	@FXML private Button foodBtn5;
	@FXML private Button foodBtn6;
	@FXML private Button foodBtn7;
	@FXML private Button foodBtn8;
	
	@FXML private Button qtyBtn1;
	@FXML private Button qtyBtn2;
	@FXML private Button qtyBtn3;
	@FXML private Button qtyBtn4;
	@FXML private Button qtyBtn5;
	@FXML private Button qtyBtn6;
	@FXML private Button qtyBtn7;
	@FXML private Button qtyBtn8;
	
	@FXML private Button backupBtn;
	@FXML private Button restoreBtn;
	@FXML private Button signOutBtn;
	
	@FXML private Button addBtn;
	@FXML private Button checkoutBtn;
	
	@FXML private Label itemPreview;
	@FXML private Label qtyPreview;
	
	@FXML private ListView<String> receiptListView;
	
	private String itemSelectionString;
	private String itemQtyString;
	private String itemPriceString;
	private List<String> boxList;
	private String selectionSwitch;
	private List<String> receiptOrders;
	private List<String> receiptQty;
	private List<String> receiptPrice;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		receiptOrders = new ArrayList<String>();
		receiptQty = new ArrayList<String>();
		receiptPrice = new ArrayList<String>();
	}
	
	@FXML
	public void handleQtyChoice(ActionEvent event) {
		Object o = event.getSource();
		Button b = null;
		if(o instanceof Button) {
			b = (Button)o;
		}
		if(b != null) {
			itemQtyString = b.getText();
			qtyPreview.setText(itemQtyString);
		}
	}
	
	@FXML
	public void handleCheckout(ActionEvent event) {
		Connection conn = null;
		conn = util.ConnectionUtil.getConnection();
		String totalPrice = ""; 
		try {
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);
			String orderNumbers = "";
			List<String> orderTotalList = new ArrayList<String>();
			List<String> receiptBuilder = new ArrayList<String>();
			int count = 0;
			ResultSet rs = statement.executeQuery("SELECT * FROM orders");
			while(rs.next()) {
				orderNumbers += String.valueOf(rs.getInt("orderID")) + ", ";
				orderTotalList.add(rs.getString("price"));
			}
			totalPrice = calcBillTotal(orderTotalList);
			statement.executeUpdate("INSERT INTO bill (orderID, total) "
					+ "VALUES ('" + orderNumbers + "', '" + totalPrice + "')");
			statement.executeUpdate("DELETE FROM orders");
			statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name = 'orders'");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			util.ConnectionUtil.closeConnection(conn);
		}
		util.ReceiptPrint.printReceipt(receiptOrders, receiptQty, receiptPrice, totalPrice);
		receiptListView.getItems().clear();
	}
	
	private String calcBillTotal(List<String> orderTotalList) {
		String totalPrice = "";
		Double value = 0.0;
		String tempValue = "";
		for(int i = 0; i < orderTotalList.size(); i++) {
			tempValue = orderTotalList.get(i);
			tempValue = tempValue.replace("$", "");
			value += Double.parseDouble(tempValue);
		}
		value += value * .08625;
		totalPrice = "$" + String.format("%.2f", value);
		return totalPrice;
	}

	@FXML
	private void handleAdd(ActionEvent event) throws ParseException, IOException {
		Connection conn = null;
		switch(selectionSwitch){
		case "coffee": 
			conn = util.ConnectionUtil.getConnection();
			try {
				Statement statement = conn.createStatement();
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery("SELECT * FROM shopMenu WHERE itemName = '" + itemSelectionString + "'");
				Statement statement2 = conn.createStatement();
				ResultSet rs2 = statement2.executeQuery("SELECT * FROM shopMenu WHERE itemName = '" + itemSelectionString + "'");
				while(rs2.next()) {
					itemPriceString = calcPrice(rs2.getString("price"), itemQtyString);
				}		
				while(rs.next()) {
					statement.executeUpdate("INSERT INTO orders (itemTableName, itemID, quantity, price) " + "VALUES ('coffee', '"
							+ rs.getInt("itemID") + "', '" + itemQtyString + "', '" + itemPriceString + "')");
				}												
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("null");
			} finally {
				util.ConnectionUtil.closeConnection(conn);
			}
			updateReceipt();
			break;
		case "condiments":
			conn = util.ConnectionUtil.getConnection();
			try {
				Statement statement = conn.createStatement();
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery("SELECT * FROM shopMenu WHERE itemName = '" + itemSelectionString + "'");
				Statement statement2 = conn.createStatement();
				ResultSet rs2 = statement2.executeQuery("SELECT * FROM shopMenu WHERE itemName = '" + itemSelectionString + "'");
				while(rs2.next()) {
					itemPriceString = calcPrice(rs2.getString("price"), itemQtyString);
				}	
				while(rs.next()) {
					statement.executeUpdate("INSERT INTO orders (itemTableName, itemID, quantity, price) " + "VALUES ('condiments', '"
							+ rs.getInt("itemID") + "', '" + itemQtyString + "', '" + itemPriceString + "')");
				}									
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("null");
			} finally {
				util.ConnectionUtil.closeConnection(conn);
			}
			updateReceipt();
			break;
		case "food":
			conn = util.ConnectionUtil.getConnection();
			try {
				Statement statement = conn.createStatement();
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery("SELECT * FROM shopMenu WHERE itemName = '" + itemSelectionString + "'");
				Statement statement2 = conn.createStatement();
				ResultSet rs2 = statement2.executeQuery("SELECT * FROM shopMenu WHERE itemName = '" + itemSelectionString + "'");
				while(rs2.next()) {
					itemPriceString = calcPrice(rs2.getString("price"), itemQtyString);
				}	
				while(rs.next()) {
					statement.executeUpdate("INSERT INTO orders (itemTableName, itemID, quantity, price) " + "VALUES ('food', '"
							+ rs.getInt("itemID") + "', '" + itemQtyString + "', '" + itemPriceString + "')");
				}					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("null");
			} finally {
				util.ConnectionUtil.closeConnection(conn);
			}
			updateReceipt();
			break;
		default:
			System.out.println("failure");
		}
	}
	
	@FXML
	private void addCoffeeOrder(ActionEvent event) throws SQLException, ParseException, IOException {
		Object o = event.getSource();
		Button b = null;
		if(o instanceof Button) {
			b = (Button)o;
		}
		if(b != null) {
			if(b.getText().contentEquals("-add new-") ) {
				createNewCoffee(event);
			}
			itemSelectionString = b.getText();
		}
		itemPreview.setText(itemSelectionString);
		selectionSwitch = "coffee";
	}

	@FXML
	private void addCondimentOrder(ActionEvent event) throws SQLException, ParseException, IOException {
		Object o = event.getSource();
		Button b = null;
		if(o instanceof Button) {
			b = (Button)o;
		}
		if(b != null) {
			if(b.getText().contentEquals("-add new-") ) {
				createNewCondiments(event);
			}
			itemSelectionString = b.getText();			
		}
		itemPreview.setText(itemSelectionString);
		selectionSwitch = "condiments";
	}

	@FXML
	private void addFoodOrder(ActionEvent event) throws SQLException, ParseException, IOException {
		Object o = event.getSource();
		Button b = null;
		if(o instanceof Button) {
			b = (Button)o;
		}
		if(b != null) {
			if(b.getText().contentEquals("-add new-") ) {
				createNewFood(event);
			}
			itemSelectionString = b.getText();			
		}
		itemPreview.setText(itemSelectionString);
		selectionSwitch = "food";
	}
	
	@FXML
	private void createNewCoffee(ActionEvent event) throws SQLException, ParseException, IOException {
		selectionSwitch = "coffee";
		TextInputDialog nameInput = new TextInputDialog();
		nameInput.setTitle("New Coffee Item");
		nameInput.getDialogPane().setContentText("Item Name: ");
		Optional<String> result = nameInput.showAndWait();
		String name = result.toString();
		TextInputDialog priceInput = new TextInputDialog();
		priceInput.setTitle("New Coffee Item");
		priceInput.getDialogPane().setContentText("Item Price: ");
		Optional<String> result2 = priceInput.showAndWait();
		String price = result2.toString();
		insertNewItem(name, price, selectionSwitch);
		if(event.getSource() instanceof Button) {
			int indexOfName = name.indexOf("]");
			String shortName = name.substring(9, indexOfName);
			((Button) event.getSource()).setText(shortName);
		}
		addCoffeeOrder(event);
	}
	
	@FXML
	private void createNewCondiments(ActionEvent event) throws SQLException, ParseException, IOException {
		selectionSwitch = "condiments";
		TextInputDialog nameInput = new TextInputDialog();
		nameInput.setTitle("New Condiment Item");
		nameInput.getDialogPane().setContentText("Item Name: ");
		Optional<String> result = nameInput.showAndWait();
		String name = result.toString();
		TextInputDialog priceInput = new TextInputDialog();
		priceInput.setTitle("New Condiment Item");
		priceInput.getDialogPane().setContentText("Item Price: ");
		Optional<String> result2 = priceInput.showAndWait();
		String price = result2.toString();
		insertNewItem(name, price, selectionSwitch);
		if(event.getSource() instanceof Button) {
			int indexOfName = name.indexOf("]");
			String shortName = name.substring(9, indexOfName);
			((Button) event.getSource()).setText(shortName);
		}
		addCondimentOrder(event);
	}
	
	@FXML
	private void createNewFood(ActionEvent event) throws SQLException, ParseException, IOException {
		selectionSwitch = "food";
		TextInputDialog nameInput = new TextInputDialog();
		nameInput.setTitle("New Food Item");
		nameInput.getDialogPane().setContentText("Item Name: ");
		Optional<String> result = nameInput.showAndWait();
		String name = result.toString();
		TextInputDialog priceInput = new TextInputDialog();
		priceInput.setTitle("New Food Item");
		priceInput.getDialogPane().setContentText("Item Price: ");
		Optional<String> result2 = priceInput.showAndWait();
		String price = result2.toString();
		insertNewItem(name, price, selectionSwitch);
		if(event.getSource() instanceof Button) {
			int indexOfName = name.indexOf("]");
			String shortName = name.substring(9, indexOfName);
			((Button) event.getSource()).setText(shortName);
		}
		addFoodOrder(event);
	}
	
	
	private void insertNewItem(String name, String price, String selection) {
		Connection conn = null;
		conn = util.ConnectionUtil.getConnection();
		int indexOfName = name.indexOf("]");
		String shortName = name.substring(9, indexOfName);
		int indexOfPrice = price.indexOf("]");
		String shortPrice = price.substring(9, indexOfPrice);
		try {
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("INSERT INTO shopMenu (itemType, itemName, price) " + "VALUES ('" + selection
					+ "', '" + shortName + "', '" + shortPrice + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.ConnectionUtil.closeConnection(conn);
		}
	}

	private String calcPrice(String price, String itemQuantity) throws ParseException {
		String calcdPrice = "";
		price = price.replace("$", "");
		Double value = Double.parseDouble(price);
		value = value * Double.parseDouble(itemQuantity);
		calcdPrice = "$" + String.format("%.2f", value);
		return calcdPrice;
	}
	
	private void updateReceipt() throws IOException {
		boxList = new ArrayList<String>();
		String listViewAsset = "Item: " + itemSelectionString + ", Quantity: " + itemQtyString + ", Price: " + itemPriceString;
		boxList.add(listViewAsset);
		receiptListView.getItems().addAll(boxList);
		receiptOrders.add(itemSelectionString);
		receiptQty.add(itemQtyString);
		receiptPrice.add(itemPriceString);
	}
	
	@FXML
	private void handleBackup() {
		util.Backup.backupBills();
	}
	
	@FXML
	private void handleRestore() {
		util.Backup.restoreBills();
	}
	
	@FXML
	private void handleSignOut() throws IOException {
		URL url = new File("src/view/LoginPane.fxml").toURI().toURL();
		AnchorPane pane = FXMLLoader.load(url);
		landingPane.getChildren().setAll(pane);
	}

	
}
