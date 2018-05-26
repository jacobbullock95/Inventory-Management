package truck;



import item.Item;
import item.Stock;
import item.StockException;

/**
 * Extends the truck class to implement the specific functions required to manage an ordinary truck
 * @author Jesse Haviland
 */

public class OrdinaryTruck extends Truck {	
	
	
	/**
	 * Iniitilises a truck with a capacity of 1000 items
	 */
	public OrdinaryTruck() {
		super(1000);
	}
	
	
	@Override
	public double getCost() throws StockException {
		
		Stock cargo = this.getCargo();
		
		// Cost of truck
		double cost = 750 + (0.25 * cargo.totalQuantity());
		
		// Cost of cargo
		for (int i = 0; i < cargo.uniqueItems(); i++) {
			
			Item item = cargo.getItemByIndex(i);
			
			cost += item.getCost() * cargo.currentQuantity(item.getName());
		}

		return cost;
	}
	
	
	@Override
	public void loadOnTruck(Item item, int quantity) throws DeliveryException, StockException {
		
		if (item.getRequiresTemperature()) {
			// Check if item requires temperature
			throw new DeliveryException("Item requires temperature");
			
		} else if ((this.countAllStock() + quantity) > this.getCapacity()) {
			// Check if room left on truck
			throw new DeliveryException("No room left on truck");
			
		} else {
			// Add item to truck cargo
			this.getCargo().addItem(item, quantity);
		}
	}

	
	@Override
	public String toString() {
		
		Stock cargo = this.getCargo();
		String str = ">Ordinary\n";
		
		String name = "";
		String quantity = "";
		
		// Loop through items adding them to the string
		for (int i = 0; i < cargo.uniqueItems(); i++) {
			
			try {
				name = cargo.getItemByIndex(i).getName();
				quantity = String.valueOf(cargo.currentQuantity(name));
			} catch (StockException e) {
			}
			
				
			
			str += name + "," + quantity + "\n";
		}
		
		return str;
	}
}
