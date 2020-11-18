package view;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;

import app.App;
import history_model.History;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import trail_model.Difficulty;
import trail_model.Trail;
import trail_model.TrailType;
import user_model.User;
import utility.Backup;
/**
 * 
 * This is the controller for the Trail Listing pane. This pane displays
 * all the trails in the storage, as well as whichever one is clicked on's
 * information in the corresponding text fields. Admins can create
 * new trails here by entering the required info in the corresponding text fields.
 * Users and admins can "go on a hike" by selecting a trail from the ListView 
 * and then entering the required information, such as time started and ended, 
 * as well as the date and distance into the TextFields and then click the
 * required button. This will add a new history to the user's HikingHistory.
 * 
 * @author Nick Broome
 *
 */
public class TrailListController implements Initializable, MenuButtons{
	
	private User loggedInUser = LoginController.getLoggedUser();
	private List<Trail> boxList;
	
	@FXML private ListView adminListBox;
	@FXML private AnchorPane trailListPane;
	@FXML private AnchorPane adminMenu;
	@FXML private AnchorPane hikeBox;
	
	@FXML private TextField trailNameField;
	@FXML private TextField trailHead1Field;
	@FXML private TextField trailHead2Field;
	@FXML private TextField trailHead3Field;
	@FXML private TextField lengthField;
	@FXML private TextField elevationField;
	@FXML private TextField diffField;
	@FXML private TextField typeField;
	@FXML private TextField stopsField;
	@FXML private TextField interField;
	@FXML private TextField idField;
	@FXML private TextField startedField;
	@FXML private TextField endedField;
	@FXML private TextField dateField;
	@FXML private TextField distanceField;
	@FXML private Button createTrailBtn;
	
	@FXML private Button trailBtn;
	@FXML private Button restoreBtn;
	@FXML private Button backupBtn;
	@FXML private Button userListBtn;
	
	/**
	 * This method initializes the trailListPane. It takes all the trails from
	 * the Storage and loads them into a ListView where the user can click and interact
	 * with them. Additionally, this method will set the visibility of admin only buttons
	 * to true or false depending on if the user is an admin.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(loggedInUser.isAdmin() == true) {
			backupBtn.setVisible(true);
			restoreBtn.setVisible(true);
			userListBtn.setVisible(true);
			createTrailBtn.setVisible(true);
		} else {
			backupBtn.setVisible(false);
			restoreBtn.setVisible(false);
			userListBtn.setVisible(false);
			createTrailBtn.setVisible(false);
		}
		boxList = new ArrayList<Trail>(App.getStorage().getTrailStorage().size());
		for(Trail x : App.getStorage().getTrailStorage().getTrailStore()) {
			boxList.add(x);
		}
		adminListBox.getItems().addAll(boxList);
	}
	
	/**
	 * This method will load all corresponding info for a trail into the
	 * text fields below upon that trail being clicked in the ListView. 
	 * @param event
	 */
	public void displayEntered(MouseEvent event) {
		Trail tempTrail = (Trail) adminListBox.getSelectionModel().getSelectedItem();
		if(tempTrail == null) {
			System.out.println("null");
		} else {
			String tempAddressHeadArray[] = new String[3];
			tempAddressHeadArray = tempTrail.getHeadAddress();
			trailNameField.setText(tempTrail.getName());
			trailHead1Field.setText(tempAddressHeadArray[0]);
			trailHead2Field.setText(tempAddressHeadArray[1]);
			trailHead3Field.setText(tempAddressHeadArray[2]);
			lengthField.setText(String.valueOf(tempTrail.getLength()));
			elevationField.setText(String.valueOf(tempTrail.getElevation()));
			diffField.setText(String.valueOf(tempTrail.getDiff()));
			typeField.setText(String.valueOf(tempTrail.getType()));
			idField.setText(tempTrail.getId());
		}
	}
	
