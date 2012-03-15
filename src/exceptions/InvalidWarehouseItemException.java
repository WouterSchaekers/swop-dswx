package exceptions;

public class InvalidWarehouseItemException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidWarehouseItemException(String message) {
		super(message);
	}
}
