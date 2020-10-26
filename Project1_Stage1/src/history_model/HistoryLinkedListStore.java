package history_model;

import java.io.IOException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import util.Evaluator;

public class HistoryLinkedListStore {
	private LinkedList<History> theList;
	private Iterator listIter;

	public HistoryLinkedListStore() {
		theList = new LinkedList<>();
		listIter = theList.listIterator(0);
	}
	
	public int size() {
		return theList.size();
	}

	public void add(History history) {
		theList.add(history);
	}

	public void addAll(LinkedList<History> historyCollection) {
		theList.addAll(historyCollection);
	}

	public boolean remove(History history) {
		return theList.remove(history);
	}

	public History findByName(String name) {
		return theList.stream().filter(history -> name.equals(history.getTrailName())).findAny().orElse(null);
	}

	public List<History> find(Predicate<History> predicate) {
		List<History> newList = theList.stream().filter(predicate).collect(Collectors.toList());
		return newList;
	}

	public void display() {
		theList.stream().forEach(System.out::println);
	}

}