	/**
	 * This method is for admin's only, and allows them to enter desired
	 * information into the various text fields, and then create a trail
	 * using that information, which will then be inserted into the trail
	 * storage. After completion, the pane is refreshed to show a new ListView.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void createTrail(ActionEvent event) throws IOException{
		String tempName = trailNameField.getText();
		String tempHead1 = trailHead1Field.getText();
		String tempHead2 = trailHead2Field.getText();
		String tempHead3 = trailHead3Field.getText();
		String[] tempAddress = new String[] {tempHead1, tempHead2, tempHead3};
		String strLength = lengthField.getText();
		Long tempLength = Long.parseLong(strLength);
		String strElev = elevationField.getText();
		Long tempElev = Long.parseLong(strElev);
		String diff = diffField.getText();
		Difficulty tempDiff = getDiffValue(diff);
		String type = typeField.getText();
		TrailType tempType = getTypeValue(type);
		Trail tempTrail = new Trail(tempName, tempAddress, tempLength, tempElev, tempDiff, tempType);
		App.getStorage().getTrailStorage().add(tempTrail);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("TrailListPane.FXML"));
		trailListPane.getChildren().setAll(pane);
	}
	
	/**
	 * This method takes a string entered into a text field by the user and 
	 * converts it into an enum of TrailType for the purpose of creating a trail
	 * through using switch case.
	 * 
	 * @param type
	 * @return an enum of TrailType for the creation of a trail.
	 */
	private TrailType getTypeValue(String type) {
		type = type.toLowerCase();
		switch(type) {
		case "loop":
			return TrailType.LOOP;
		case "out_and_back":
			return TrailType.OUT_AND_BACK;
		case "point_to_point":
			return TrailType.POINT_TO_POINT;
		}
		return null;
	}

	/**
	 * This method takes a string entered into a text field by the user
	 * and converts it into an enum of Difficulty for the purpose of creating
	 * a trail through using switch case.
	 * 
	 * @param diff
	 * @return an enum of Difficulty for the creation of a trail.
	 */
	private Difficulty getDiffValue(String diff) {
		diff = diff.toLowerCase();
		switch(diff) {
		case "easy":
			return Difficulty.EASY;
		case "moderate":
			return Difficulty.MODERATE;
		case "hard":
			return Difficulty.HARD;
		}
		return null;
	}

	/**
	 * This method, when the Go on a Hike button is pressed,
	 * will take the trail's ID number from the ID field and then search
	 * the storage to find that trail using the .find method from TrailStore.
	 * It then pulls needed information, such as the trail's name, from this 
	 * to ensure it has acquired the appropriate trail name. It then
	 * gets information from the started, ended, date and distance fields
	 * to create a History object, which is then inserted into that user's
	 * HistoryStore.
	 * 
	 * @param event
	 * @throws ParseException
	 */
	@FXML
	public void goHike(ActionEvent event) throws ParseException {
		String tempID = idField.getText();
		Predicate<Trail> pTrail = e -> e.getId().contentEquals(tempID);
		Set<Trail> tempSet = App.getStorage().getTrailStorage().find(pTrail);
		List<Trail> trailList = new LinkedList<Trail>();
		for(Trail x : tempSet) {
			trailList.add(x);
		}
		String tempStarted = startedField.getText();
		String tempEnded = endedField.getText();
		double tempTimeTook = calcTimeTook(tempStarted, tempEnded);
		String tempDateStr = dateField.getText();
		String tempDistance = distanceField.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date tempDate = dateFormat.parse(tempDateStr);
		History tempHistory = new History(trailList.get(0).getName(), tempTimeTook, tempDate, tempDistance);
		loggedInUser.getHikingHistory().add(tempHistory);
		loggedInUser.getHikingHistory().display();
		}

	/**
	 *  This method is used in the goHike method to calculate, from strings, the time it took
	 *  for the hike to be done in minutes. 
	 * 
	 * @param tempStarted the time the hike started
	 * @param tempEnded the time the hike ended
	 * @return a double that is the value of the two times subtracted, in minutes.
	 * @throws ParseException
	 */
	private double calcTimeTook(String tempStarted, String tempEnded) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(tempStarted);
		Date date2 = format.parse(tempEnded);
		double timeTook = date2.getTime() - date1.getTime();
		double timeTookMin = (timeTook / (1000 * 60)) % 60;
		double timeTookHr = (timeTook / (1000 * 60 * 60)) % 24; 
		double timeTookHrToMin = timeTookHr * 60;
		double calcTimeTook = timeTookMin + timeTookHrToMin;
		return calcTimeTook;
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
		trailListPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleProfileBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePane.fxml"));
		trailListPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleUserListBtn(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UserListPane.fxml"));
		trailListPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
		loggedInUser = null;
		trailListPane.getChildren().setAll(pane);
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
