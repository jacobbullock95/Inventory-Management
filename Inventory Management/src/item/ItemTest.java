package item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	
	Item item;
	
	/* Items have 5 properties: name, cost to make, sell price, reorder point, reorder amount and temperature
	 * Some items do not require to be kept at a certain temperature an thus temperature == null
	 * Item definitions are loaded in through a csv into the application, see item_properties.csv on blackboard
	 */ 
	
	@Before
	public void setUpItem() {
		item = null;
	}

	@Test
	public void test() {
			
		
		String name = "rice";
		int cost = 2;
		int sellPrice = 3;
		int reorderPoint = 225;
		int reorderAmount = 300;
		//int temperature = null;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
		
		
		assertEquals(name, item.cost());
		assertEquals(cost, item.name());
		assertEquals(sellPrice, item.sellPrice());
		assertEquals(reorderPoint, item.reorderPoint());
		assertEquals(reorderAmount, item.reorderAmount());
		assertEquals(null, item.temperature());
	}
	
	@Test
	public void test2() {
		
		String name = "frozen meat";
		int cost = 10;
		int sellPrice = 14;
		int reorderPoint = 450;
		int reorderAmount = 575;
		int temperature = -14;
		
		item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
		
		
		assertEquals(name, item.cost());
		assertEquals(cost, item.name());
		assertEquals(sellPrice, item.sellPrice());
		assertEquals(reorderPoint, item.reorderPoint());
		assertEquals(reorderAmount, item.reorderAmount());
		assertEquals(temperature, item.temperature());
	}

}
