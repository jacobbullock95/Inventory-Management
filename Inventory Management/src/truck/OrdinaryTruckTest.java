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
	 * Instead of using the method 'put()' to add items to truck, we use load on truck
	 * so there can be a number of checks to see if the item is temp controlled, the truck has room etc.
	 * before it is added
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
	Stock truckStock;
	private Item item1 = new Item("rice",2,3,225,300,null);
	private Item item2 = new Item("beans",4,6,450,525,null);
	private Item item3 = new Item("milk",2,3,300,425,3);
	
	
	/* Test 0: Declaring OridnaryTruck objects
	 */
	OrdinaryTruck ordinaryTruck;


	/* Test 1: Constructing a OrdinaryTruck object
	 */
	@Before @Test public void setUpOrdinaryTruckTest()  {
		ordinaryTruck = new OrdinaryTruck();
		truckStock = new Stock();
	}
	
	/* Test 2: Gets the total cargo capacity of the truck
	 */
	@Test public void getTruckCapacityTest() throws OrdinaryTruckException {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Check the capacity
		assertEquals(1000, ordinaryTruck.getCapacity());
	}
	
	/* Test 3: Put an item of stock on the truck
	 */
	@Test public void loadStockItemTest() throws OrdinaryTruckException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put an item on the truck 
		truckStock.loadOnTruck(item1, 1);
		
		//Check the item is on the truck
		assertEquals(1, ordinaryTruck.countItemStock(item1));
		
	}
	
	/* Test 4: Counts the trucks current cargo
	 */
	@Test public void countTruckStockTest() throws OrdinaryTruckException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
				
		//Put some items on the truck 
		truckStock.loadOnTruck(item1, 56);
		truckStock.loadOnTruck(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(160, ordinaryTruck.countAllStock());
		assertEquals(840, ordinaryTruck.getRemainingSpace());
		
	}
	
	/* Test 5: Calculate the cost of the truck
	 */
	@Test public void getTruckCostTest() throws OrdinaryTruckException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		truckStock.loadOnTruck(item1, 400);
		truckStock.loadOnTruck(item2, 100);
		
		
		//Check the cost is correct 
		assertEquals(87500, ordinaryTruck.getCost());
		
	}
	
	
	/* Test 6: List the cargo currently on the truck
	 *Hint: Use .toSrting()
	 */
	@Test public void listCargoTest() throws OrdinaryTruckException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		truckStock.loadOnTruck(item1, 400);
		truckStock.loadOnTruck(item2, 100);
		
		
		//Check current items on the truck
		assertEquals("item1,item2,400,100", ordinaryTruck.currentCargo());
				
		
	}
	
	/* Test 7: Throw error if temp controlled item is put on truck
	 */
	@Test(expected = OrdinaryTruckException.class)
	public void throwTempErrorTest() throws OrdinaryTruckException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put a temperature controlled item on the truck
		truckStock.loadOnTruck(item3, 1);

	}
	
	/* Test 8: Throw error if no room left on truck but item is trying to be loaded
	 */
	@Test(expected = OrdinaryTruckException.class)
	public void truckFullTest() throws OrdinaryTruckException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Load too man items onto truck
		truckStock.loadOnTruck(item1, 1001);
		
	}
	
	

}
