package exceptions;

public class IsNotReadyException extends Exception
{
	private static final long serialVersionUID = 1L;

	public IsNotReadyException(String exception){
		super(exception);
	}
}