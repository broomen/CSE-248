package trail_model;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import util.Evaluator;


public class TrailTreeMapStore {
	private static TreeMap<String, Trail> theMap;
	
	public TrailTreeMapStore() {
		theMap = new TreeMap<String, Trail>();
	}
	
	public void add(Trail trail) {
		theMap.put(trail.getId(), trail);
	}
	
	public void addAll(TreeMap<String, Trail> importMap) {
		theMap.putAll(importMap);
	}
	
	public Trail remove(Trail trail) {
		return theMap.remove(trail.getId());
	}
	
	public Map findByName(String name) {
		return theMap.values().stream().filter(s -> s.compareTo(new Trail(name)) == 0).collect(Collectors.toMap(Trail::getId, Trail::getName));
	}
	
	public Map find(Predicate<Trail> predicate) {
		long start = System.currentTimeMillis();
		Map newMap = theMap.values().stream().filter(t-> predicate.test(t)).collect(Collectors.toMap(Trail::getId, Trail::getName));
		long end = System.currentTimeMillis();
		long timeTook = end - start;
		Evaluator.addTrailResult((int)timeTook);
		try {
			Evaluator.evalFind("trailTreeMap");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newMap;
	}
	
	public void display() {
		theMap.values().stream().forEach(System.out::println);;
	}


	
	
}