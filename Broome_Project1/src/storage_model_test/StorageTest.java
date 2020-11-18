package storage_model_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import storage_model.Storage;
import trail_model.Trail;

/**
 * Tests the storage class. Tests to see if it really holds the two values it claims to.
 * 
 * @author Nick Broome
 *
 */

public class StorageTest {
	private static Storage storage;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		storage = new Storage();
		Trail t = new Trail("test");
		storage.getTrailStorage().add(t);
	}

	@Test
	void testStorage() {
		assertFalse(storage.getTrailStorage().getTrailStore().isEmpty());
		assertTrue(storage.getUserStorage().getUserStore().isEmpty());
	}
}
