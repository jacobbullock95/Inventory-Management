package truck;

/* A trivial exception class for the Movie List program.
 */
@SuppressWarnings("serial")
public class ManifestException extends Exception {

	public ManifestException() {
		super();
	}

	public ManifestException(String message) {
		super(message);
	}


}
