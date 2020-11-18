package trail_model_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import trail_model.Trail;
import trail_model.TrailStore;

public class TrailStoreTest {
	/**
	 * Tests the trail store to see if it can store trails and to see that the 
	 * size methods works. 
	 */
	@Test
	static void testAddAndSize() {
		Trail t1 = new Trail("t1");
		Trail t2 = new Trail("t2");
		Trail t3 = new Trail("t3");
		Trail t4 = new Trail("t4");
		TrailStore testStore = new TrailStore();
		testStore.add(t1);
		testStore.add(t2);
		testStore.add(t3);
		testStore.add(t4);
		assertEquals(testStore.size(), 4);	
		
	}

}
