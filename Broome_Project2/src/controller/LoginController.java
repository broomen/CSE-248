package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.ConnectionUtil;

public class LoginController {

	@FXML private AnchorPane loginPane;
	@FXML private TextField usernameField;
	@FXML private PasswordField passField;

	@FXML
	private void handleLogin(ActionEvent event) throws IOException {
		Connection conn = null;
		conn = util.ConnectionUtil.getConnection();
		String tempUsername = usernameField.getText();
		String tempPassword = passField.getText();
		try {
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery("SELECT * FROM employee WHERE username = '" + tempUsername + "'");
			if (rs.getString("password").contentEquals(tempPassword)) {
				URL url = new File("src/view/LandingPane.fxml").toURI().toURL();
				AnchorPane pane = FXMLLoader.load(url);
				loginPane.getChildren().setAll(pane);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.ConnectionUtil.closeConnection(conn);
		}
	}
}
