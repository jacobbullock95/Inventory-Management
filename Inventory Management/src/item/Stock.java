package item;

import java.util.Map;
import java.util.TreeMap;

public class Stock {

	/**
	 * @author Jacob Bullock
	 * @version 1.0
	 */
	
	//Private Variables
	private Map<Item, Integer> stock; // mapping from item name to rating
	
	//Constructor 
	public Stock() {
		stock = new TreeMap<Item, Integer>(); // use tree map to sort by key
	}
	
	//Methods
	
	/**
	 * Adds an item to the stock.
	 * @param item is the item to be added
	 * @param quantity is the number of the item to be added
	 * @
	 */
	public void addItem(Item item, int quantity) {
		if (stock.containsKey(item)){
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
	 * @param itemName is the item we wish to find the quantity of
	 * @return the current stored quantity of the particular item
	 */
	public int currentQuantity(String itemName) {
		return stock.get(getItemByName(itemName));
	}
	

	/**
	 * @param index is the index number of the item we wish to find
	 * @return the item found at the index specified
	 */
	public Item getItemByIndex(int index) {
		Item theItem = null;
		int counter = 0;
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
	 * @param itemName is the name of the item we wish to find
	 * @return the item found with the specified name
	 */
	public Item getItemByName(String itemName) {
		Item theItem = null;
		for (Item item : stock.keySet()) {
			if(item.getName() == itemName) {
				theItem = item;
				break;
			}
		}
		return theItem;
	}
	
	
	/**
	 * @param itemName is the name of the item to have its quantity reduced
	 * @param quantity is the amount to reduce by
	 */
	public void reduceQuantity(String itemName, int quantity) {
		int newQuantity = stock.get(getItemByName(itemName)) - quantity;
		
		stock.put(getItemByName(itemName), newQuantity);
		
	}

	/**
	 * @param itemName is the name of the item to have its quantity increased
	 * @param quantity is the amount to increase by
	 */
	public void increaseQuantity(String itemName, int quantity) {
		int newQuantity = stock.get(getItemByName(itemName)) + quantity;
		
		stock.put(getItemByName(itemName), newQuantity);
		
	}

	/**
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

