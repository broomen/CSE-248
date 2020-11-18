package trail_model_Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import trail_model.Trail;
/**
 * Tests the trail class for two specific things. One
 * that it can hold the given value correctly when given it
 * in it's constructor, and two, that the ID counter starting at 0
 * increments correctly.
 * 
 * @author Brick
 *
 */
public class TrailTest {

	@Test
	public void testIdName() {
		Trail t1 = new Trail("t1");
		Trail t2 = new Trail("t2");
		assertTrue(t2.getId() == "1");
		assertTrue(t2.getName().contentEquals("t2"));
	}
}
