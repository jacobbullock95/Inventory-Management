package csv;

import java.util.ArrayList;
import java.util.List;
import item.Stock;
import truck.DeliveryException;
import truck.Manifest;
import truck.Truck;

public class ParseManifest {
	
	CSVRead reader;
	Manifest manifest;
	List<String[]> result;

	public ParseManifest(String file) {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("sales");
		
		
		reader = new CSVRead(file, headers);
		result = reader.getResult();
	}
	
	public Manifest parseResults(Stock stock) throws DeliveryException {
		
		final boolean COOLED = true;
		final boolean NOT_COOLED = false;
		
		manifest = new Manifest(stock);
		Truck truck;
		
		// Ensure truck is always initialised
		if (result.get(1)[0].equals(">Refrigerated")) {
			truck = manifest.createTruck(COOLED);
		} else {
			truck = manifest.createTruck(NOT_COOLED);
		}
		
		manifest.addTruck(truck);
		
		// Import manifest CSV into manifest object
		for (int i = 2; i < result.size(); i++) {
			
			// If a new truck is being used
			if (result.get(i)[1] == null) {
				
				if (result.get(i)[0].equals(">Refrigerated")) {
					truck = manifest.createTruck(COOLED);
				} else {
					truck = manifest.createTruck(NOT_COOLED);
				}
				
				// Add new truck to manifest
				manifest.addTruck(truck);
			} else {
				// Add items under that truck to the truck
				
				String name = result.get(i)[0];
				int quantity = Integer.parseInt(result.get(i)[1]);
				
				truck.loadOnTruck(stock.getItemByName(name), quantity);
				
				// Update stock quantity
				stock.increaseQuantity(name, quantity);

			}
		}	
		
		return manifest;
	}
	
	public List<String[]> getResult() {
		return result;
	}

}