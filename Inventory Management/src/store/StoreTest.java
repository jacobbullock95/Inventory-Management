// Jesse

package store;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import item.Item;
import item.Stock;


/*
 * Store is to follow the singleton design pattern
 * from https://www.tutorialspoint.com/java/java_using_singleton.htm
 * 
   public class ClassicSingleton {
	   private static ClassicSingleton instance = null;
	   private ClassicSingleton() {
	      // Exists only to defeat instantiation.
	   }
	
	   public static ClassicSingleton getInstance() {
	      if(instance == null) {
	         instance = new ClassicSingleton();
	      }
	      return instance;
	   }
	}
 *
 */


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
	@before
	public void setUpTest() {
		store = null;
		inventory = null;
		inventory = new Stock();
		item1 = new Item("a", 1, 2, 3, 4);
		item2 = new Item("b", 5, 6, 7);
		item3 = new Item("c", 8, 9, 10, 11);
		inventory.add(item1, 20);
		inventory.add(item2, 40);
		inventory.add(item3, 60);
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
		
		assertEquals(storeName, store.name());
		assertEquals(inventory, store.inventory());
		assertEquals(initialCapital, store.capital());
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
		// Or should we have the ability to set the store name etc. when calling getInstance?
		
		assertEquals(storeName, store.name());
		assertEquals(inventory, store.inventory());
		assertEquals(initialCapital, store.capital());
	}
	
	
	
	
	/*
	 * Creates an store object and add the three items. 
	 * Adds 10000 dollars profit and tests the stores capital
	 * Adds 10000 dollars cost and tests the stores capital
	 */
	@Test
	public void storeCapitalTest() {
		
		String storeName = "SuperMart";
		int initialCapital = 100000;
		
		store = new Store(storeName, inventory, initialCapital);
		
		// Capital can be gained, e.g. here, 10000 of profit is being recorded
		store.profit(10000);
				
		assertEquals(initialCapital + 10000, store.capital());
		
		// Capital can be lost, e.g. here, 30000 of cost is being recorded
		store.cost(30000);
						
		assertEquals(initialCapital + 10000 - 30000, store.capital());
	}

}
