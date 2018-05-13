// Jesse

package store;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import item.Item;
import item.Stock;


public class StoreTest {
	
	Store store;
	Stock inventory;
	Item item1;
	Item item2;
	Item item3;
	
	/*
	 * This function is called before all tests
	 * This resets the store and stock object
	 * and re-initialise three item objects
	 * and add the three items to the inventory
	 */
	@Before
	public void setUpTest() {
		store = null;
		inventory = new Stock();
	}

	/*
	 * Creates an store object and add the three items. 
	 * Tests the getters of the store class
	 */
	@Test
	public void storeCreateTest() {

		String storeName = "SuperMart";
		int initialCapital = 100000;

		// Store is a singleton class meaning we call getInstance to obtain a reference
		store = Store.getInstance();
		
		// Use setters to set the name, inventory and capital of the store
		store.setName(storeName);
		store.setInventory(inventory);
		store.setCapital(initialCapital);
		
		// Or should we have the ability to set the store name etc. when calling getInstance?
		
		assertEquals(storeName, store.getName());
		assertEquals(inventory, store.getInventory()); 	
		assertEquals(initialCapital, store.getCapital());
	}
	
	/*
	 * Creates an store object and add the three items. 
	 * Since store is a singleton class, we should be able to call get instance and all information
	 * should still be present
	 */
	@Test
	public void storeSingletonTest() {
		
		String storeName = "SuperMart";
		int initialCapital = 100000;

		// Store is a singleton class meaning we call getInstance to obtain a reference
		store = Store.getInstance();
		
		/*
		 * Because calling getInstance creates a diferent Stock object (Treemap) in the Store class
		 * than the one created in storetest, we must call set inventory to ensure the assertion returns
		 * true. Without calling this, both TreeMaps will contain the same data but return a different
		 * hash value causing the test to fail.
		 * 
		 * Name & Initial capital however do not need to be set and will default to the correct amounts.
		 * the default values can only be changed in the Store class as they are hard-coded in.
		 */
		store.setInventory(inventory);
		
		assertEquals(storeName, store.getName());
		assertEquals(inventory, store.getInventory());
		assertEquals(initialCapital, store.getCapital());
	}
	
	
	
	
	/*
	 * Creates an store object and add the three items. 
	 * Adds 10000 dollars profit and tests the stores capital
	 * Adds 10000 dollars cost and tests the stores capital
	 */
	@Test
	public void storeCapitalTest() {
		
		int initialCapital = 100000;
		
		store = Store.getInstance();
		
		// Capital can be gained, e.g. here, 10000 of profit is being recorded
		store.profit(10000);
				
		assertEquals(initialCapital + 10000, store.getCapital());
		
		// Capital can be lost, e.g. here, 30000 of cost is being recorded
		store.loss(30000);
						
		assertEquals(initialCapital + 10000 - 30000, store.getCapital());
	}

}
