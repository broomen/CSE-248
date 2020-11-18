package view;

import java.io.IOException;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import user_model.User;
/**
 * 
 * This is the controller for the register pane. This pane
 * allows the user to enter a desired username and password into the
 * corresponding fields and then creates a new user and inserts it
 * into the storage.
 * 
 * @author Nick Broome
 *
 */
public class RegisterController {
	
	@FXML private AnchorPane registerPane;
	@FXML private TextField usernameField;
	@FXML private PasswordField passField;
	
	/**
	 * Upon clicking the register button, the app will take the information in the
	 * usernameField and passwordField to create a new user and then insert it into
	 * the storage. Once done, it will re-direct to the login pane.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void handleConfirm(ActionEvent event) throws IOException {
		String tempUsername = usernameField.getText();
		String tempPassword = passField.getText();
		User newUser = new User(tempUsername, tempPassword);
		App.getStorage().getUserStorage().add(newUser);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginPane.FXML"));
		registerPane.getChildren().setAll(pane);		
	}

}
