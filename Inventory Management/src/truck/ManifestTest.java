// Jacob

package truck;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import item.Item;

public class ManifestTest {

	/* Test 0: Declaring Manifest objects
	 */
	Manifest manifest;
	Truck truck1 = new OrdinaryTruck();
	Truck truck2 = new OrdinaryTruck();
	Truck truck3 = new RefrigeratedTruck();
	Item item1 = new Item("a", 1, 2, 3, 4, 5);


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
	
					
	//Export Test
	/* Test 4: Export Manifest to string for CSV
	 */
	@Test public void exportManifestTest() throws ManifestException {
		//Create new manifest
		manifest = new Manifest();
		
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		manifest.addTruck(truck3);
		
		String theString = "";
		theString = "truck1,truck2,truck3";
		
		//Check the CSV is created correctly
		assertEquals(theString,manifest.toString());
	}
	
	//Get non-full truck test
	@Test public void nonFullTruckTest() throws ManifestException {
		//Create new manifest
		manifest = new Manifest();
		
		//Load Truck 2
		truck2.loadOnTruck(item1, 1000);
				
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		
		assertEquals(truck1, manifest.getNonFullTruck());
		
		
	}
	
	


}
