package csv;

/**
 * An exception class used for all things related to CSV parsing
 */

@SuppressWarnings("serial")
public class CSVFormatException extends Exception {

	public CSVFormatException() {
		super();
	}

	public CSVFormatException(String message) {
		super(message);
	}

}

