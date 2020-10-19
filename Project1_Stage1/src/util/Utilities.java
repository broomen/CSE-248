package util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import history_model.History;
import trail_model.Trail;
import user_model.User;

public class Utilities {

	public static Collection emitTrails(int number) {
		int trailCounter = 0;
		Collection<Trail> list = new LinkedList<>();
		for (int i = 0; i < number; i++) {
			list.add(new Trail("T" + trailCounter++));
		}
		return list;
	}
	
	public static TreeMap emitTrailsForMap(int number) {
		int trailCounter = 0;
		TreeMap<String, Trail> map = new TreeMap<String, Trail>();
		for(int i = 0; i < number; i++) {
			Trail tempTrail = new Trail("T" + trailCounter++);
			map.put(tempTrail.getId(), tempTrail);
		}
		return map;
	}
	
	public static Collection emitUsersForSet(int number) {
		int userCounter = 0;
		Collection<User> list = new LinkedList<>();
		for(int  i = 0; i < number; i++) {
			list.add(new User("USER" + userCounter++));
		}
		return list;
	}
	
	public static TreeMap emitUsersForMap(int number) {
		int userCounter = 0;
		TreeMap<String, User> map = new TreeMap<String, User>();
		for(int i = 0; i < number; i++) {
			User tempUser = new User("USER" + userCounter++);
			map.put(tempUser.getUsername(), tempUser);
		}
		return map;
	}
	
	public static LinkedList<History> emitHistory(int number){
		int historyCounter = 0;
		LinkedList<History> list = new LinkedList<History>();
		for(int i = 0; i < number; i++) {
			History tempHistory = new History("HIS" + historyCounter++);
			list.add(tempHistory);
		}
		return list;
	}
	
}
