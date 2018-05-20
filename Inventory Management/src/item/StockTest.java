// Jesse

package item;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StockTest {

	Stock stock;
	Item item1;
	Item item2;
	Item item3;
	
	/*
	 * No exceptions are expected
	 */
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	/*
	 * This function is called before all tests
	 * this resets the store object and re-initialise the three item objects
	 */
	@Before
	public void setUpTest() {
		stock = null;
		item1 = new Item("a", 1, 2, 3, 4, 5);
		item2 = new Item("b", 5, 6, 7, 8, 9);
		item3 = new Item("c", 8, 9, 10, 11, 12);
	}

	/*
	 * Creates an stock object and add the three items. 
	 * Tests the correct recording of the size of the stock
	 */
	@Test
	public void stockCreateTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		// The function is used as follows: add(item object, number of items in stock)
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.supply(item name) should return the number of items in stock
		assertEquals(20, stock.currentQuantity("a"));
		assertEquals(1002, stock.currentQuantity("b"));
		assertEquals(40, stock.currentQuantity("c"));
	}
	
	/*
	 * Creates an stock object and add the three items. 
	 * Tests the correct recording of the size of the stock
	 */
	@Test
	public void stockCreateTestSize() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		// The function is used as follows: add(item object, number of items in stock)
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.uniqueItems() should return the number of unique items in stock
		assertEquals(3, stock.uniqueItems());
	}
	
	/*
	 * Creates an stock object and add the three items. 
	 * Tests the ability to access items by index
	 */
	@Test
	public void stockAccessIndexTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.get(index) should return the item at specified index
		assertEquals(item1, stock.getItemByIndex(0));
		assertEquals(item2, stock.getItemByIndex(1));
		assertEquals(item3, stock.getItemByIndex(2));
	}
	
	/*
	 * Creates an stock object and add the three items. 
	 * Tests the ability to access items by name
	 */
	@Test
	public void stockAccessNameTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.retrieve(name) should return an item with the specified name
		// This could be implemented through a for loop checking each items name
		assertEquals(item1, stock.getItemByName("a"));
		assertEquals(item2, stock.getItemByName("b"));
		assertEquals(item3, stock.getItemByName("c"));
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability to increase a quantity of an item
	 */
	@Test
	public void stockQuantityIncreaseTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.increaseQuantity(item name, number) should increase the stock of item name by number
		stock.increaseQuantity("a", 5);
		stock.increaseQuantity("b", 50);
		stock.increaseQuantity("c", 10);
		
		assertEquals(25, stock.currentQuantity("a"));
		assertEquals(1052, stock.currentQuantity("b"));
		assertEquals(50, stock.currentQuantity("c"));
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability to reduce a quantity of an item
	 */
	@Test
	public void stockQuantityReduceTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.reduceQuantity(item name, number) should reduce the stock of item name by number
		stock.reduceQuantity("a", 10);
		stock.reduceQuantity("b", 500);
		stock.reduceQuantity("c", 10);
		
		assertEquals(10, stock.currentQuantity("a"));
		assertEquals(502, stock.currentQuantity("b"));
		assertEquals(30, stock.currentQuantity("c"));
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability to both increase and reduce a quantity of an item
	 */
	@Test
	public void stockQuantityEditTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.reduceQuantity(item name, number) should reduce the stock of item name by number
		stock.reduceQuantity("a", 10);
		stock.reduceQuantity("b", 500);
		
		assertEquals(10, stock.currentQuantity("a"));
		assertEquals(502, stock.currentQuantity("b"));
		
		// stock.increaseQuantity(item name, number) should increase the stock of item name by number
		stock.increaseQuantity("a", 5);
		stock.increaseQuantity("b", 50);
		
		assertEquals(15, stock.currentQuantity("a"));
		assertEquals(552, stock.currentQuantity("b"));
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability for the stock class to count quantity of all items
	 */
	@Test
	public void stockQuantityTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.countQuantity() should return an integer representing the total 
		// quantity of all stock present
		assertEquals(1062, stock.totalQuantity());
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability for the stock class to count quantity of all items
	 */
	@Test
	public void stockQuantityTestTwo() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.countQuantity() should return an integer representing the total 
		// quantity of all stock present
		assertEquals(1062, stock.totalQuantity());
		
		
		// Edit quantities and test again
		stock.increaseQuantity("a", 10);
		stock.reduceQuantity("b", 500);
		
		assertEquals(572, stock.totalQuantity());
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability for the stock class to count quantity of all items
	 */
	@Test
	public void stockQuantityTestThree() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		
		// stock.countQuantity() should return an integer representing the total 
		// quantity of all stock present
		assertEquals(1022, stock.totalQuantity());
		
		
		// Edit quantities and test again
		stock.increaseQuantity("a", 10);
		stock.reduceQuantity("b", 500);
		
		assertEquals(532, stock.totalQuantity());
		
		// Add new item and test again
		stock.addItem(item3, 40);
		
		assertEquals(572, stock.totalQuantity());
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability for the stock class to count cost of all items
	 */
	@Test
	public void stockCostTest() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.countCost() should return an integer representing the total 
		// cast of all stock present
		assertEquals(1*20 +  5*1002 + 8*40 , stock.calculateCost());
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability for the stock class to count cost of all items
	 */
	@Test
	public void stockCostTestTwo() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		stock.addItem(item3, 40);
		
		// stock.countCost() should return an integer representing the total 
		// cast of all stock present
		assertEquals(1*20 +  5*1002 + 8*40 , stock.calculateCost());
		
		
		// Edit quantities and test again
		stock.increaseQuantity("a", 10);
		stock.reduceQuantity("b", 500);
		
		assertEquals(1*30 +  5*502 + 8*40 , stock.calculateCost());
	}
	
	/*
	 * Creates a stock object and adds three items.
	 * Tests the ability for the stock class to count cost of all items
	 */
	@Test
	public void stockCostTestThree() throws StockException {
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		stock.addItem(item2, 1002);
		
		// stock.countCost() should return an integer representing the total 
		// cast of all stock present
		assertEquals(1*20 +  5*1002, stock.calculateCost());
		
		
		// Edit quantities and test again
		stock.increaseQuantity("a", 10);
		stock.reduceQuantity("b", 500);
		
		assertEquals(1*30 +  5*502, stock.calculateCost());
		
		// Add new item and test again
		stock.addItem(item3, 40);
		
		assertEquals(1*30 +  5*502 + 8*40 , stock.calculateCost());
	}
	
	/*
	 * Creates a stock object and add an item.
	 * Tests the ability to throw exception with message
	 */
	@Test
	public void stockNegativeQuantityException() throws StockException {
		thrown.expect(StockException.class);
		thrown.expectMessage("Negative quantity of items exception");
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, -20);
	}
	
	/*
	 * Creates a stock object and add an item.
	 * Tests the ability to throw exception with message
	 */
	@Test
	public void stockNegativeQuantityExceptionTwo() throws StockException {
		thrown.expect(StockException.class);
		thrown.expectMessage("Negative quantity of items exception");
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, 20);
		
		// Reduce stock into negative quantity
		stock.reduceQuantity(item1.getName(), 30);
	}
	
	/*
	 * Creates a stock object and add three items.
	 * Tests the ability to throw exception with message
	 */
	@Test
	public void stockItemDoesNotExistException() throws StockException {
		thrown.expect(StockException.class);
		thrown.expectMessage("Item index out of bounds exception");
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, -20);
		stock.addItem(item2, 1002);
		
		// Access an item that does not exist
		stock.getItemByIndex(10);
	}
	
	/*
	 * Creates a stock object and add three items.
	 * Tests the ability to throw exception with message
	 */
	@Test
	public void stockItemDoesNotExistExceptionTwo() throws StockException {
		thrown.expect(StockException.class);
		thrown.expectMessage("Item does not exist exception");
		
		// Create an empty stock collection
		stock = new Stock();
		
		// Add items to the collection
		stock.addItem(item1, -20);
		stock.addItem(item2, 1002);
		
		// Access an item that does not exist
		stock.getItemByName("Cookies");
	}

}
