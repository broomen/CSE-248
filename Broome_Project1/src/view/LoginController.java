package view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import user_model.User;
/**
 * This is the controller for the Login Pane. The login pane is the first
 * thing the user sees when starting the app, and has the option for the user
 * to either login or go to the register pane and register a new account. This controller
 * also stores a user that is referenced by the other panes that is whoever the current
 * logged in user is, so as to quickly reference their information
 * when needed. 
 * 
 * @author Nick Broome
 *
 */
public class LoginController {
	
	private static User tempUser;
	
	@FXML private AnchorPane loginPane;
	@FXML private TextField usernameField;
	@FXML private PasswordField passField;
	
	/**
	 * This method and the one below it function similarly, which is they respond to the
	 * user clicking on a button. This method, apart from just navigating to a different pane, 
	 * also checks the information put in the username and password fields to see if that
	 * user exists in the Storage userStorage, and if it does then it will log the user in
	 * otherwise it will not. 
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void handleLogin(ActionEvent event) throws IOException {
		String tempUsername = usernameField.getText();
		String tempPassword = passField.getText();
		User loginUser = null;
		try {
			loginUser = App.getStorage().getUserStorage().getUserStore().get(tempUsername);
		} catch(NullPointerException e) {
		}
		if(loginUser.getPassword().contentEquals(tempPassword)) {
			tempUser = loginUser;
			AnchorPane pane = FXMLLoader.load(getClass().getResource("LandingPane.FXML"));
			loginPane.getChildren().setAll(pane);
		}		
	}
	
	@FXML
	private void handleRegister(ActionEvent event) throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("RegisterPane.FXML"));
		loginPane.getChildren().setAll(pane);
	}

	public static User getLoggedUser() {
		return tempUser;
	}

	public void setLoggedUser(User tempUser) {
		this.tempUser = tempUser;
	}
	
	

}
