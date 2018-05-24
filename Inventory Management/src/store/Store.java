package store;

import item.Stock;

public class Store {
	
	/**
	 * Provides the functionality to set up the Store object.
	 * Please note this class uses a Singleton design pattern
	 * in order to ensure only 1 store can exist
	 * @author Jacob Bullock
	 * @version 1.0
	 */
	
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
	 * @return a single static Store instance
	 */
	public static Store getInstance() {
		if(instance == null) {
			instance = new Store();
		}
		return instance;
	}
		
	/**
	 * @return get the stores name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param storeName sets the stores name
	 */
	public void setName(String storeName) {
		this.name = storeName;
	}

	/**
	 * @return the stores inventory
	 */
	public Stock getInventory() {
		return inventory;
	}
	
	/**
	 * @param inventory sets the stores inventory
	 */
	public void setInventory(Stock inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the stores capital
	 */
	public double getCapital() {
		return capital;
	}

	/**
	 * @param capital sets the stores capital
	 */
	public void setCapital(double initialCapital) {
		this.capital = initialCapital;
	}

	/**
	 * @param d is the amount the stores capital should be increased
	 */
	public void profit(double d) {
		this.capital += d;
	}

	/**
	 * @param d is the amount the stores capital should be decreased
	 */
	public void loss(double d) {
		this.capital -= d;
	}
}
