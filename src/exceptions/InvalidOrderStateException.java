package exceptions;

public class InvalidOrderStateException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOrderStateException(String message) {
		super(message);
	}
}
