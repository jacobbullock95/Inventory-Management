// Jesse

package truck;

import item.Item;
import item.Stock;

public abstract class Truck {

	private int capacity = 1000;
	private Stock cargo;
	
	public Truck() {
		cargo = new Stock();
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void loadOnTruck(Item item, int quantity) {
		cargo.addItem(item, quantity);
	}
	
	public int countAllStock() {
		return cargo.totalQuantity();
	}
	
	public int getCost() {
		return cargo.calculateCost();
	}
	
	public int countItemStock(String itemName) {
		return cargo.currentQuantity(itemName);
	}
	
	public String currentCargo() {
		return "s";
	}
	
	
	
}
