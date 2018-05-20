package csv;

import java.util.ArrayList;
import java.util.List;
import item.Stock;

public class ParseSales {
	
	CSVRead reader;
	List<String[]> result;

	public ParseSales(String file) {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("sales");
		
		reader = new CSVRead(file, headers);
		result = reader.getResult();
	}
	
	public double parseResults(Stock stock) {
		
		double profit = 0;
		
		for (int i = 1; i < result.size(); i++) {	
			String name = result.get(i)[0];
			int sales = Integer.parseInt(result.get(i)[1]);
			
			profit += (stock.getItemByName(name).getSellPrice() * sales);
			
			stock.reduceQuantity(name, sales);
		}
		
		return profit;	
		
	}
	
	public List<String[]> getResult() {
		return result;
	}

}
