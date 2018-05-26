package truck;

/**
 * An exception class used for all things related to the truck/manifest class
 */
@SuppressWarnings("serial")
public class DeliveryException extends Exception {

	public DeliveryException() {
		super();
	}

	public DeliveryException(String message) {
		super(message);
	}


}
