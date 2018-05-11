// Jacob

package truck;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManifestTest {

	/* Test 0: Declaring Manifest objects
	 */
	Manifest manifest;
	Truck truck1 = new OrdinaryTruck();
	Truck truck2 = new OrdinaryTruck();
	Truck truck3 = new RefrigeratedTruck();


	/* Test 1: Constructing a Manifest object
	 */
	@Before @Test public void setUpOrdinaryTruckTest()  {
		manifest = new Manifest();
	}
	
	/* Test 2: Count the manifest
	 */
	@Test public void countNumTrucksTest() throws ManifestException {
		//Create new manifest
		manifest = new Manifest();
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		
		//Check the capacity
		assertEquals(3, manifest.numTrucks());
	}
	
	/* Test 3: Get the total cost of the order
	 */
	@Test public void getTotalCostTest() throws ManifestException {
		//Create new manifest
		manifest = new Manifest();
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		int cost = truck1.getCost() + truck2.getCost() + truck3.getCost();
		
		//Check the capacity
		assertEquals(cost, manifest.getTotalCost());
	}
	
	/* Test 4: List the manifest
	 */
	
	/* Test 6: List the manifest
	 *Hint: Use .toSrting()
	 */
	@Test public void listManifestTest() throws ManifestException  {
		//Create new manifest
		manifest = new Manifest();
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		
		//Check current items on the truck
		assertEquals("truck1,truck2,truck3", ordinaryTruck.listManifest());
				
		
	}
	
	

	//Testing includes creating trucks and adding them to a manifest. keeping track of each 
	//truck object, possibly in an array, and being able to access each objects attributes
	//for example, truckArray[4].cost or truckArray[5].maxTempearature etc.....
	
	//other tests may include operations performed on the manifest.

}
