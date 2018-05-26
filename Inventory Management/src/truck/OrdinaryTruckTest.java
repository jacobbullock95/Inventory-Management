// author Jacob Bullock

package truck;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import item.Item;
import item.StockException;


public class OrdinaryTruckTest {
	
	/* The following tests are for the basic functionality of
	 * the Ordinary Truck class
	 */
	
	/*
	 * No exceptions are expected
	 */
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
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
	public void loadStockItemTest() throws DeliveryException, StockException  {
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
	public void countTruckStockTest() throws DeliveryException, StockException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
				
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 56);
		ordinaryTruck.loadOnTruck(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(160, ordinaryTruck.countAllStock());
		assertEquals(840, ordinaryTruck.getRemainingSpace());
		
	}
	
	/* 
	 * Test 4: Calculate the cost of the truck
	 */
	@Test 
	public void getTruckCostTest() throws DeliveryException, StockException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 400);
		ordinaryTruck.loadOnTruck(item2, 100);
		
		
		//Check the cost is correct 
		assertEquals(2075, ordinaryTruck.getCost(), 1.0);
		
	}
	
	
	/* 
	 * Test 5: List the cargo currently on the truck
	 * Hint: Use .toSrting()
	 */
	@Test 
	public void listCargoTest() throws DeliveryException, StockException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 400);
		
		
		//Check current items on the truck
		assertEquals(">Ordinary\nrice,400\n", ordinaryTruck.toString());
				
		
	}
	
	/* 
	 * Test 6: Throw error if temp controlled item is put on truck
	 */
	@Test
	public void throwTempErrorTest() throws DeliveryException, StockException  {
		thrown.expect(DeliveryException.class);
		thrown.expectMessage("Item requires temperature");
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Put a temperature controlled item on the truck
		ordinaryTruck.loadOnTruck(item3, 1);

	}
	
	/* 
	 * Test 7: Throw error if no room left on truck but item is trying to be loaded
	 */
	@Test
	public void truckFullTest() throws DeliveryException, StockException  {
		thrown.expect(DeliveryException.class);
		thrown.expectMessage("No room left on truck");
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		
		//Load too man items onto truck
		ordinaryTruck.loadOnTruck(item1, 1001);
		
	}
	
	

}
