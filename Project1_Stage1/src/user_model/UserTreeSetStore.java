package user_model;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import trail_model.Trail;
import util.Evaluator;

public class UserTreeSetStore {
	private static TreeSet<User> theSet;

	public UserTreeSetStore() {
		theSet = new TreeSet<>();
	}

	public void add(User user) {
		theSet.add(user);
	}

	public void addAll(Collection userCollection) {
		theSet.addAll(userCollection);
	}

	public boolean remove(User user) {
		return theSet.remove(user);
	}

	public Set findByName(String name) {
		return theSet.stream().filter(s -> s.compareTo(new User(name)) == 0).collect(Collectors.toSet());
	}

	public Set find(Predicate<User> predicate) {
		long start = System.currentTimeMillis();
		Set newSet = theSet.stream().filter(t-> predicate.test(t)).collect(Collectors.toSet());
		long end = System.currentTimeMillis();
		long timeTook = end - start;
		Evaluator.addUserResult((int)timeTook);
		try {
			Evaluator.evalFind("userTreeSet");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newSet;
	}

	public void display() {
		theSet.stream().forEach(System.out::println);
	}
}