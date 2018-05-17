package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * This is a simple CSV writer
 * It reads data from an ArrayList of arrays and stores it in a CSV at a specified location
 * @author Jesse Haviland
 */
public class CSVWrite {
	
	private String file;
	private String comma = ",";
	private List<String[]> data;
	
	
	/**
	 * Initialises the class. Stores the file location string and data locally
	 * Calls Write() function to write the CSV file
	 * @param file
	 * @param data
	 */
	public CSVWrite(String file, List<String[]> data) {
		this.file = file;
		this.data = data;
		
		Write();
	}
	
	
	/**
	 * The writes the data to the CSV file
	 * It uses a file writer reader to write the file line by line
	 * On each line, the provided data is combined into a comma separated String with a new line character at the end
	 */
	public void Write() {
		String line = "";
		
		try {
			FileWriter fw = new FileWriter(file);
			
			for (int i = 0; i < data.size(); i++) {
				
				line = "";
				
				for (int j = 0; j < (data.get(i).length - 1); j++) {
					line += data.get(i)[j];
					line += comma;
				}
				
				line += data.get(i)[data.get(i).length - 1];
				line += "\n";
				
				fw.write(line);
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
