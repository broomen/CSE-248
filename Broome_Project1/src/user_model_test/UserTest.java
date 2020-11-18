package user_model_test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import user_model.User;

public class UserTest {
	/*
	 * Tests the user class to see if they can be made and that the default
	 * setting of their isAdmin boolean to false works.
	 */
	@Test
	static void testAdminAndName() {
		User u1 = new User("u1");
		assertTrue(u1.getUsername().contentEquals("u1"));
		assertFalse(u1.isAdmin());
		
	}
}
