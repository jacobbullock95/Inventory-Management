package item;

public class Item implements Comparable<Item>{
	
	/**
	 * @author Jacob Bullock
	 * @version 1.0
	 */
	
	//Private Variables
	private String name;
	private int cost;
	private int sellPrice;
	private int reorderPoint;
	private int reorderAmount;
	private Object temperature;
	
	//Constructor 
	public Item(String name, int cost, int sellPrice, int reorderPoint, int reorderAmount, Object temperature) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setCost(cost);
		this.setSellPrice(sellPrice);
		this.setReorderPoint(reorderPoint);
		this.setReorderAmount(reorderAmount);
		this.setTemperature(temperature);
	}
	
	//Methods

	/**
	 * @return the cost of the item
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost to be set for the item
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name to be set for the item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sellPrice of the item
	 */
	public int getSellPrice() {
		return sellPrice;
	}

	/**
	 * @param sellPrice to be set for the item
	 */
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * @return the reorderPoint of the item
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}

	/**
	 * @param reorderPoint to be set for the item
	 */
	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	/**
	 * @return the reorderAmount of the item
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}

	/**
	 * @param reorderAmount to be set for the item
	 */
	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}

	/**
	 * @return the temperature of the item
	 */
	public Object getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature to be set for the item
	 */
	public void setTemperature(Object temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * This method is needed to implement the Comparable class so that items in the 
	 * tree map can be compared. It is a simple implementation due to the fact that
	 * the items dont require a particular order they must just be comparable.
	 * @param o is the item to be compared
	 */

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}
	

	

}
