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
	private List<String[]> arrayData;
	private String data;
	
	
	/**
	 * Initialises the class. Stores the file location string and data locally
	 * Calls Write() function to write the CSV file
	 * @param file indicating the location and name of file to be created
	 * @param data of CSV in 2D array format
	 * @throws IOException 
	 */
	public CSVWrite(String file, List<String[]> data) throws IOException {
		this.file = file;
		this.arrayData = data;
		Write();
	}
	
	
	/**
	 * Initialises the class. Stores the file location string and data locally
	 * Calls WriteString() function to write the CSV file
	 * @param file file indicating the location and name of file to be created
	 * @param data of CSV in a pre formatted string
	 * @throws IOException 
	 */
	public CSVWrite(String file, String data) throws IOException {
		this.file = file;
		this.data = data;
		WriteString();
	}
	
	
	/**
	 * The writes the data to the CSV file
	 * It uses a file writer reader to write the file line by line
	 * On each line, the provided data is combined into a comma separated String with a new line character at the end
	 * @throws IOException 
	 */
	public void Write() throws IOException {
		String line = "";

		FileWriter fw = new FileWriter(file);
		
		for (int i = 0; i < arrayData.size(); i++) {
			
			line = "";
			
			for (int j = 0; j < (arrayData.get(i).length - 1); j++) {
				line += arrayData.get(i)[j];
				line += comma;
			}
			
			line += arrayData.get(i)[arrayData.get(i).length - 1];
			line += "\n";
			
			fw.write(line);
		}
		
		fw.close();
	}
	
	
	/**
	 * The writes the data to the CSV file
	 * It uses a file writer reader to write the file
	 * This function assumes the string is already in comma seperated format
	 * @throws IOException 
	 */
	public void WriteString() throws IOException {
		FileWriter fw = new FileWriter(file);
		fw.write(data);
		fw.close();
	}

}
