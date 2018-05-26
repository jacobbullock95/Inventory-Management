package item;

import java.util.Map;
import java.util.TreeMap;



public class Stock {

/**
 * This class provides the functionality for holding a list of items 
 * in stock and providing their quantity. Note that this is different from Item 
 * as Item simply manages each item where Stock describes exactly
 * what is in the Store Stock or what Stock may be on a Truck
 * @author Jacob Bullock
 * @version 1.0
 */



	
	//Private Class Variables
	private Map<Item, Integer> stock; // mapping from item name to rating
	
	//Constructor 
	/**
	 * Creates a Stock object which is of the type TreeMap
	 */
	public Stock() {
		stock = new TreeMap<Item, Integer>(); // use tree map to sort by key
	}
	
	//Methods
	
	/**
	 * Adds an item to the stock.
	 * @param item is the item to be added
	 * @param quantity is the number of the item to be added
	 * @throws StockException 
	 * @
	 */
	public void addItem(Item item, int quantity) throws StockException {
		if(quantity < 0) {
			throw new StockException("Negative quantity of items exception");
		}
		else if (stock.containsKey(item)){
			stock.put(item, quantity + stock.get(item)); //If the key exists and we use 'put', the item (key) will remain the same
											//and a new quantity (value) is added
		}
		else
			stock.put(item, quantity); // If item isnt already in list, add it and initialise its quantity.
										//When adding an item, if you do not wish to add a quantity then pass 
										//in 0.
	}
	
	/**
	 * @return the number of unique items in the stock
	 */
	public int uniqueItems() {
		return stock.size();
	}

	/**
	 * Gets the current stored quantity of the particular item 
	 * @param itemName is the item we wish to find the quantity of
	 * @return the current stored quantity of the particular item
	 * @throws StockException 
	*/
	public int currentQuantity(String itemName) throws StockException  {
		return stock.get(getItemByName(itemName));
	}
	

	/**
	 * Gets the item at a certain index in the stock
	 * @param index is the index number of the item we wish to find
	 * @return the item found at the index specified
	 * @throws StockException 
	 */
	public Item getItemByIndex(int index) throws StockException {
		Item theItem = null;
		int counter = 0;
		
		if(index > this.uniqueItems() - 1) {
			throw new StockException("Item index out of bounds exception");
		}
		
		for (Item item : stock.keySet()) {
			if(counter == index) {
				theItem = item;
				break;
			}
			counter ++;
		}
		return theItem;
	}
	
	
	/**
	 * Gets an item from the stock with a particular name
	 * @param itemName is the name of the item we wish to find
	 * @return the item found with the specified name
	 * @throws StockException 
	 */
	public Item getItemByName(String itemName) throws StockException {
		Item theItem = null;
		for (Item item : stock.keySet()) {
			if(item.getName().equals(itemName)) {
				theItem = item;
				break;
			}
		}
		if(theItem == null) {
			throw new StockException("Item does not exist exception");
		}
		else {
			return theItem;
		}
		
	}
	
	
	/**
	 * Reduces the quantity of an item
	 * @param itemName is the name of the item to have its quantity reduced
	 * @param quantity is the amount to reduce by
	 * @throws StockException 
	 */
	public void reduceQuantity(String itemName, int quantity) throws StockException {
		int priorQuantity = this.currentQuantity(itemName);
		int newQuantity = 0;
		
		if(quantity > priorQuantity) {
			throw new StockException("Negative quantity of items exception");
		}
		else {
			newQuantity = stock.get(getItemByName(itemName)) - quantity;
		}
		
		
		stock.put(getItemByName(itemName), newQuantity);
		
	}

	/**
	 * Increases the quantity of an item
	 * @param itemName is the name of the item to have its quantity increased
	 * @param quantity is the amount to increase by
	 * @throws StockException 
	 */
	public void increaseQuantity(String itemName, int quantity) throws StockException {
		int newQuantity = stock.get(getItemByName(itemName)) + quantity;
		
		stock.put(getItemByName(itemName), newQuantity);
		
	}

	/**
	 * Gets the total quantity of all items in stock
	 * @return the total quantity of all items in stock
	 */
	public int totalQuantity() {
		int theQuantity = 0;
		for (Item item : stock.keySet()) {
			theQuantity += stock.get(item);
			}
		return theQuantity;
	}
	
	/**
	 * Calculates the cost of all items in stock 
	 * @return the cost of all items in stock
	 */
	public int calculateCost() {
		int theCost = 0;
		for (Item item : stock.keySet()) {
			theCost += (item.getCost() * stock.get(item));
			}
		return theCost;
	}


	
}


