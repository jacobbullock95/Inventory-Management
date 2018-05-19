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
	
	public void parseResults(Stock stock) {
		
		for (int i = 1; i < result.size(); i++) {	
			String name = result.get(i)[0];
			int sales = Integer.parseInt(result.get(i)[1]);
			
			stock.reduceQuantity(name, sales);
		}
	}
	
	public List<String[]> getResult() {
		return result;
	}

}
