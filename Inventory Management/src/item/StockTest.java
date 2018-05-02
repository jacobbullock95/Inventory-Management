package item;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StockTest {
	
	/*
	 * The collection used for this should be a list such as ArrayList
	 * This allows access to the items by the index in the list.
	 */
	
	Stock stock;
	Item item1;
	Item item2;
	Item item3;
	
	@Before
	public void setUpItem() {
		stock = null;
		item1 = new Item("a", 1, 2, 3, 4);
		item2 = new Item("b", 5, 6, 7);
		item3 = new Item("c", 8, 9, 10, 11);
	}

	@Test
	public void test1() {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.add(item1);
		stock.add(item2);
		stock.add(item3);
		
		// stock.size should return the number of elements in stock
		assertEquals(3, stock.size());
	}
	
	@Test
	public void test2() {
		
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
	
	@Test
	public void test3() {
		
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
