package user_model_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import user_model.User;
import user_model.UserStore;

public class UserStoreTest {
	/**
	 * Tests the userStore class to see if they can really store users
	 * and do it correctly by checking the size method as well after.
	 */
	@Test
	static void testUserAddAndSize() {
		User u1 = new User("u1");
		User u2 = new User("u2");
		User u3 = new User("u3");
		UserStore testStore = new UserStore();
		testStore.add(u1);
		testStore.add(u2);
		testStore.add(u3);
		assertEquals(testStore.size(), 3);
	}

}
