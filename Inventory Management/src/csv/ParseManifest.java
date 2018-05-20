package csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import item.Stock;
import truck.DeliveryException;
import truck.Manifest;
import truck.Truck;


/**
 * Reads in and parses a CSV document into a Manifest object
 * @author Jesse Haviland
 */
public class ParseManifest {
	
	CSVRead reader;
	Manifest manifest;
	List<String[]> result;

	
	/**
	 * Initialises the class, creates the headers list and reads the CSV file
	 * @param file indicating the location and name of the CSV file
	 * @throws IOException
	 */
	public ParseManifest(String file) throws IOException {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("sales");
		
		reader = new CSVRead(file, headers);
		result = reader.getResult();
	}
	
	
	/**
	 * Loops through the data read from the CSV file and adds it into a manfest object
	 * @param stock to add items to from the trucks cargo
	 * @return The completed manifest
	 * @throws DeliveryException
	 * @throws CSVFormatException
	 */
	public Manifest parseResults(Stock stock) throws DeliveryException, CSVFormatException {
		
		final boolean COOLED = true;
		final boolean NOT_COOLED = false;
		
		manifest = new Manifest(stock);
		Truck truck;
		
		if (result.size() < 3) {
			// There is no more data here
			return manifest;
		}
		
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
				} else if (result.get(i)[0].equals(">Ordinary")){
					truck = manifest.createTruck(NOT_COOLED);
				} else {
					throw new CSVFormatException("Invalid CSV Format");
				}
				
				// Add new truck to manifest
				manifest.addTruck(truck);
			} else {
				// Add items under that truck to the truck
				
				String name = result.get(i)[0];
				
				try {
					int quantity = Integer.parseInt(result.get(i)[1]);
					truck.loadOnTruck(stock.getItemByName(name), quantity);
					stock.increaseQuantity(name, quantity);
				} catch (NumberFormatException e) {
					throw new CSVFormatException("Invalid CSV Format");
				}
			}
		}	
		
		return manifest;
	}
	
	
	/**
	 * @return The result from the CSV read
	 */
	public List<String[]> getResult() {
		return result;
	}

}