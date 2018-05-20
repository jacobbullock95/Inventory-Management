package csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import item.Stock;


/**
 * Reads in and parses a CSV document to adjust store profit and stock levels
 * @author 
 */
public class ParseSales {
	
	CSVRead reader;
	List<String[]> result;

	
	/**
	 * Initialises the class, creates the headers list and reads the CSV file
	 * @param file indicating the location and name of the CSV file
	 * @throws IOException
	 * @throws CSVFormatException 
	 */
	public ParseSales(String file) throws IOException, CSVFormatException {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("sales");
		
		reader = new CSVRead(file, headers);
		result = reader.getResult();
	}
	
	
	/**
	 * Loops through the data read from the CSV file while counting store profit and adjusting store stock levels
	 * @param stock whose stock levels are being reduced
	 * @return The total profit from the sales
	 * @throws CSVFormatException 
	 */
	public double parseResults(Stock stock) throws CSVFormatException {
		
		double profit = 0;
		
		for (int i = 1; i < result.size(); i++) {	
			String name = result.get(i)[0];
			
			try {
				int sales = Integer.parseInt(result.get(i)[1]);
				profit += (stock.getItemByName(name).getSellPrice() * sales);
				stock.reduceQuantity(name, sales);
			} catch(NumberFormatException e) {
				throw new CSVFormatException("Invalid CSV Format");
			}
		}
		
		return profit;	
	}
	
	
	/**
	 * @return The result from the CSV read
	 */
	public List<String[]> getResult() {
		return result;
	}

}
