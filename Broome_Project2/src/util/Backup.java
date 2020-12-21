package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Backup {
	
	public static void backupBills(){
		Connection conn = null;
		conn = ConnectionUtil.getConnection();
		Statement statement;
		String[] billInfo = new String[2];
		List<String[]> allBills = new ArrayList<String[]>();
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bill");
			while(rs.next()) {
				billInfo[0] = rs.getString("orderID");
				billInfo[1] = rs.getString("total");
				allBills.add(billInfo);				
			}
			statement = conn.createStatement();
			FileOutputStream fos = new FileOutputStream("backup/backup.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allBills);
			oos.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn);
		}
		
	}
	
	public static void restoreBills() {
		Connection conn = null;
		conn = ConnectionUtil.getConnection();
		Statement statement;
		String[] billInfo = new String[2];
		String orderIDs = "";
		String orderPrices = "";
		try {
			FileInputStream fis = new FileInputStream("backup/backup.dat");
			ObjectInputStream ois  = new ObjectInputStream(fis);
			ArrayList<String[]> backedUpList = (ArrayList<String[]>) ois.readObject();
			ois.close();
			statement = conn.createStatement();
			for(int i = 0; i < backedUpList.size(); i++) {
				billInfo = backedUpList.get(i);
				System.out.println(billInfo.length);
				for(int j = 0; j < billInfo.length-1; j++) {
					orderIDs = billInfo[j];
					orderPrices = billInfo[j+1];
				}
				statement.executeUpdate("INSERT INTO bill (orderID, total) "
						+ "VALUES ('" + orderIDs + "', '" + orderPrices + "')");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
