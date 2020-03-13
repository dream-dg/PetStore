//exception thrown if user input is invalid
public class PetStoreException extends Exception {
	private static final long serialVersionUID = 1L;

	public PetStoreException(String message) {
		super(message);
	}
}