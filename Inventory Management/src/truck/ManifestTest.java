// Jacob

package truck;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import item.Item;
import item.Stock;
import item.StockException;
import truck.Manifest;

public class ManifestTest {

	/* 
	 * Declaring Manifest objects
	 */
	Manifest manifest;

	Truck truck1 = new OrdinaryTruck();
	Truck truck2 = new OrdinaryTruck();
	Truck truck3 = new RefrigeratedTruck();
	Item item1 = new Item("a", 1, 2, 3, 1000);
	Item item2 = new Item("a", 1, 2, 3, 800);
	Item item3 = new Item("a", 1, 2, 3, 801);
	Item item4 = new Item("a", 1, 2, 3, 1);
	
	Item item5 = new Item("a", 1, 2, 3, 1,-10);
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
	@Test public void getTotalCostTest() throws DeliveryException, StockException {
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
	@Test public void nonFullTruckTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		//Load Truck 2
		truck2.loadOnTruck(item1, 1000);
				
		//Add trucks
		manifest.addTruck(truck1);
		manifest.addTruck(truck2);
		
		assertEquals(truck1, manifest.getNonFullTruck(false));
		
		
	}
	
	/* Test 4: Get the non full truck and not the full one.
	*/
	@Test public void coldNonFullTruckTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		//Load Truck 2
		truck2.loadOnTruck(item1, 1000);
				
		//Add trucks
		manifest.addTruck(truck3);
		manifest.addTruck(truck2);
		
		assertEquals(truck3, manifest.getNonFullTruck(true));
		
		
	}
	
	/* Test 5: Create a ordinaryTruck
	*/
	@Test public void createTruckTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(false);
		
		manifest.addTruck(truck4);
		
		manifest.loadTrucks(false, item1);
		
		assertEquals(manifest.createTruck(false).toString(), manifest.getNonFullTruck(false).toString());
		
	}
	
	/* Test 6: Create a refrigeratedTruck
	*/
	@Test public void createColdTruckTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(true);
		
		manifest.addTruck(truck4);
		
		manifest.loadTrucks(true, item2);
		
		assertEquals(manifest.createTruck(true).toString(), manifest.getNonFullTruck(true).toString());		
	}
	
	/* Test 7: load more items than truck capacity and ensure a new truck is created
	 * and item is continued to be loaded
	*/
	@Test public void continueLoadingTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(true);
		Truck truck5 = manifest.createTruck(true);
		
		manifest.addTruck(truck4);
		manifest.addTruck(truck5);
		
		manifest.loadTrucks(true, item3);
		
		assertEquals(truck5, manifest.getNonFullTruck(true));		
	}
	
	/* Test 8: export Manifest test
	*/
	@Test public void exportManifestTest() throws DeliveryException, StockException, IOException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(true);
		manifest.addTruck(truck4);
		
		String file = " ";
		
		manifest.exportManifest(file);

		assertEquals(file, " ");
		
		
	}
	
	/*
	 * Test 8: load trucks test
	 */
	@Test public void loadTrucksTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		manifest.addTruck(truck1);
		
		manifest.addTruck(truck2);
		
		manifest.loadTrucks(false, item1);
		
		assertEquals(truck2, manifest.getNonFullTruck(false));
		
	}
	
	/*
	 * Test 9: Calculate the manifest
	 */
	
	@Test public void calculateManifestTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		stock.addItem(item1, 1);
		
		manifest.addTruck(truck1);
		
		manifest.loadTrucks(false, item1);
		
		manifest.CalculateManifest();
		
	}
	
	/*
	 * Test 10: Optimize Manifest
	 */
	
	@Test public void optimizeManifestTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		stock.addItem(item5, 1001);
		
		manifest.addTruck(truck3);
		
		manifest.loadTrucks(false, item5);
		
		manifest.OptimiseManifest();
				
	}
	
	
	


}
