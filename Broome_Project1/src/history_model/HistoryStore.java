package history_model;

import java.io.IOException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * 
 * This class serves as the storage class for all of the user's Hiking History.
 * It takes each history object created through the user going on a hike and
 * collects it into a linked list. The class has very general methods of find,
 * remove, size, etc. 
 * 
 * @author Nick Broome
 *
 */
public class HistoryStore {
	private LinkedList<History> theList;
	private Iterator listIter;

	public HistoryStore() {
		theList = new LinkedList<>();
		listIter = theList.listIterator(0);
	}
	
	public LinkedList<History> getHistoryStore() {
		return theList;
	}

	public void setHistoryStore(LinkedList<History> theList) {
		this.theList = theList;
	}

	public Iterator getIter() {
		return listIter;
	}

	public void setIter(Iterator listIter) {
		this.listIter = listIter;
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
