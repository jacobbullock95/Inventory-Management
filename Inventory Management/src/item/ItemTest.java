// Jesse

package item;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import item.StockException;

public class ItemTest {
	
	Item item;
	
	/* 
	 * Items have 5 properties: name, cost to make, sell price, reorder point, reorder amount and temperature
	 * Some items do not require to be kept at a certain temperature and thus temperature is an optional parameter
	 * Item definitions are loaded in through a csv into the application, see item_properties.csv on blackboard
	 */ 
	
	/*
	 * No exceptions are expected
	 */
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	/*
	 * This function is called before all tests
	 * this resets the item object
	 */
	@Before
	public void setUpItem() {
		item = null;
	}
	
	
	/*
	 * Creates an item object (without temperature) and tests getters of the item class
	 */
	@Test
	public void itemCreateTestNoTemp() {
		String name = "rice";
		int cost = 2;
		int sellPrice = 3;
		int reorderPoint = 225;
		int reorderAmount = 300;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
		
		assertEquals(name, item.getName());
		assertEquals(cost, item.getCost());
		assertEquals(sellPrice, item.getSellPrice());
		assertEquals(reorderPoint, item.getReorderPoint());
		assertEquals(reorderAmount, item.getReorderAmount());
	}
	
	/*
	 * Creates an item object (with temperature) and tests getters of the item class
	 */
	@Test
	public void itemCreateTestWithTemp() {
		
		String name = "frozen meat";
		int cost = 10;
		int sellPrice = 14;
		int reorderPoint = 450;
		int reorderAmount = 575;
		int temperature = -14;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		
		assertEquals(name, item.getName());
		assertEquals(cost, item.getCost());
		assertEquals(sellPrice, item.getSellPrice());
		assertEquals(reorderPoint, item.getReorderPoint());
		assertEquals(reorderAmount, item.getReorderAmount());
		assertEquals(temperature, item.getTemperature());
	}
	
}
