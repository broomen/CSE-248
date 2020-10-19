package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Evaluator {
	public static LinkedList<Integer> trailResults = new LinkedList<>();
	public static LinkedList<Integer> userResults = new LinkedList<>();
	public static LinkedList<Integer> historyResults = new LinkedList<>();
	public static File timeFile = new File("output_data/evalTimes.txt");

	public static void evalFind(String caseType) throws IOException {
		FileWriter writer = new FileWriter(timeFile, true);
		switch (caseType) {
		case "trailTreeMap":
			writer.write(trailTreeMapEval());
			writer.close();
			break;
		case "trailTreeSet":
			writer.write(trailTreeSetEval());
			writer.close();
			break;
		case "userTreeMap":
			writer.write(userTreeMapEval());
			writer.close();
			break;
		case "userTreeSet":
			writer.write(userTreeSetEval());
			writer.close();
			break;
		case "historyLinkedList":
			writer.write(historyLinkedListEval());
			writer.close();
			break;
		}
	}

	public static String trailTreeMapEval() {
		if (!trailResults.listIterator().hasPrevious()) {
			return trailResults.getFirst().toString() + " milliseconds for a trail TreeMap Find.\n";
		} else if (trailResults.listIterator().hasPrevious()) {
			return trailResults.getLast().toString() + " milliseconds for a trail TreeMap Find.\n";
		} else {
			return "no data to input.\n";
		}
	}

	public static String trailTreeSetEval() {
		if (!trailResults.listIterator().hasPrevious()) {
			return trailResults.getFirst().toString() + " milliseconds for a trail TreeSet Find.\n";
		} else if (trailResults.listIterator().hasPrevious()) {
			return trailResults.getLast().toString() + " milliseconds for a trail TreeSet Find.\n";
		} else {
			return "no data to input.\n";
		}
	}

	public static String userTreeMapEval() {
		if (!userResults.listIterator().hasPrevious()) {
			return userResults.getFirst().toString() + " milliseconds for a user TreeMap Find.\n";
		} else if (userResults.listIterator().hasPrevious()) {
			return userResults.getLast().toString() + " milliseconds for a user TreeMap Find.\n";
		} else {
			return "no data to input.\n";
		}
	}

	public static String userTreeSetEval() {
		if (!userResults.listIterator().hasPrevious()) {
			return userResults.getFirst().toString() + " milliseconds for a user TreeSet Find.\n";
		} else if (userResults.listIterator().hasPrevious()) {
			return userResults.getLast().toString() + " milliseconds for a user TreeSet Find.\n";
		} else {
			return "no data to input.\n";
		}
	}
	
	public static String historyLinkedListEval() {
		if (!historyResults.listIterator().hasPrevious()) {
			return historyResults.getFirst().toString() + " milliseconds for a history LinkedList Find.\n";
		} else if (historyResults.listIterator().hasPrevious()) {
			return historyResults.getLast().toString() + " milliseconds for a history LinkedList Find.\n";
		} else {
			return "no data to input.\n";
		}
	}

	public static void addTrailResult(int value) {
		if (trailResults.isEmpty()) {
			trailResults.addFirst(value);
		} else {
			trailResults.listIterator().add(value);
		}
	}

	public static void addUserResult(int value) {
		if (userResults.isEmpty()) {
			userResults.addFirst(value);
		} else {
			userResults.listIterator().add(value);
		}
	}

	public static void addHistoryResult(int value) {
		if (historyResults.isEmpty()) {
			historyResults.addFirst(value);
		}
		historyResults.listIterator().add(value);
	}

	public static void evalEmit(int timeTook, String caseType) throws IOException {
		FileWriter writer = new FileWriter(timeFile, true);
		switch (caseType) {
		case "trailTreeMap":
			writer.write(timeTook + " milliseconds for a trail TreeMap Insert.\n");
			writer.close();
			break;
		case "trailTreeSet":
			writer.write(timeTook + " milliseconds for a trail TreeSet Insert.\n");
			writer.close();
			break;
		case "userTreeMap":
			writer.write(timeTook + " milliseconds for a user TreeMap Insert.\n");
			writer.close();
			break;
		case "userTreeSet":
			writer.write(timeTook + " milliseconds for a user TreeSet Insert.\n");
			writer.close();
			break;
		case "historyLinkedList":
			writer.write(timeTook + " milliseconds for a history LinkedList Insert.\n");
			writer.close();
			break;
		}
	}

}
