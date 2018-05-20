package truck;

import item.Item;
import item.Stock;

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
	 */
	public abstract double getCost();
	
	
	/**
	 * Loads an item into the trucks cargo
	 * @param item to be loaded
	 * @param quantity of item to load
	 * @throws DeliveryException when excess items are loaded or a temperature controlled item is placed on a truck that can not supply that temperature
	 */
	public abstract void loadOnTruck(Item item, int quantity) throws DeliveryException;
	
	
	/**
	 * Converts the truck to a CSV format
	 * @return the truck type and cargo names and quantity in a CSV format
	 */
	public abstract String toString();
	
	
	/**
	 * @return The Stock class containing the current cargo of the truck
	 */
	public Stock getCargo() {
		return cargo;
	}
	
	
	/**
	 * @return The current maximum capacity of the truck
	 */
	public int getCapacity() {
		return capacity;
	}
	
	
	/**
	 * @return The current amount of items on board the truck
	 */
	public int countAllStock() {
		return cargo.totalQuantity();
	}
	
	
	/**
	 * Counts the number of a specific item on board the truck
	 * @param itemName is the name of the item
	 * @return the quantity of that item in the trucks cargo
	 */
	public int countItemStock(String itemName) {
		return cargo.currentQuantity(itemName);
	}
	
	
	/**
	 * @return The remaining space on board the truck
	 */
	public int getRemainingSpace() {
		return capacity - countAllStock();
	}
	
	
	
}
