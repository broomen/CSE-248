package app;

import java.io.IOException;
import java.util.function.Predicate;

import history_model.History;
import history_model.HistoryLinkedListStore;
import trail_model.Trail;
import trail_model.TrailTreeMapStore;
import trail_model.TrailTreeSetStore;
import user_model.User;
import user_model.UserTreeMapStore;
import user_model.UserTreeSetStore;
import util.Evaluator;
import util.Utilities;

public class Demo {

	public static void main(String[] args) throws IOException {
// TREE SET TRAIL:
		TrailTreeSetStore trailStore = new TrailTreeSetStore();
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			trailStore.addAll(Utilities.emitTrails(number));
		}
		long end = System.currentTimeMillis();
		long timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "trailTreeSet");
		Evaluator.evalTrailSetFind(trailStore);
		
// TREE MAP TRAIL:
		TrailTreeMapStore trailMapStore = new TrailTreeMapStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			trailMapStore.addAll(Utilities.emitTrailsForMap(number));
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "trailTreeMap");
		Evaluator.evalTrailMapFind(trailMapStore);
		
// TREE SET USER:
		UserTreeSetStore userSetStore = new UserTreeSetStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			userSetStore.addAll(Utilities.emitUsersForSet(number));
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "userTreeSet");
		Evaluator.evalUserSetFind(userSetStore);
		
// TREE MAP USER:
		UserTreeMapStore userMapStore = new UserTreeMapStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			userMapStore.addAll(Utilities.emitUsersForMap(number));
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "userTreeMap");
		Evaluator.evalUserMapFind(userMapStore);
		
// LINKED LIST HISTORY:
		HistoryLinkedListStore historyListStore = new HistoryLinkedListStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			historyListStore.addAll(Utilities.emitHistory(number));
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "historyLinkedList");
		Evaluator.evalHistoryFind(historyListStore);
		
// FINISH STATEMENT: 		
		System.out.println("done, check the output_data folder for info!");
		
		
		
	}

}
