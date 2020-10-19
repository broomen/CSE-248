package user_model;

import java.util.LinkedList;
import java.util.Random;

import history_model.History;
import history_model.HistoryLinkedListStore;

public class User implements Comparable<User> {
	private String username;
	private String password;
	private HistoryLinkedListStore hikingHistory;

	public User(String username) {
		this.username = username;
		this.password = generateRandomPassword();
		this.hikingHistory = new HistoryLinkedListStore();
	}

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

	public HistoryLinkedListStore getHikingHistory() {
		return hikingHistory;
	}

	public void setHikingHistory(HistoryLinkedListStore hikingHistory) {
		this.hikingHistory = hikingHistory;
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
