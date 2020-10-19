package trail_model;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import util.Evaluator;

public class TrailTreeSetStore {
	private static TreeSet<Trail> theSet;

	public TrailTreeSetStore() {
		theSet = new TreeSet<>();
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
		long start = System.currentTimeMillis();
		Set newSet = theSet.stream().filter(t-> predicate.test(t)).collect(Collectors.toSet());
		long end = System.currentTimeMillis();
		long timeTook = end - start;
		Evaluator.addTrailResult((int)timeTook);
		try {
			Evaluator.evalFind("trailTreeSet");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newSet;
	}

	public void display() {
//		theSet.stream().forEach(t -> System.out.println(t));
		theSet.stream().forEach(System.out::println);
	}
}