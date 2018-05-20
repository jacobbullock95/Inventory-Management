package truck;

import java.util.ArrayList;
import csv.CSVWrite;
import item.Item;
import item.Stock;

/**
 * Provides functionality to manage a collection of trucks. 
 * @author Jesse Haviland
 */

public class Manifest {
	
	private ArrayList<Truck> truckList;
	private Stock stock;
	private ArrayList<Item> list;
	
	
	/**
	 * Initialises the manifest object. Stores provided stock and initialises the truck and item lists
	 * @param stock of the store
	 */
	public Manifest(Stock stock) {
		this.stock = stock;
		truckList = new ArrayList<Truck>();
		list = new ArrayList<Item>();
	}
	
	
	/**
	 * Adds a truck to the list of trucks
	 * @param truck to be added
	 */
	public void addTruck(Truck truck) {
		truckList.add(truck);
	}
	
	
	/**
	 * @return The total number of trucks in the manifest
	 */
	public int totalTrucks() {
		return truckList.size();
	}
	
	
	/**
	 * @return The total cost of all trucks and their cargo in the manifest
	 */
	public double getTotalCost() {
		double cost = 0;
		
		// Get truck cost
		for (int i = 0; i < truckList.size(); i++) {
			cost += truckList.get(i).getCost();
		}
		
		return cost;
		
	}
	
	
	/**
	 * Searches for a truck in the manifest with space left of board
	 * @param cooled indicates a refrigerated truck is required otherwise a truck of any type will be returned
	 * @return A non full truck of requested type
	 */
	public Truck getNonFullTruck(boolean cooled) {
		
		Truck truck;
		
		for (int i = 0; i < truckList.size(); i++) {
			if (cooled 
			    && truckList.get(i) instanceof RefrigeratedTruck
			    && truckList.get(i).getRemainingSpace() > 0) {
					return truckList.get(i);

			} else if (!cooled 
				&& truckList.get(i).getRemainingSpace() > 0) {
					return truckList.get(i);
			}
		}

		truck = createTruck(cooled);
		addTruck(truck);

		return truck;	
	}
	
	
	/**
	 * Calculates the items that need to be re-ordered
	 * Calls OptimiseManifest
	 * @throws DeliveryException
	 */
	public void CalculateManifest() throws DeliveryException {
		
		for (int i = 0; i < stock.uniqueItems(); i++) {
			
			Item currentItem = stock.getItemByIndex(i);
			String currentItemName = currentItem.getName();
			
			// If current quantity is below the re order point
			if (stock.currentQuantity(currentItemName) < currentItem.getReorderPoint()) {
				list.add(currentItem);
			}
		}
		
		OptimiseManifest();
		
	}
	
	
	/**
	 * Loads required items with quantities onto trucks in a cost optimal manner. Adds new trucks to the manifest
	 * @throws DeliveryException
	 */
	public void OptimiseManifest() throws DeliveryException {
		final int MAX_REF_TEMP = 10;
		final boolean COOLED = true;
		final boolean NOT_COOLED = false;
		
		int itemsRemaining = list.size();
		int minTemp = 100;
		int index = 0;
		
		// While items need to be loaded
		while (itemsRemaining > 0) {
			
			// Get the item on the list with the lowest required temperature
			for (int i = 0; i < list.size(); i++) {
				
				if (list.get(i).getRequiresTemperature()) {
					if (list.get(i).getTemperature() < minTemp) {
						minTemp = list.get(i).getTemperature();
						index = i;
					}
				}
			}
			
			if (minTemp <= MAX_REF_TEMP) {
				// If object requires refrigeration, load on refrigerated truck
				loadTrucks(COOLED, list.get(index));
			} else {
				// If object does not require refrigeration, load on any truck
				loadTrucks(NOT_COOLED, list.get(index));
			}
			
			// Remove item from list
			list.remove(list.get(index));
			
			// Reset the min temp
			minTemp = 100;
			
			// Reset the item index to 0
			index = 0;
			
			// Reduce to reflect number of items to be added
			itemsRemaining--;
		}
		
	}
	
	
	/**
	 * Loads the re-order amount of an item onto trucks
	 * @param cooled indicates a refrigerated truck is required otherwise it will be loaded on a truck of any type
	 * @throws DeliveryException
	 */
	public void loadTrucks(boolean cooled, Item item) throws DeliveryException {
		
		int quantityRem = item.getReorderAmount();
		Truck truck;
		int spaceRem;
		
		// Don't stop until item is fully loaded
		while (quantityRem > 0) {
			truck = getNonFullTruck(cooled);
			spaceRem = truck.getRemainingSpace();
			
			if (spaceRem >= quantityRem) {
				// Add everything and return
				truck.loadOnTruck(item, quantityRem);
				quantityRem = 0;
			} else {
				// Add all that is possible and loop again
				truck.loadOnTruck(item, spaceRem);
				quantityRem -= spaceRem;
			}
		}
	}
	
	
	/**
	 * @return A string which is the concatenation of all of the truck toString results in the manifest
	 */
	public String toString() {
		
		String str = "";
		
		for (int i = 0; i < truckList.size(); i++) {
			

			str += truckList.get(i).toString();
		}
		
		
		return str;
		
	}
	
	
	/**
	 * Exports the manifest to a CSV document at a specified location
	 * @param file is the location where the CSV will be saved
	 */
	public void exportManifest(String file) {
		new CSVWrite(file, toString());
	}
	
	
	/**
	 * Creates an empty truck of requested type
	 * @param cooled indicates a refrigerated truck is required otherwise a truck of any type will be returned
	 * @return A truck of rquested type
	 */
	private Truck createTruck(boolean cooled) {
		
		Truck truck;
		
		if (cooled) {
			truck = new RefrigeratedTruck();
		} else {
			truck = new OrdinaryTruck();
		}
		
		return truck;
	}
}
