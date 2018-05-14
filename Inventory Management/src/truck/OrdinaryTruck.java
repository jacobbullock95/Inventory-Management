// Jesse

package truck;

import item.Item;

public class OrdinaryTruck extends Truck {	
	

	public OrdinaryTruck() {
		super(1000);
	}
	
	@Override
	public double getCost() {
		
		double cost = 750 + (0.25 * this.getCargo().totalQuantity());
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
	
	
	
	
	
	
	
}
