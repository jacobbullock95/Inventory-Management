// Jesse

package truck;

import java.util.ArrayList;

import csv.CSVWrite;
import item.Item;
import item.Stock;


public class Manifest {
	
	private ArrayList<Truck> truckList;
	private Stock stock;
	private ArrayList<Item> list;
	
	public Manifest() {
		
		truckList = new ArrayList<Truck>();
		list = new ArrayList<Item>();
	}
	
	public Manifest(Stock stock) {
		
		this.stock = stock;
		truckList = new ArrayList<Truck>();
		list = new ArrayList<Item>();
	}
	
	public void addTruck(Truck truck) {
		truckList.add(truck);
	}
	
	public int totalTrucks() {
		return truckList.size();
	}
	
	public double getTotalCost() {
		double cost = 0;
		
		// Get truck cost
		for (int i = 0; i < truckList.size(); i++) {
			cost += truckList.get(i).getCost();
		}
		
		return cost;
		
	}
	
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
	
	public void loadTrucks(boolean cooled, Item item) throws DeliveryException {
		
		int quantityRem = item.getReorderAmount();
		Truck truck;
		int spaceRem;
		
		while (quantityRem > 0) {
			truck = getNonFullTruck(cooled);
			spaceRem = truck.getRemainingSpace();
			
			if (spaceRem >= quantityRem) {
				truck.loadOnTruck(item, quantityRem);
				quantityRem = 0;
			} else {
				truck.loadOnTruck(item, spaceRem);
				quantityRem -= spaceRem;
			}
		}
	}
	
	public Truck createTruck(boolean cooled) {
		
		Truck truck;
		
		if (cooled) {
			truck = new RefrigeratedTruck();
		} else {
			truck = new OrdinaryTruck();
		}
		
		return truck;
		
	}
	
	public void CalculateManifest() throws DeliveryException {
		
		for (int i = 0; i < stock.uniqueItems(); i++) {
			
			Item currentItem = stock.getItemByIndex(i);
			String currentItemName = currentItem.getName();
			
			if (stock.currentQuantity(currentItemName) < currentItem.getReorderPoint()) {
				list.add(currentItem);
			}
		}
		
		OptimiseManifest();
		
	}
	
	public void OptimiseManifest() throws DeliveryException {
		final int MAX_REF_TEMP = 10;
		final boolean COOLED = true;
		final boolean NOT_COOLED = false;
		
		int itemsRemaining = list.size();
		int minTemp = 100;
		int index = 0;
		
		
		
		while (itemsRemaining > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				if (list.get(i).getRequiresTemperature()) {
					if (list.get(i).getTemperature() < minTemp) {
						minTemp = list.get(i).getTemperature();
						index = i;
					}
				}
				
			}
			
			if (minTemp <= MAX_REF_TEMP) {
				loadTrucks(COOLED, list.get(index));
			} else {
				loadTrucks(NOT_COOLED, list.get(index));
			}
			
			list.remove(list.get(index));
			minTemp = 100;
			index = 0;
			itemsRemaining--;
		}
		
	}
	
	public String toString() {
		
		String str = "";
		
		for (int i = 0; i < truckList.size(); i++) {
			

			str += truckList.get(i).toString();
		}
		
		
		return str;
		
	}
	
	public void exportManifest(String file) {
		CSVWrite writer = new CSVWrite(file, toString());
	}
	
	

}
