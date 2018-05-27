// author Jacob Bullock

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
	Item item2 = new Item("b", 1, 2, 3, 800);
	Item item3 = new Item("c", 1, 2, 3, 801);
	Item item4 = new Item("d", 1, 2, 3, 1);
	
	Item item5 = new Item("e", 1, 2, 3, 1, -10);
	Item item6 = new Item("f", 1, 2, 3, 1, -20);
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
	
	/* 
	 * Test 4a: Get the non full truck and not the full one.
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
	
	/* 
	 * Test 4b: Get the non full truck and not the full one.
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
	
	/* 
	 * Test 5: Create a ordinaryTruck
	 */
	@Test public void createTruckTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(false);
		
		manifest.addTruck(truck4);
		
		manifest.loadTrucks(false, item4);
		
		assertEquals(">Ordinary\nd,1\n", manifest.getNonFullTruck(false).toString());
		
	}
	
	/* 
	 * Test 6: Create a refrigeratedTruck
	 */
	@Test public void createColdTruckTest() throws DeliveryException, StockException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(true);
		
		manifest.addTruck(truck4);
		
		manifest.loadTrucks(true, item4);
		
		assertEquals(">Refrigerated\nd,1\n", manifest.getNonFullTruck(true).toString());		
	}
	
	/* 
	 * Test 7: load more items than truck capacity and ensure a new truck is created
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
		assertEquals(">Refrigerated\nc,1\n", manifest.getNonFullTruck(true).toString());	
	}
	
	/* 
	 * Test 8: export Manifest test
	 * The exported CSV needs to be manually examined
	 */
	@Test public void exportManifestTest() throws DeliveryException, StockException, IOException {
		//Create new manifest
		manifest = new Manifest(stock);
		
		Truck truck4 = manifest.createTruck(true);
		manifest.addTruck(truck4);
		manifest.loadTrucks(true, item3);
		
		String file = "test.csv";
		
		manifest.exportManifest(file);
	}
	
	/*
	 * Test 9: load trucks test
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
	 * Test 10: Calculate the manifest
	 */
	@Test public void calculateManifestTest() throws DeliveryException, StockException {
		
		stock.addItem(item5, 1);
		stock.addItem(item2, 1);
		stock.addItem(item3, 0);
		stock.addItem(item4, 0);
		stock.addItem(item6, 0);
		
		// Create new manifest
		manifest = new Manifest(stock);
		
		manifest.CalculateManifest();

		assertEquals(">Refrigerated\nb,798\ne,1\nf,1\n>Ordinary\nb,2\nc,801\nd,1\n", manifest.toString());

	}
	
	/*
	 * Test 11: Optimize Manifest
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
