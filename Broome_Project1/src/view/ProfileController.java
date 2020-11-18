package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.App;
import history_model.History;
import history_model.HistoryStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import user_model.User;
import utility.Backup;
/**
 * 
 * The controller for the profile pane. This pane displays the information
 * pertinent to the current logged in user, such as their hiking history
 * and profile picture. The class fetches the current logged in user
 * from the LoginController. The user can also update their username
 * and password on this pane. 
 * 
 * @author Nick Broome
 *
 */
public class ProfileController implements Initializable, MenuButtons {

	private User loggedInUser = LoginController.getLoggedUser();
	private List<History> historyList; 

	
	@FXML private AnchorPane profilePane;
	@FXML private ImageView pictureView;
	@FXML private Button trailBtn;
	@FXML private Button restoreBtn;
	@FXML private Button backupBtn;
	@FXML private Button userListBtn;
	@FXML private TextField usernameField;
	@FXML private TextField passField;
	@FXML private ListView historyListView;

	/**
	 * This method initializes the profile pane. It takes the current logged in user's hiking history
	 * and displays it in a listview. If the user is an admin, then they will see the
	 * extra buttons only shown to an admin, otherwise they will not.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(loggedInUser.isAdmin() == true) {
			backupBtn.setVisible(true);
			restoreBtn.setVisible(true);
			userListBtn.setVisible(true);
		} else {
			backupBtn.setVisible(false);
			restoreBtn.setVisible(false);
			userListBtn.setVisible(false);
		}
		historyList = new ArrayList<History>(loggedInUser.getHikingHistory().size());
		for(History x : loggedInUser.getHikingHistory().getHistoryStore()) {
			historyList.add(x);
		}
		historyListView.getItems().addAll(historyList);		
	}

	/**
	 * Upon clicking the button, whatever is entered in the username and password
	 * field will be taken and used to replace/update the loggedInUser's information.
	 * It then refreshes the page.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void updateProfile(ActionEvent event) throws IOException {
		System.out.println("test");
		String tempUsername = usernameField.getText();
		String tempPassword = passField.getText();
		HistoryStore tempHistory = loggedInUser.getHikingHistory();
		Boolean isAdmin = loggedInUser.isAdmin();
		User tempUser = new User(tempUsername, tempPassword, tempHistory, isAdmin);
		App.getStorage().getUserStorage().remove(loggedInUser);
		loggedInUser = tempUser;
		App.getStorage().getUserStorage().add(tempUser);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePane.fxml"));
		profilePane.getChildren().setAll(pane);
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
		AnchorPane pane = FXMLLoader.load(getClass().getResource("TrailListPane.FXML"));
		profilePane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleProfileBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePane.FXML"));
		profilePane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleUserListBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UserListPane.fxml"));
		profilePane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
		loggedInUser = null;
		profilePane.getChildren().setAll(pane);
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