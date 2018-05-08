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
	
	@before
	public void setUpItem() {
		inventory = new Stock();
		item1 = new Item("a", 1, 2, 3, 4);
		item2 = new Item("b", 5, 6, 7);
		item3 = new Item("c", 8, 9, 10, 11);
		inventory.add(item1);
		inventory.add(item2);
		inventory.add(item3);
	}

	@Test
	public void test1() {
		
		String storeName = "SuperMart";
		int initialCapital = 100000;
		
		// Store is initialised with a store name, an initial inventory, and a starting capital
		store = new Store(storeName, inventory, initialCapital);
		
		assertEquals(storeName, store.name());
		assertEquals(inventory, store.inventory());
		assertEquals(initialCapital, store.capital());
	}
	
	@Test
	public void test2() {
		
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
	
	@Test
	public void test2() {
		
		String storeName = "SuperMart";
		int initialCapital = 100000;
		
		store = new Store(storeName, inventory, initialCapital);
		
		// The inventory can be changed 
		
						
		assertEquals(initialCapital + 10000 - 30000, store.capital());
	}

}
