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
		TrailTreeSetStore trailStore = new TrailTreeSetStore();
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			trailStore.addAll(Utilities.emitTrails(number));
//			trailStore.display();
//			System.out.println("--------------------");
		}
		long end = System.currentTimeMillis();
		long timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "trailTreeSet");
		Predicate<Trail> pTrail = i -> i.getId().startsWith("253");
		trailStore.find(pTrail);
		
		
		TrailTreeMapStore trailMapStore = new TrailTreeMapStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			trailMapStore.addAll(Utilities.emitTrailsForMap(number));
//			trailMapStore.display();
//			System.out.println("--------------------");
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "trailTreeMap");
		Predicate<Trail> pTrail2 = i -> i.getId().startsWith("243");
		trailMapStore.find(pTrail2);
		
		UserTreeSetStore userSetStore = new UserTreeSetStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			userSetStore.addAll(Utilities.emitUsersForSet(number));
//			userSetStore.display();
//			System.out.println("--------------------");
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "userTreeSet");
		Predicate<User> pUser1 = i -> i.getPassword().startsWith("x");
		userSetStore.find(pUser1);
		
		UserTreeMapStore userMapStore = new UserTreeMapStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			userMapStore.addAll(Utilities.emitUsersForMap(number));
//			userSetStore.display();
//			System.out.println("--------------------");
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "userTreeMap");
		Predicate<User> pUser2 = i -> i.getPassword().startsWith("x");
		userMapStore.find(pUser2);
		
		HistoryLinkedListStore historyListStore = new HistoryLinkedListStore();
		start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			int number = i * 10;
			historyListStore.addAll(Utilities.emitHistory(number));
//			historyListStore.display();
//			System.out.println("--------------------");
		}
		end = System.currentTimeMillis();
		timeTook = end - start;
		Evaluator.evalEmit((int)timeTook, "historyLinkedList");
		Predicate<History> pHistory = i -> i.getTrailName().startsWith("HIS99");
		historyListStore.find(pHistory);
		
		System.out.println("done, check the output_data folder for info!");
		
		
		
	}

}
