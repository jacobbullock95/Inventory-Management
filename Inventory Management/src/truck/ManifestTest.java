// author Jacob Bullock

package truck;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import item.Item;
import item.Stock;
import truck.Manifest;

public class ManifestTest {

	/* 
	 * Declaring Manifest objects
	 */
	Manifest manifest;

	Truck truck1 = new OrdinaryTruck();
	Truck truck2 = new OrdinaryTruck();
	Truck truck3 = new RefrigeratedTruck();
	Item item1 = new Item("a", 1, 2, 3, 4);
	Stock stock = new Stock();



	/* 
	 * Pre test: Constructing a Manifest object
	 */
	@Before 
	public void setUpOrdinaryTruckTest()  {
		manifest = null;
		truck1 = new OrdinaryTruck();
		truck2 = new OrdinaryTruck();
		truck3 = new RefrigeratedTruck();
	}
	
	/* 
	 * Test 1: Count the manifest
	 */
	@Test public void countNumTrucksTest() throws DeliveryException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		
		//Check the capacity
		assertEquals(3, manifest.totalTrucks());
	}
	
	/* 
	 * Test 2: Get the total cost of the order
	 */
	@Test public void getTotalCostTest() throws DeliveryException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		double cost = truck1.getCost() + truck2.getCost() + truck3.getCost();
		
		//Check the capacity
		assertEquals(cost, manifest.getTotalCost(), 1.0);
	}
	

	/* 
	 * Test 3: List the manifest
	 * Hint: Use .toSrting()
	 */
	@Test public void listManifestTest() throws DeliveryException  {

		//Create new manifest
		manifest = new Manifest(stock);
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		String theString = "";
		theString = ">Ordinary\n>Ordinary\n>Refrigerated\n";
		
		//Check the CSV is created correctly
		assertEquals(theString,manifest.toString());
	}
	
	/* Test 4: Get the non full truck and not the full one.
	*/
	@Test public void nonFullTruckTest() throws DeliveryException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		//Load Truck 2
		truck2.loadOnTruck(item1, 1000);
				
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		
		assertEquals(truck1, manifest.getNonFullTruck(false));
		
		
	}
	
	


}
