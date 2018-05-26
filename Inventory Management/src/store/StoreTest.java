// author Jesse Haviland

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
	 * Creates an store object
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
		assertEquals(initialCapital, store.getCapital(), 0);
	}
	
	/*
	 * Obtains store object
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
		store.setCapital(initialCapital);
		
		assertEquals(storeName, store.getName());
		assertEquals(inventory, store.getInventory());
		assertEquals(initialCapital, store.getCapital(), 0);
	}
	
	/*
	 * Obtains store object
	 * Adds a double profit and tests the stores capital
	 */
	@Test
	public void storeCapitalTest() {
		
		int initialCapital = 100000;
		
		store = Store.getInstance();
		
		// Capital can be gained, e.g. here, 10000 of profit is being recorded
		store.profit(10000.55);
				
		assertEquals(initialCapital + 10000.55, store.getCapital(), 0.01);
	}
	
	/*
	 * Obtains store object
	 * Adds an int profit and tests the stores capital
	 */
	@Test
	public void storeCapitalTestTwo() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		
		// Capital can be gained, e.g. here, 10000 of profit is being recorded
		store.profit(10000);
				
		assertEquals(initialCapital + 10000, store.getCapital(), 0.01);
	}
	
	/*
	 * Obtains store object
	 * Reduces a double dollars cost and tests the stores capital
	 */
	@Test
	public void storeCapitalTestThree() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		
		// Capital can be lost, e.g. here, 30000 of cost is being recorded
		store.loss(30000.33);
						
		assertEquals(initialCapital - 30000.33, store.getCapital(), 0.01);
	}
	
	/*
	 * Obtains store object
	 * Reduces an int dollars cost and tests the stores capital
	 */
	@Test
	public void storeCapitalTestFour() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		
		// Capital can be lost, e.g. here, 30000 of cost is being recorded
		store.loss(30000);
						
		assertEquals(initialCapital - 30000, store.getCapital(), 0.01);
	}
	
	/*
	 * Obtains store object
	 * Adds 10000 dollars profit and tests the stores capital
	 */
	@Test
	public void storeCapitalTestFive() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		
		// Capital can be gained, e.g. here, 10000 of profit is being recorded
		store.profit(10000.01);
				
		assertEquals(initialCapital + 10000.01, store.getCapital(), 0.01);
		
		// Capital can be lost, e.g. here, 30000 of cost is being recorded
		store.loss(30000.33);
						
		assertEquals(initialCapital + 10000.01 - 30000.33, store.getCapital(), 0.99);
	}
	
	/*
	 * Tests the toString method of Store class.
	 * Should return a string in the following format (always ROUNDED to two decimal places!):
	 * "Current Capital: $[DOLLARS].[CENTS]"
	 * Eg. "Current Capital: $123456.78", "Current Capital: $123456.00" or "Current Capital: $123.00" or 
	 */
	@Test
	public void storeCapitalToString() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		
		assertEquals("Current Capital: $100000.00", store.toString());
	}
	
	/*
	 * Tests the toString method of Store class.
	 */
	@Test
	public void storeCapitalToStringTwo() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		store.profit(10000.01);
		
		assertEquals("Current Capital: $110000.01", store.toString());
	}
	
	/*
	 * Tests the toString method of Store class.
	 */
	@Test
	public void storeCapitalToStringThree() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		store.loss(30000.33);
		
		assertEquals("Current Capital: $69999.67", store.toString());
	}
	
	/*
	 * Tests the toString method of Store class.
	 */
	@Test
	public void storeCapitalToStringRounding() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		store.profit(30000.505);
		
		assertEquals("Current Capital: $130000.51", store.toString());
	}
	
	/*
	 * Tests the toString method of Store class.
	 */
	@Test
	public void storeCapitalToStringRoundingTwo() {
		
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setCapital(initialCapital);
		store.profit(30000.504);
		
		assertEquals("Current Capital: $130000.50", store.toString());
	}

}
