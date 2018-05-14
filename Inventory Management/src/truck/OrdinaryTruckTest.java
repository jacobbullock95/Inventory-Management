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
import org.junit.Before;
import org.junit.Test;
import item.Item;

public class OrdinaryTruckTest {
	
	/* The following tests are for the basic functionality of
	 * the Ordinary Truck class
	 */
	
	//Test class variables 
	private Item item1;
	private Item item2;
	private Item item3;
	
	
	/* 
	 * Declaring OridnaryTruck objects
	 */
	OrdinaryTruck ordinaryTruck;


	/* 
	 * Pre Test: Constructing a OrdinaryTruck object
	 */
	@Before 
	public void setUpOrdinaryTruckTest()  {
		ordinaryTruck = new OrdinaryTruck();
		//truckStock = new Stock();
		item1 = new Item("rice",2,3,225,300);
		item2 = new Item("beans",4,6,450,525);
		item3 = new Item("milk",2,3,300,425,3);
	}
	
	/* 
	 * Test 1: Gets the total cargo capacity of the truck
	 */
	@Test 
	public void getTruckCapacityTest() throws DeliveryException {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Check the capacity
		assertEquals(1000, ordinaryTruck.getCapacity());
	}
	
	/* 
	 * Test 2: Put an item of stock on the truck
	 */
	@Test 
	public void loadStockItemTest() throws DeliveryException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put an item on the truck 
		ordinaryTruck.loadOnTruck(item1, 1);
		
		//Check the item is on the truck
		assertEquals(1, ordinaryTruck.countItemStock(item1.getName()));
		
	}
	
	/* 
	 * Test 3: Counts the trucks current cargo
	 */
	@Test 
	public void countTruckStockTest() throws DeliveryException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
				
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 56);
		ordinaryTruck.loadOnTruck(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(160, ordinaryTruck.countAllStock());
		
	}
	
	/* 
	 * Test 4: Calculate the cost of the truck
	 */
	@Test 
	public void getTruckCostTest() throws DeliveryException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 400);
		ordinaryTruck.loadOnTruck(item2, 100);
		
		
		//Check the cost is correct 
		assertEquals(875, ordinaryTruck.getCost(), 1.0);
		
	}
	
	
	/* 
	 * Test 5: List the cargo currently on the truck
	 * Hint: Use .toSrting()
	 */
	@Test 
	public void listCargoTest() throws DeliveryException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 400);
		ordinaryTruck.loadOnTruck(item2, 100);
		
		
		//Check current items on the truck
		assertEquals("item1,item2,400,100", ordinaryTruck.currentCargo());
				
		
	}
	
	/* 
	 * Test 6: Throw error if temp controlled item is put on truck
	 */
	@Test(expected = DeliveryException.class)
	public void throwTempErrorTest() throws DeliveryException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put a temperature controlled item on the truck
		ordinaryTruck.loadOnTruck(item3, 1);

	}
	
	/* 
	 * Test 7: Throw error if no room left on truck but item is trying to be loaded
	 */
	@Test(expected = DeliveryException.class)
	public void truckFullTest() throws DeliveryException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Load too man items onto truck
		ordinaryTruck.loadOnTruck(item1, 1001);
		
	}
	

}
