package item;

public class Item implements Comparable<Item>{
	
	/**
	 * This class is for setting up the Items with all of their associated
	 * detials so that they may be added to a 'Stock class' TreeMap.
	 * @author Jacob Bullock
	 * @version 1.0
	 */
	
	//Private Class Variables
	private String name;
	private int cost;
	private int sellPrice;
	private int reorderPoint;
	private int reorderAmount;
	private int temperature;
	private boolean requiresTemperature;
	
	//Constructor for Non-Temperature Controlled Items
	/**
	 * 
	 * @param name is the items name
	 * @param cost is the cost of the item to the business
	 * @param sellPrice is the price of the item for the customer
	 * @param reorderPoint is the minimum amount of stock on hand before an order is placed
	 * @param reorderAmount is the amount to order when the items quantity in stock drops below reorderPoint
	 */
	public Item(String name, int cost, int sellPrice, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.cost = cost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.requiresTemperature = false;
	}
	
	//Constructor for Temperature Controlled Items
	/**
	 * 
	 * @param name is the items name
	 * @param cost is the cost of the item to the business
	 * @param sellPrice is the price of the item for the customer
	 * @param reorderPoint is the minimum amount of stock on hand before an order is placed
	 * @param reorderAmount is the amount to order when the items quantity in stock drops below reorderPoint
	 * @param temperature is the maximum temperature this item is allowed to be stored or transported at
	 */
	public Item(String name, int cost, int sellPrice, int reorderPoint, int reorderAmount, int temperature) {
		this.name = name;
		this.cost = cost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temperature = temperature;
		this.requiresTemperature = true;
	}
	
	//Methods

	/**
	 * @return the cost of the item
	 */
	public int getCost() {
		return cost;
	}


	/**
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the sellPrice of the item
	 */
	public int getSellPrice() {
		return sellPrice;
	}


	/**
	 * @return the reorderPoint of the item
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}


	/**
	 * @return the reorderAmount of the item
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}


	/**
	 * @return the temperature of the item
	 */
	public int getTemperature() {
		return temperature;
	}

	
	public void setRequiresTemperature(boolean choice) {
		this.requiresTemperature = choice;
	}
	
	public boolean getRequiresTemperature() {
		return requiresTemperature;
	}
	
	/**
	 * This method is needed to implement the Comparable class so that items in the 
	 * tree map can be compared. It is a simple implementation due to the fact that
	 * the items dont require a particular order they must just be comparable.
	 * @param o is the item to be compared
	 */

	@Override
	public int compareTo(Item o) {
		return name.compareTo(o.name);
	}
	

	

}
