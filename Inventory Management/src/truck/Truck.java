package truck;

import item.Item;
import item.Stock;
import item.StockException;

/**
 * An abstract class that implements the basic functions required to manage a truck
 * @author Jesse Haviland
 */

public abstract class Truck {

	private int capacity;
	private Stock cargo;
	
	
	/**
	 * Initialises the truck with an empty cargo and a capacity
	 * @param capacity is the total number of items the truck can hold
	 */
	public Truck(int capacity) {
		cargo = new Stock();
		this.capacity = capacity;
	}
	
	
	/**
	 * Calculates the total cost of the truck plus its contents
	 * @return The cost of the truck plus its contents in dollars
	 * @throws StockException 
	 */
	public abstract double getCost() throws StockException;
	
	
	/**
	 * Loads an item into the trucks cargo
	 * @param item to be loaded
	 * @param quantity of item to load
	 * @throws DeliveryException when excess items are loaded or a temperature controlled item is placed on a truck that can not supply that temperature
	 * @throws StockException 
	 */
	public abstract void loadOnTruck(Item item, int quantity) throws DeliveryException, StockException;
	
	
	/**
	 * Converts the truck to a CSV format
	 * @return the truck type and cargo names and quantity in a CSV format
	 * @throws StockException
	 */
	public abstract String toStringTruck() throws StockException;
	
	
	/**
	 * Getter method for the current cargo of the truck
	 * @return The Stock class containing the current cargo of the truck
	 */
	public Stock getCargo() {
		return cargo;
	}
	
	
	/**
	 * Getter method for the current capacity of the truck
	 * @return The current maximum capacity of the truck
	 */
	public int getCapacity() {
		return capacity;
	}
	
	
	/**
	 * Gets the quantity of stock on board the truck
	 * @return The current amount of items on board the truck
	 */
	public int countAllStock() {
		return cargo.totalQuantity();
	}
	
	
	/**
	 * Counts the number of a specific item on board the truck
	 * @param itemName is the name of the item
	 * @return the quantity of that item in the trucks cargo
	 * @throws StockException 
	 */
	public int countItemStock(String itemName) throws StockException {
		return cargo.currentQuantity(itemName);
	}
	
	
	/**
	 * Gets the amount of space remaining on the truck
	 * @return The remaining space on board the truck
	 */
	public int getRemainingSpace() {
		return capacity - countAllStock();
	}
	
	
	
}
