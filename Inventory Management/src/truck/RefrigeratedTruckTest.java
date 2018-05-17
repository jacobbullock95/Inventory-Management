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
	private Item item1 = new Item("rice",2,3,225,300,null);
	private Item item2 = new Item("beans",4,6,450,525,null);
	private Item item3 = new Item("milk",2,3,300,425,3);
	private Item item4 = new Item("ice cream",8,14,175,250,-20);
	
	
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
	@Test public void getTruckCapacityTest() throws RefrigeratedTruckException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Check the capacity
		assertEquals(800, refrigeratedTruck.getCapacity());
	}
	
	/* Test 3: Put an item of stock on the truck
	 */
	@Test public void loadStockItemTest() throws RefrigeratedTruckException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put an item on the truck 
		truckStock.loadOnTruck(item3, 1);
		
		//Check the item is on the truck
		assertEquals(1, refrigeratedTruck.countItemStock(item1));
		
	}
	
	/* Test 4: Counts the trucks current cargo
	 */
	@Test public void countTruckStockTest() throws RefrigeratedTruckException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
				
		//Put some items on the truck 
		truckStock.loadOnTruck(item3, 56);
		truckStock.loadOnTruck(item4, 104);
		
		//Check the number of items on the truck
		assertEquals(160, refrigeratedTruck.countAllStock());
		assertEquals(640, refrigeratedTruck.getRemainingSpace());
		
	}
	
	/* Test 5: Calculate the cost of the truck
	 */
	@Test public void getTruckCost() throws RefrigeratedTruckException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		truckStock.loadOnTruck(item3, 400);
		truckStock.loadOnTruck(item4, 100);
		
		
		
		//Check the cost is correct 
		assertEquals(173298, refrigeratedTruck.getCost());
		
	}
	
	/* Test 6: List the cargo currently on the truck
	 *Hint: Use .toSrting()
	 */
	@Test public void listCargoTest() throws RefrigeratedTruckException  {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		truckStock.loadOnTruck(item1, 400);
		truckStock.loadOnTruck(item2, 100);
		
		
		//Check current items on the truck
		assertEquals("item1,item2,400,100", refrigeratedTruck.currentCargo());
				
		
	}
	
	
		
	/* Test 7: Test that truck temperature is cold enough for coldest item
	 */
	@Test public void getMaxTempTest() throws RefrigeratedTruckException  {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		truckStock.loadOnTruck(item3, 400);
		truckStock.loadOnTruck(item4, 100);
		
		
		//Check current items on the truck
		assertEquals(-20, refrigeratedTruck.getTemperature());
				
		
	}


}
