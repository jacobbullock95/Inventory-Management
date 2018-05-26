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
import item.Stock;
import item.StockException;

public class TruckTest {
	
	/* The following tests are for the basic functionality of
	 * the Abstract Truck class
	 */
	
	//Test class variables 
	private Item item1;
	private Item item2;

	
	private Stock stock;
	
	
	/* 
	 * Declaring Truck objects
	 * Considering that 'Truck' is an abstract class an cannot be instantiated
	 * This test class will utlise an OrdinaryTruck object as all temperature testing
	 * is contained in the RefrigeratedTruckTest class
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
	}
	
	/* 
	 * Test 1: Tests to see that the getCargo method returns a Stock object
	 * correctly with the correct values in perfect place. (Also checks the countAllStock
	 * method simultaneously, this is explained below) 
	 * 
	 * Note that stock = ordinaryTruck.cargo() may be seen as a poor way to test this but
	 * the two Stock types must be synced up otherwise they return the same TreeMap but
	 * the TreeMaps are separate, therefore they are referenced by  a different number.
	 * 
	 * The second Assert in this test is to help ease the testers mind that both Stock
	 * instances do infact contain the same number of items
	 * 
	 * If both of these Assert's pass then i am confident this method is working correctly
	 * 
	 * If any issues/bugs arise during production related to Stock, it might be wise to 
	 * start looking here. That said, there seems to be no issues.
	 * 
	 * Note that the second Assert statment in this test will also cover testing the 
	 * countAllStock method in type Truck.
	 */
	@Test 
	public void getCargoTest() throws DeliveryException, StockException {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
		stock = new Stock();
		
		ordinaryTruck.loadOnTruck(item1, 100);
		ordinaryTruck.loadOnTruck(item2, 100);
			
		stock = ordinaryTruck.getCargo();
				
		//Check the capacity
		assertEquals(stock, ordinaryTruck.getCargo());
		assertEquals(stock.totalQuantity(), ordinaryTruck.countAllStock());
		assertEquals(200, ordinaryTruck.countAllStock());
	}
	
	/* 
	 * Test 2: Counts the trucks current cargo and checks the
	 * quantity of a specified item
	 */
	@Test 
	public void countItemStockTest() throws DeliveryException, StockException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
				
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 56);
		ordinaryTruck.loadOnTruck(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(56, ordinaryTruck.countItemStock(item1.getName()));
		assertEquals(104, ordinaryTruck.countItemStock(item2.getName()));
		
	}
	
	/* 
	 * Test 3: Tests to see that the remaining space on the truck is 
	 * identified correctly by the Trucks methods
	 */
	@Test 
	public void getRemainingSpaceTest() throws DeliveryException, StockException  {
		//Create new truck
		ordinaryTruck = new OrdinaryTruck();
				
		//Put some items on the truck 
		ordinaryTruck.loadOnTruck(item1, 56);
		ordinaryTruck.loadOnTruck(item2, 104);
		
		//Check the number of items on the truck
		assertEquals(840, ordinaryTruck.getRemainingSpace());
		
	}
	
	
	
	

}
