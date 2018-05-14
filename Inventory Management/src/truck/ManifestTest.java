// Jacob

package truck;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManifestTest {

	/* 
	 * Declaring Manifest objects
	 */
	Manifest manifest;
	Truck truck1;
	Truck truck2;
	Truck truck3;


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
		manifest = new Manifest();
		
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
		manifest = new Manifest();
		
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
		manifest = new Manifest();
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		
		//Check current items on the truck
		assertEquals("truck1,truck2,truck3", manifest.listManifest());
				
		
	}
	
	

	//Testing includes creating trucks and adding them to a manifest. keeping track of each 
	//truck object, possibly in an array, and being able to access each objects attributes
	//for example, truckArray[4].cost or truckArray[5].maxTempearature etc.....
	
	//other tests may include operations performed on the manifest.

}
