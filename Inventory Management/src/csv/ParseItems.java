package csv;

import java.util.ArrayList;
import java.util.List;

import item.Item;
import item.Stock;

public class ParseItems {
	
	CSVRead reader;
	List<String[]> result;
//	ArrayList<Item> itemList;

	public ParseItems(String file) {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("cost");
		headers.add("sellPrice");
		headers.add("reorderPoint");
		headers.add("reorderAmount");
		headers.add("temperature");
		
		reader = new CSVRead(file, headers);
		result = reader.getResult();
//		itemList = new ArrayList<Item>();
//		parseResults();
	}
	
	public void parseResults(Stock stock) {
		
		for (int i = 1; i < result.size(); i++) {
			
			String name = result.get(i)[0];
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
			
			//itemList.add(item);
		}
	}
	
	public List<String[]> getResult() {
		return result;
	}

}
