package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 * Just a simple interface for all of the controllers with the menu bar to have to 
 * ensure they all have the same handle button methods. 
 * 
 * @author Nick Broome
 *
 */
public interface MenuButtons {

	@FXML void handleTrailBtn(ActionEvent event) throws IOException;
	
	@FXML void handleProfileBtn(ActionEvent event) throws IOException;
	
	@FXML void handleUserListBtn(ActionEvent event) throws IOException;
	
	@FXML void handleLogOut(ActionEvent event) throws IOException;
	
}
