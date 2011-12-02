package exceptions;

public class DepotOverCapacityException extends Exception
{
	private static final long serialVersionUID = 1L;

	public DepotOverCapacityException(String string) {
		super(string);
	}
}
