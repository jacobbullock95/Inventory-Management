// Jesse

package truck;

import item.Item;
import item.Stock;

public abstract class Truck {

	private int capacity;
	private Stock cargo;
	
	public Truck(int capacity) {
		cargo = new Stock();
		this.capacity = capacity;
	}
	
	public abstract double getCost();
	
	public abstract void loadOnTruck(Item item, int quantity) throws DeliveryException;
	
	public Stock getCargo() {
		return cargo;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
//	public void loadOnTruck(Item item, int quantity) {
//		cargo.addItem(item, quantity);
//	}
	
	public int countAllStock() {
		return cargo.totalQuantity();
	}
	
	public int countItemStock(String itemName) {
		return cargo.currentQuantity(itemName);
	}
	
	public String currentCargo() {
		return "s";
	}
	
	
	
}
