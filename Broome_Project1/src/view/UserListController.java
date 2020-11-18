package view;

import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import user_model.User;
import utility.Backup;
/**
 * This controller is for the UserListPane. This pane is for
 * admins exlcusively, and allows them to view all users on the app,
 * as well as their information. This includes their username, 
 * password, and HikingHistory. They have the ability to update/edit
 * users here if needed, as well as remove them outright if their accounts
 * should have a need to be deleted. The elements that can be updated
 * are the username, password, and if they are/are not an admin. 
 * 
 * @author Nick Broome
 *
 */
public class UserListController implements Initializable, MenuButtons {
	private User loggedInUser = LoginController.getLoggedUser();
	private User selectedUser;
	private List<History> historyList;
	private List<User> userList;
	
	@FXML private ListView userListView;
	@FXML private ListView userHistoryListView;
	
	@FXML private AnchorPane userListPane;
	
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private TextField isAdminField;
	@FXML private Button trailBtn;
	@FXML private Button restoreBtn;
	@FXML private Button backupBtn;
	@FXML private Button userListBtn;
	
	/**
	 * This method initializes the userListPane, and loads a ListView
	 * with all users from the storage. It also determines the visiblity
	 * of some buttons that admin only.
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
		userList = new ArrayList<User>(App.getStorage().getUserStorage().size());
		for(User x : App.getStorage().getUserStorage().getUserStore().values()) {
			userList.add(x);
		}
		userListView.getItems().addAll(userList);
	}
	
	/**
	 * Upon pressing the update button, this method will pull the entered
	 * information from the corresponding fields and use it to change/update
	 * whatever is desired about the user that was selected in the ListView.
	 * The pane is then refreshed to reflect this new information.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void updateProfile(ActionEvent event) throws IOException {
		String tempUsername = usernameField.getText();
		String tempPassword = passwordField.getText();
		Boolean isAdmin = Boolean.parseBoolean(isAdminField.getText());
		HistoryStore tempHistory = selectedUser.getHikingHistory();
		User tempUser = new User(tempUsername, tempPassword, tempHistory, isAdmin);
		App.getStorage().getUserStorage().remove(selectedUser);
		App.getStorage().getUserStorage().add(tempUser);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UserListPane.fxml"));
		userListPane.getChildren().setAll(pane);
	}
	
	/**
	 * Upon pressing the delete button, this method will search the app
	 * for the selected user from the ListView, and upon finding them,
	 * will delete that user from the app storage. 
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void deleteProfile(ActionEvent event) throws IOException {
		App.getStorage().getUserStorage().remove(selectedUser);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UserListPane.fxml"));
		userListPane.getChildren().setAll(pane);
	}
	
	/**
	 * This method will load the users info into the corresponding
	 * text fields upon the user's name being clicked in the ListView.
	 * It will also load the user that is selected into an additional
	 * variable, selectedUser, which is used in the various methods in this pane.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void loadUserInfo(MouseEvent event) throws IOException {
		userHistoryListView.getItems().clear();
		User tempUser = (User) userListView.getSelectionModel().getSelectedItem();
		selectedUser = tempUser;
		if(tempUser == null) {
			System.out.println("null");
		} else {
			usernameField.setText(tempUser.getUsername());
			passwordField.setText(tempUser.getPassword());
			if(tempUser.isAdmin() == true) {
				isAdminField.setText("true");
			} else {
			isAdminField.setText("false");
			}
			historyList = new ArrayList<History>(tempUser.getHikingHistory().size());
			for(History x : tempUser.getHikingHistory().getHistoryStore()) {
				historyList.add(x);
			}
			userHistoryListView.getItems().addAll(historyList);			
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
		userListPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleProfileBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePane.fxml"));
		userListPane.getChildren().setAll(pane);
	}

	@FXML
	public void handleUserListBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UserListPane.fxml"));
		userListPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
		loggedInUser = null;
		userListPane.getChildren().setAll(pane);
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
