package item;

/* A trivial exception class for the Movie List program.
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