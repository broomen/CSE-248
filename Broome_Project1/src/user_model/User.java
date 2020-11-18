package user_model;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.Random;

import history_model.History;
import history_model.HistoryStore;

/**
 * This class stores all the information needed for the users.
 * It contains their username, password, hikingHistory, profilePicture,
 * and if they are or aren't an admin. Every account is given the default
 * profile picture upon inception. The boolean isAdmin determines
 * whether or not that user will have full access to the app, as a 
 * regular user is limited in what they can do compare to an admin.
 * This class contains primarily common methods such as getters and setters.
 * 
 * @author Nick Broome
 *
 */

public class User implements Comparable<User>, Serializable{
	private String username;
	private String password;
	private HistoryStore hikingHistory;
	private String profilePicture;
	private boolean isAdmin;

	public User(String username) {
		this.username = username;
		this.password = generateRandomPassword();
		this.hikingHistory = new HistoryStore();
		this.isAdmin = false;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.hikingHistory = new HistoryStore();
		this.profilePicture = "Broome_Project1/photos/default.png";
		this.isAdmin = false;
	}
	
	public User(String username, String password, HistoryStore history, Boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.hikingHistory = history;
		this.isAdmin = isAdmin;
		this.profilePicture = "Broome_Project1/photos/default.png";
	}
/**
 * This class is mostly used for testing when creating multiple accounts. It simply
 * generates a random password as a String and returns it to the constructor that doesn't
 * contain a password as a parameter. 
 * 
 * @return a generatedString containing random letters at a random length.
 */
	private String generateRandomPassword() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HistoryStore getHikingHistory() {
		return hikingHistory;
	}

	public void setHikingHistory(HistoryStore hikingHistory) {
		this.hikingHistory = hikingHistory;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

	@Override
	public int compareTo(User o) {
		if (password.compareTo(o.password) == 0) {
			return 0;
		} else if (password.compareTo(o.password) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
