package store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import item.Stock;

/**
 * Provides the functionality to set up the Store object.
 * Please note this class uses a Singleton design pattern
 * in order to ensure only 1 store can exist
 * @author Jacob Bullock
 * @version 1.0
 */

public class Store {
	
	//Private Class Variables
	private String name = "SuperMart";
	private double capital = 100000.00;
	private Stock inventory = new Stock();		
	private static Store instance = null;
	
	
	//Constructor
	/**
	 * The Store constructor is made private to prevent instantiation. using the singleton design
	 * patter, the next method below getInstance will ensure only one instance of Store can be created
	 */
	private Store() {
		//made private to stop instantiation
	}
	
	/** 
	 * Gets the single instance of Store
	 * @return a single static Store instance
	 */
	public static Store getInstance() {
		if(instance == null) {
			instance = new Store();
		}
		return instance;
	}
		
	/**
	 * Getter method for the stores name
	 * @return get the stores name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method for the stores name
	 * @param storeName sets the stores name
	 */
	public void setName(String storeName) {
		this.name = storeName;
	}

	/**
	 * Getter method for the stores inventory
	 * @return the stores inventory
	 */
	public Stock getInventory() {
		return inventory;
	}
	
	/**
	 * Setter method for the stores name
	 * @param inventory sets the stores inventory
	 */
	public void setInventory(Stock inventory) {
		this.inventory = inventory;
	}

	/**
	 * Getter method for the stores capital
	 * @return the stores capital
	 */
	public double getCapital() {
		return capital;
	}

	/**
	 * Setter method for the stores name
	 * @param capital sets the stores capital
	 */
	public void setCapital(double initialCapital) {
		this.capital = initialCapital;
	}

	/**
	 * Increases the stores capital
	 * @param amount is the amount the stores capital should be increased
	 */
	public void profit(double amount) {
		this.capital += amount;
	}

	/**
	 * Reduces the stores capital
	 * @param amount is the amount the stores capital should be decreased
	 */
	public void loss(double amount) {
		this.capital -= amount;
	}
	
	/**
	 * Converts the stores prifit into the following format: "Current Capital: $[dollars].[cents]"
	 * @return the stores current capital in a string format with a label 
	 */
	public String toString() {
		
		BigDecimal roundCap = new BigDecimal (this.getCapital());
		
		BigDecimal newCap = roundCap.setScale(2, RoundingMode.HALF_UP);
		
		String str = "";
		
		str += "Current Capital: $" + newCap;
		
		return str;
	}
	
}
