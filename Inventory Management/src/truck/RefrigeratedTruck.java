// Jesse

package truck;

import java.lang.Math;

import item.Item;
import item.Stock;

public class RefrigeratedTruck extends Truck {
	
	private int temperature;
	private int minTemperature = -20;
	
	public RefrigeratedTruck() {
		
		super(800);
		
		// Set to max temperature by default
		temperature = 10;
	}
	
	

	@Override
	public double getCost() {
		
		Stock cargo = this.getCargo();
		
		// Cost of truck
		double cost = 900 + (200 * Math.pow(0.7, temperature/5));
		
		// Cost of cargo
		for (int i = 0; i < cargo.uniqueItems(); i++) {
			
			Item item = cargo.getItemByIndex(i);
			
			cost += item.getCost() * cargo.currentQuantity(item.getName());
		}
		
		return cost;
		
	}
	
	
	
	@Override
	public void loadOnTruck(Item item, int quantity) throws DeliveryException {
		
		if ((this.countAllStock() + quantity) > this.getCapacity()) {
			// Check if room left on truck
			throw new DeliveryException("No room left on truck");
			
		} else if (item.getRequiresTemperature()) {
			// Check if item requires temperature

			if (item.getTemperature() < temperature) {
				// Check to see if temperature of new item is lower than the temperature of the truck
				
				if (item.getTemperature() < minTemperature) {
					// Check to see if truck can provide required temperature
					throw new DeliveryException("Required temperature too low.");
					
				} else {
					// Set new truck temperature and add item to cargo
					temperature = item.getTemperature();
					this.getCargo().addItem(item, quantity);
				}
			} else {
				this.getCargo().addItem(item, quantity);
			}
		} else {
			// Add item to truck cargo
			this.getCargo().addItem(item, quantity);
		}
		
	}
	
	
	
	public int getTemperature() {
		return temperature;
	}
	
	
	
	@Override
	public String toString() {
		
		Stock cargo = this.getCargo();
		String str = ">Refrigerated\n";
		
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
