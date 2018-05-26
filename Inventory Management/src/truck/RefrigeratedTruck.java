// author Jesse Haviland

package truck;

import java.lang.Math;


import item.Item;
import item.Stock;
import item.StockException;

/**
 * Extends the truck class to implement the specific functions required to manage a refrigerated truck
 * @author Jesse Haviland
 */

public class RefrigeratedTruck extends Truck {
	
	private int temperature;
	private int minTemperature = -20;
	
	/**
	 * Iniitilises a truck with a capacity of 800 items, also sets the temperature of the truck to 10 degrees celcius
	 */
	public RefrigeratedTruck() {
		
		super(800);
		
		// Set to max temperature by default
		temperature = 10;
	}
	
	
	/**
	 * Getter method for the current temperature of the truck
	 * @return The temperature which the truck is set to
	 */
	public int getTemperature() {
		return temperature;
	}
	
	
	@Override
	public double getCost() throws StockException {
		
		Stock cargo = this.getCargo();
		
		// Cost of truck
		double exp = temperature/5.0;
		double cost = 900 + (200 * Math.pow(0.7, exp));
		
		// Cost of cargo
		for (int i = 0; i < cargo.uniqueItems(); i++) {
			
			Item item = cargo.getItemByIndex(i);
			
			cost += item.getCost() * cargo.currentQuantity(item.getName());
		}
		
		return cost;
		
	}
	
	
	@Override
	public void loadOnTruck(Item item, int quantity) throws DeliveryException, StockException {
		
		if ((this.countAllStock() + quantity) > this.getCapacity()) {
			// Check if room left on truck
			throw new DeliveryException("No room left on truck");
			
		} else if (item.getRequiresTemperature()) {
			// Check if item requires temperature

			if (item.getTemperature() < temperature) {
				// Check to see if temperature of new item is lower than the temperature of the truck
				
				if (item.getTemperature() < minTemperature) {
					// Check to see if truck can provide required temperature
					throw new DeliveryException("Required temperature too low");
					
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

	
	@Override
	public String toString() {
		
		Stock cargo = this.getCargo();
		String str = ">Refrigerated\n";
		
		String name = "";
		String quantity = "";
		
		// Loop through items adding them to the string
		for (int i = 0; i < cargo.uniqueItems(); i++) {
			
			try {
				name = cargo.getItemByIndex(i).getName();
				quantity = String.valueOf(cargo.currentQuantity(name));
			}
			 catch (StockException e) {
			}
			
			
			str += name + "," + quantity + "\n";
		}
		
		return str;
	}
}
