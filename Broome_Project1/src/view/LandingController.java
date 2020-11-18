package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import user_model.User;
import utility.Backup;
/**
 * The controller for the LandingPane. The landingPane
 * is where the user first arrives after logging in to the App,
 * and apart from a greeting, only offers buttons to navigate to the
 * othe areas of the app.
 * 
 * @author Brick
 *
 */
public class LandingController implements Initializable, MenuButtons {
	
	private User loggedInUser = LoginController.getLoggedUser();
	
	@FXML private AnchorPane landingPane;
	
	@FXML private Label welcomeLabel;
	@FXML private Button trailBtn;
	@FXML private Button restoreBtn;
	@FXML private Button backupBtn;
	@FXML private Button userListBtn;

	/**
	 * This method initializes the pane, setting the welcomeLabel to include the
	 * logged in user's username. If the user is an admin, then they will see the
	 * extra buttons only shown to an admin, otherwise they will not.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		welcomeLabel.setText("Welcome, " + loggedInUser.getUsername());
		if(loggedInUser.isAdmin() == true) {
			backupBtn.setVisible(true);
			restoreBtn.setVisible(true);
			userListBtn.setVisible(true);
		} else {
			backupBtn.setVisible(false);
			restoreBtn.setVisible(false);
			userListBtn.setVisible(false);
		}
	}
	
	/**
	 * This method and those below it all function nearly the same, with handling
	 * the event of a user clicking on one of the buttons in the menu AnchorPane.
	 * Depending on the button pressed, various actions will be accomplished in the
	 * app. handleUserListBtn, handleBackup, and handleRestore are exclusive
	 * to the admins.
	 */
	@FXML
	public void handleTrailBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("TrailListPane.fxml"));
		landingPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleProfileBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePane.fxml"));
		landingPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleUserListBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UserListPane.fxml"));
		landingPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
		loggedInUser = null;
		landingPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleBackup(ActionEvent event) {
		Backup.backup(App.getStorage());
	}
	
	@FXML
	public void handleRestore(ActionEvent event) {
		Backup.restore(App.getStorage());
	}
	

}
