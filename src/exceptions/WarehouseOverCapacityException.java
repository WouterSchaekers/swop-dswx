package exceptions;

public class WarehouseOverCapacityException extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WarehouseOverCapacityException(String string) {
		super(string);
	}
}
