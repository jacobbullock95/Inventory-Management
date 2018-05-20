// Jesse

package truck;

import item.Item;
import item.Stock;

public class OrdinaryTruck extends Truck {	
	

	public OrdinaryTruck() {
		super(1000);
	}
	
	@Override
	public double getCost() {
		
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
	public void loadOnTruck(Item item, int quantity) throws DeliveryException {
		
		
		if (item.getRequiresTemperature()) {
			// Check if item requires temperature
			throw new DeliveryException("Item requires temperature.");
			
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
		
		for (int i = 0; i < cargo.uniqueItems(); i++) {
			
			name = cargo.getItemByIndex(i).getName();
			quantity = String.valueOf(cargo.currentQuantity(name));
			str += name + "," + quantity + "\n";
		}
		
		
		return str;
	}
	
	
	
	
	
	
	
}
