package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This is a simple CSV reader
 * It reads a CSV file and stores the result into an ArrayList of Arrays
 * @author Jesse Haviland
 */
public class CSVRead {
	
	private String file;
	private String comma = ",";
	private List<String> headers;
	private List<String[]> result;
	
	
	/**
	 * Initialises the class. Stores the file location string and headers locally
	 * Calls Read() function to read the CSV file
	 * @param file This is the location and name of the CSV file stored in a string
	 * @param headers These are the header names of the CSV file stored in an ArrayList of Strings
	 * @throws IOException 
	 * @throws CSVFormatException 
	 */
	public CSVRead(String file, List<String> headers) throws IOException, CSVFormatException {
		this.file = file;
		this.headers = headers;
		
		Read();
	}
	
	
	/**
	 * The reads the data from the CSV file
	 * It uses a buffered reader to read the file line by line
	 * On each line, the String is split into sections at comma locations
	 * These are then stored in a new row in the ArrayList
	 * @return Returns the data read from the CSV file in an ArrayList of Array of String
	 * @throws IOException 
	 * @throws CSVFormatException 
	 */
	public List<String[]> Read() throws IOException, CSVFormatException {
		String line;
		int size = headers.size();
		int csvLength = 0;
		
		// Initialise output array
		result = new ArrayList<String[]>();	
		
		// Add header strings to output
		result.add(new String[size]);
		for (int i = 0; i < size; i++) {
			result.get(0)[i] = headers.get(i);
		}
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		while((line = br.readLine()) != null) {
			
			String[] splitLine = line.split(comma);				
			
			// Increment CSV length
			csvLength++;
			
			// Add CSV data to ArrayList
			try {
				result.add(new String[size]);
				for (int i = 0; i < splitLine.length; i++) {
					result.get(csvLength)[i] = splitLine[i];
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				br.close();
				throw new CSVFormatException("Invalid CSV Format");
			}
		}
		
		br.close();

		
		return result;
	}
	
	
	/**
	 * Getter for result variable
	 * @return The data extracted from the CSV
	 */
	public List<String[]> getResult() {
		return result;
	}
	
	
	/**
	 * Setter for the file variable
	 * @param file This is the location and name of the CSV file stored in a string
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	
	/**
	 * Setter for the headers variable
	 * @param headers These are the header names of the CSV file stored in an ArrayList of Strings
	 */
	public void setHeaders(List<String> headers) {
		this.headers = headers;;
	}

}
