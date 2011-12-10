package exceptions;

public class InvalidSchedulingRequestException extends Exception
{

	private static final long serialVersionUID = 1L;

	public InvalidSchedulingRequestException(String string) {
		super(string);
	}

}
