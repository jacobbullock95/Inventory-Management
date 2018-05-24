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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import item.Item;

public class RefrigeratedTruckTest {
	
	/* 
	 * The following tests are for the basic functionality of
	 * the Refrigerated Truck class
	 */
	
	//Test class variables 
	private Item item3;
	private Item item4;
	private Item item5;
	
	/*
	 * No exceptions are expected
	 */
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	
	/* 
	 * Declaring RefrigeratedTruck objects
	 */
	RefrigeratedTruck refrigeratedTruck;
	@SuppressWarnings("unused")
	private int temperature;


	/* 
	 * Pre test: Constructing a RefrigeratedTruck object
	 */
	@Before 
	public void setUpRefrigeratedTruckTest() {
		refrigeratedTruck = new RefrigeratedTruck();
		item3 = new Item("milk",2,3,300,425,3);
		item4 = new Item("ice cream",8,14,175,250,-20);
	}
	
	/* 
	 * Test 1: Gets the total cargo capacity of the truck
	 */
	@Test public void getTruckCapacityTest() throws DeliveryException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Check the capacity
		assertEquals(800, refrigeratedTruck.getCapacity());
	}
	
	/* 
	 * Test 2: Put an item of stock on the truck
	 */
	@Test public void loadStockItemTest() throws DeliveryException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put an item on the truck 
		refrigeratedTruck.loadOnTruck(item3, 1);
		
		//Check the item is on the truck
		assertEquals(1, refrigeratedTruck.countItemStock(item3.getName()));
		
	}
	
	/*
	 * Test 3: Counts the trucks current cargo
	 */
	@Test public void countTruckStockTest() throws DeliveryException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
				
		//Put some items on the truck 
		refrigeratedTruck.loadOnTruck(item3, 56);
		refrigeratedTruck.loadOnTruck(item4, 104);
		
		//Check the number of items on the truck
		assertEquals(160, refrigeratedTruck.countAllStock());
		assertEquals(640, refrigeratedTruck.getRemainingSpace());
		
	}
	
	/* 
	 * Test 4: Calculate the cost of the truck
	 */
	@Test public void getTruckCost() throws DeliveryException {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		refrigeratedTruck.loadOnTruck(item3, 400);
		refrigeratedTruck.loadOnTruck(item4, 100);
		
		
		
		//Check the cost is correct 
		assertEquals(3332.98, refrigeratedTruck.getCost(), 1.0);
		
	}
	
	/* 
	 * Test 5: List the cargo currently on the truck
	 * Hint: Use .toSrting()
	 */
	@Test public void listCargoTest() throws DeliveryException  {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		refrigeratedTruck.loadOnTruck(item3, 400);
		refrigeratedTruck.loadOnTruck(item4, 100);
		
		
		//Check current items on the truck
		assertEquals(">Refrigerated\nice cream,100\nmilk,400\n", refrigeratedTruck.toString());
				
		
	}
	
	
		
	/* 
	 * Test 6: Test that truck temperature is cold enough for coldest item
	 */
	@Test public void getMaxTempTest() throws DeliveryException  {
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		
		//Put some items on the truck 
		refrigeratedTruck.loadOnTruck(item3, 400);
		refrigeratedTruck.loadOnTruck(item4, 100);
		
		
		//Check current items on the truck
		assertEquals(-20, refrigeratedTruck.getTemperature());
				
		
	}
	
	/* 
	 * Test 7: Try to add a cold item to a truck which has a temp
	 * not low enough
	 */
	@Test public void tempExceptionTest() throws DeliveryException  {
		thrown.expect(DeliveryException.class);
		thrown.expectMessage("Required temperature too low");
		//Create new truck
		refrigeratedTruck = new RefrigeratedTruck();
		int temp = refrigeratedTruck.getTemperature() -31; // -31 is to ensure the item is the wrong temp,
		//the value of this wrong temp isnt relevant it just ensures if the temp is wrong'the correct
		//error message is thrown
		
		item5 = new Item("testitem", 1,1,1,1, temp);
		
		
		
		refrigeratedTruck.loadOnTruck(item5, 1);
		
		
				
		
	}


}
