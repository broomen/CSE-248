package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage_model.Storage;
import trail_model.Trail;
import user_model.User;

/**
 * 
 *  This project is a Java based application using scenebuilder
 *  to create an application that stores users and trails into
 *  data structures to allow a user to create an account and
 *  log their hiking history into the app. A variable,
 *  mainStorage, is maintained in this class for the
 *  application to refer back to, and the mainStorage holds
 *  the trail and user information.
 * 
 * 
 * @author Nick Broome
 * 
 * @version v1.0, November 18,2020
 *
 */

public class App extends Application {
	private static Storage mainStorage;
	
	/**
	 * The main method launches the application.
	 * 
	 * @param args The String array argument is not used.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * 
	 * Starts the application with an initial user, an admin account
	 * with the login name "admin" and password "password". This
	 * user has full access in the app. The method displays a 720p
	 * resolution login page.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStorage = new Storage();
		User adminUser = new User("admin", "password");
		adminUser.setAdmin(true);
		mainStorage.getUserStorage().add(adminUser);
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
		Scene scene = new Scene(root, 1280, 720);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Broome Trails");
		primaryStage.show();
	}
	
	/**
	 * 
	 * @return This method functions to return the mainStorage variable held in this
	 * class for use throughout the application.
	 */
	public static Storage getStorage() {
		return mainStorage;
	}

}
