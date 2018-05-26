package csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import item.Item;
import item.Stock;
import item.StockException;


/**
 * Reads in and parses a CSV document to import item types
 * @author Jacob Bullock
 */
public class ParseItems {
	
	CSVRead reader;
	List<String[]> result;

	
	/**
	 * Initialises the class, creates the headers list and reads the CSV file
	 * @param file indicating the location and name of the CSV file
	 * @throws IOException 
	 * @throws CSVFormatException 
	 */
	public ParseItems(String file) throws IOException, CSVFormatException {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("cost");
		headers.add("sellPrice");
		headers.add("reorderPoint");
		headers.add("reorderAmount");
		headers.add("temperature");
		
		reader = new CSVRead(file, headers);
		result = reader.getResult();
	}
	
	
	/**
	 * Loops through the data read from the CSV file while creating and adding items to the stores stock
	 * @param stock to add imported items to
	 * @throws StockException
	 * @throws CSVFormatException
	 */
	public void parseResults(Stock stock) throws StockException, CSVFormatException {
				
		for (int i = 1; i < result.size(); i++) {
			
			String name = result.get(i)[0];
			
			try {
				int cost = Integer.parseInt(result.get(i)[1]);
				int sellPrice = Integer.parseInt(result.get(i)[2]);
				int reorderPoint = Integer.parseInt(result.get(i)[3]);
				int reorderAmount = Integer.parseInt(result.get(i)[4]);
				int temperature;
				
				Item item;
	
				if (result.get(i)[5] == null) {
					item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount);
				} else {
					temperature = Integer.parseInt(result.get(i)[5]);
					item = new Item(name, cost, sellPrice, reorderPoint, reorderAmount, temperature);
				}
				
				stock.addItem(item, 0);
			} catch(NumberFormatException e) {
				throw new CSVFormatException("Invalid CSV Format");
			}
		}
	}
	
	
	/**
	 * Getter for the CSV parser results
	 * @return The result from the CSV read
	 */
	public List<String[]> getResult() {
		return result;
	}

}
