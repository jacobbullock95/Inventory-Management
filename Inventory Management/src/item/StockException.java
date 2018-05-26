package item;

/**
 * An exception class used for all things related to the stock class
 */

@SuppressWarnings("serial")
public class StockException extends Exception {

	public StockException() {
		super();
	}

	public StockException(String message) {
		super(message);
	}


}