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

public class RefrigeratedTruckTest {
	
	/* The following tests are for the basic functionality of
	 * the Refrigerated Truck class
	 */
	
	//Test class variables 
	Map<Item, Integer> truckStock;
	private Item item1;
	private Item item2;
	
	
	/* Test 0: Declaring RefrigeratedTruck objects
	 */
	RefrigeratedTruck refrigeratedTruck;


	/* Test 1: Constructing a RefrigeratedTruck object
	 */
	@Before @Test public void setUpRefrigeratedTruckTest() {
		refrigeratedTruck = new RefrigeratedTruck();
	}
	
	/* Test 2: Gets the total cargo capacity of the truck
	 */
	@Test public void getTruckCapacityTest() {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Check the capacity
		assertEquals(800, refrigeratedTruck.getCapacity());
	}
	
	/* Test 3: Put an item of stock on the truck
	 */
	@Test public void loadStockItemTest() {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put an item on the truck 
		truckStock.put(item1, 1);
		
		//Check the item is on the truck
		assertEquals(1, refrigeratedTruck.countItemStock(item1));
		
	}
	
	/* Test 4: Counts the trucks current cargo
	 */
	@Test public void countTruckStockTest() {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
				
		//Put some items on the truck 
		truckStock.put(item1, 56);
		truckStock.put(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(160, refrigeratedTruck.countAllStock());
		
	}
	
	/* Test 5: Calculate the cost of the truck
	 */
	@Test public void getTruckCost() {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		truckStock.put(item1, 400);
		truckStock.put(item2, 100);
		
		//Check the cost is correct 
		assertEquals(87500, refrigeratedTruck.getCost());
		
	}
		
	// TODO
	// Test to get lowest tempearature then factor this in with the cost tests
	// validate temp range is fine.
	//check docs but temp seems to be the big difference between the two

}
