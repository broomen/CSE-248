package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReceiptPrint {

	public static void printReceipt(List<String> receiptOrders, List<String> receiptQty, List<String> receiptPrice, String totalPrice) {
		String orderItem = "";
		String orderQty = "";
		String orderPrice = "";
		File sales = new File("sales/sales.txt");
		try {
			if(sales.exists() == false) {
				sales.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(sales, true));
			for(int i = 0; i < receiptOrders.size(); i++) {
				orderItem = receiptOrders.get(i);
				orderQty = receiptQty.get(i);
				orderPrice = receiptPrice.get(i);
				out.append("Order: " + orderItem + "\nOrder Quantity: " + orderQty + "\nOrder Price: " + orderPrice + "\n");
			}
			out.append("Total: " + totalPrice + "\n\n---------------BREWME COFFEE---------------\n\n");
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
		

}
