package app;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ConnectionUtil;
	

public class App extends Application{
	private static Connection conn;
	
	
	//default username and password for sign is admin and password.
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		conn = null;
		conn = ConnectionUtil.getConnection();
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
		Scene scene = new Scene(root, 1024, 768);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Brewme Coffee");
		primaryStage.show();
	}

	public static Connection getConn() {
		return conn;
	}	

}
