// author Jesse Haviland

package item;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ItemTest {
	
	Item item;
	Item item2;
	
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
	
	/*
	 * Creates an item object (with temperature) and tests getRequiresTemperature()
	 */
	@Test
	public void itemCreateTestWithTempTwo() {
		
		String name = "frozen meat";
		int cost = 10;
		int sellPrice = 14;
		int reorderPoint = 450;
		int reorderAmount = 575;
		int temperature = -14;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		
		assertEquals(true, item.getRequiresTemperature());
	}
	
	/*
	 * Creates an item object (without temperature) and tests getRequiresTemperature()
	 */
	@Test
	public void itemCreateTestNoTempTwo() {
		String name = "rice";
		int cost = 2;
		int sellPrice = 3;
		int reorderPoint = 225;
		int reorderAmount = 300;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
		
		assertEquals(false,  item.getRequiresTemperature());
	}
	
	/*
	 * Compare two items together
	 */
	@Test
	public void itemCompare() {
		String name = "rice";
		int cost = 2;
		int sellPrice = 3;
		int reorderPoint = 225;
		int reorderAmount = 300;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
		
		assertEquals(0,  item.compareTo(item));
	}
	
	/*
	 * Compare two items together
	 */
	@Test
	public void itemCompareTwo() {
		String name = "rice";
		int cost = 2;
		int sellPrice = 3;
		int reorderPoint = 225;
		int reorderAmount = 300;
		
		String name2 = "frozen meat";
		int cost2 = 10;
		int sellPrice2 = 14;
		int reorderPoint2 = 450;
		int reorderAmount2 = 575;
		int temperature2 = -14;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
		
		item2 = new Item(name2, cost2, sellPrice2, reorderPoint2, reorderAmount2, temperature2);
		
		
		assertEquals(item.getName().compareTo(item2.getName()),  item.compareTo(item2));
		assertEquals(item2.getName().compareTo(item.getName()),  item2.compareTo(item));
	}
	
}
