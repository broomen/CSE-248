package trail_model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * This class is the storage class for the Trail objects, and stores them
 * into a TreeSet. The class contains common methods such as getters and setters,
 * as well as others such as the ability to findByName and return a set containing
 * multiple Trails with the same or similar names. 
 * 
 * @author Nick Broome
 *
 */
public class TrailStore implements Serializable{
	private static TreeSet<Trail> theSet;

	public TrailStore() {
		theSet = new TreeSet<>();
	}
	
	public static TreeSet<Trail> getTrailStore() {
		return theSet;
	}

	public static void setTrailStore(TreeSet<Trail> theSet) {
		TrailStore.theSet = theSet;
	}

	public int size() {
		return theSet.size();
	}

	public void add(Trail trail) {
		theSet.add(trail);
	}

	public void addAll(Collection trailCollection) {
		theSet.addAll(trailCollection);
	}

	public boolean remove(Trail trail) {
		return theSet.remove(trail);
	}

	public Set findByName(String name) {
		return theSet.stream().filter(s -> s.compareTo(new Trail(name)) == 0).collect(Collectors.toSet());
	}

	public Set find(Predicate<Trail> predicate) {
		Set newSet = theSet.stream().filter(t-> predicate.test(t)).collect(Collectors.toSet());
		return newSet;
	}

	public void display() {
//		theSet.stream().forEach(t -> System.out.println(t));
		theSet.stream().forEach(System.out::println);
	}
}