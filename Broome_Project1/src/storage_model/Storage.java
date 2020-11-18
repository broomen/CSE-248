package storage_model;

import trail_model.TrailStore;
import user_model.UserStore;
/**
 * 
 * This class is the main storage container for the application,
 * holding inside both trailStorage and userStorage. I made this
 * purely for organizational purpose and ease of access when using
 * backup and restore. This class contains common methods such as
 * getters and setters. 
 * 
 * @author Nick Broome
 *
 */
public class Storage {
	private TrailStore trailStorage;
	private UserStore userStorage;
	
	public Storage(TrailStore trailStorage, UserStore userStorage) {
		this.trailStorage = trailStorage;
		this.userStorage = userStorage;
	}
	
	public Storage() {
		this.trailStorage = new TrailStore();
		this.userStorage = new UserStore();
	}

	public TrailStore getTrailStorage() {
		return trailStorage;
	}

	public void setTrailStorage(TrailStore trailStorage) {
		this.trailStorage = trailStorage;
	}

	public UserStore getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(UserStore userStorage) {
		this.userStorage = userStorage;
	}
	

}
