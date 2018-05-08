// Jacob

/* Class Notes:
 * 
 	 * Map<Item, Integer> truckStock; (Map<'the item','number to load'>)
	 * Truck stock is represented in the same Map style as store stock 
	 * however they are separate of each other.
	 * 
	 * As seen in test 5, cost should be calculated in cents as it will stop us from having
	 * floating point calculation errors. Cents can easily be displayed as dollars to the user.
	 * This can be changed later to dollars but will be easier to begin with
	 * 
 */

package truck;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import item.Item;
import item.Stock;

public class OrdinaryTruckTest {
	
	/* The following tests are for the basic functionality of
	 * the Ordinary Truck class
	 */
	
	//Test class variables 
	Map<Item, Integer> truckStock;
	private Item item1;
	private Item item2;
	
	
	/* Test 0: Declaring OridnaryTruck objects
	 */
	OrdinaryTruck ordinaryTruck;


	/* Test 1: Constructing a OrdinaryTruck object
	 */
	@Before @Test public void setUpOrdinaryTruckTest() {
		ordinaryTruck = new OrdinaryTruck();
	}
	
	/* Test 2: Gets the total cargo capacity of the truck
	 */
	@Test public void getTruckCapacityTest() {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Check the capacity
		assertEquals(1000, ordinaryTruck.getCapacity());
	}
	
	/* Test 3: Put an item of stock on the truck
	 */
	@Test public void loadStockItemTest() {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put an item on the truck 
		truckStock.put(item1, 1);
		
		//Check the item is on the truck
		assertEquals(1, ordinaryTruck.countItemStock(item1));
		
	}
	
	/* Test 4: Counts the trucks current cargo
	 */
	@Test public void countTruckStockTest() {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
				
		//Put some items on the truck 
		truckStock.put(item1, 56);
		truckStock.put(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(160, ordinaryTruck.countAllStock());
		
	}
	
	/* Test 5: Calculate the cost of the truck
	 */
	@Test public void getTruckCost() {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		truckStock.put(item1, 400);
		truckStock.put(item2, 100);
		
		//Check the cost is correct 
		assertEquals(87500, ordinaryTruck.getCost());
		
	}
		
	// TODO
	// Test 6: List the current cargo
	// Test 7: Throw error when adding temperature controlled item
	// Test 8: Unload item from truck
	// Test 9: Throw error when loading > 1000 items
	// Test 10: Throw error when removing item that doesn't exist

}
