package truck;

/* A trivial exception class for the Movie List program.
 */
@SuppressWarnings("serial")
public class OrdinaryTruckException extends Exception {

	public OrdinaryTruckException() {
		super();
	}

	public OrdinaryTruckException(String message) {
		super(message);
	}


}
