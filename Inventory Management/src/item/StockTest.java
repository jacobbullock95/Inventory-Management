// Jesse

package item;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StockTest {
	
	/*
	 * The collection used for this should be a list such as ArrayList
	 * This allows access to the items by the index in the list.
	 * 
	 * A stock object will contain:
	 * An ArrayList of Items
	 * A HashMap where:
	 * 	They key corresponds to an item.name
	 * 	The value corresponds to the quantity
	 */
	
	Stock stock;
	Item item1;
	Item item2;
	Item item3;
	
	/*
	 * This function is called before all tests
	 * this resets the store object and re-initialise the three item objects
	 */
	@Before
	public void setUpTest() {
		stock = null;
		item1 = new Item("a", 1, 2, 3, 4);
		item2 = new Item("b", 5, 6, 7);
		item3 = new Item("c", 8, 9, 10, 11);
	}

	/*
	 * Creates an stock object and add the three items. 
	 * Tests the correct recording of the size of the stock
	 * Tests the correct recording of the supply of an item
	 */
	@Test
	public void stockCreateTest() {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		// The function is used as follows: add(item object, number of items in stock)
		stock.add(item1, 20);
		stock.add(item2, 1002);
		stock.add(item3, 40);
		
		// stock.size() should return the number of unique items in stock
		assertEquals(3, stock.size());
		
		// stock.supply(item name) should return the number of items in stock
		assertEquals(20, stock.supply("a"));
		assertEquals(1002, stock.supply("a"));
		assertEquals(40, stock.supply("a"));
	}
	
	/*
	 * Creates an stock object and add the three items. 
	 * Tests the ability to access items by index
	 */
	@Test
	public void stockAccessIndexTest() {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.add(item1);
		stock.add(item2);
		stock.add(item3);
		
		// stock.get(index) should return the item at specified index
		assertEquals(item1, stock.get(0));
		assertEquals(item2, stock.get(1));
		assertEquals(item3, stock.get(2));
	}
	
	/*
	 * Creates an stock object and add the three items. 
	 * Tests the ability to access items by name
	 */
	@Test
	public void stockAccessNameTest() {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.add(item1);
		stock.add(item2);
		stock.add(item3);
		
		// stock.retrieve(name) should return an item with the specified name
		// This could be implemented through a for loop checking each items name
		assertEquals(item1, stock.retrieve("a"));
		assertEquals(item2, stock.retrieve("b"));
		assertEquals(item3, stock.retrieve("c"));
	}

}
