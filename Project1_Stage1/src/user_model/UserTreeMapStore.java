package user_model;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import trail_model.Trail;
import util.Evaluator;

public class UserTreeMapStore {
private static TreeMap<String, User> theMap;
	
	public UserTreeMapStore() {
		theMap = new TreeMap<String, User>();
	}
	
	public int size() {
		return theMap.size();
	}
	
	public void add(User user) {
		theMap.put(user.getUsername(), user);
	}
	
	public void addAll(TreeMap<String, User> importMap) {
		theMap.putAll(importMap);
	}
	
	public User remove(User user) {
		return theMap.remove(user.getUsername());
	}
	
	public Map findByName(String name) {
		return theMap.values().stream().filter(s -> s.compareTo(new User(name)) == 0).collect(Collectors.toMap(User::getUsername, User::getPassword));
	}
	
	public Map find(Predicate<User> predicate) {
		Map newMap = theMap.values().stream().filter(t-> predicate.test(t)).collect(Collectors.toMap(User::getUsername, User::getPassword));
		return newMap;
	}
	
	public void display() {
		theMap.values().stream().forEach(System.out::println);;
	}


	
	
}