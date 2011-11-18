package exceptions;

public class InvalidResourceException extends Exception
{
	public InvalidResourceException() {
	}

	public InvalidResourceException(String arg0) {
		super(arg0);
	}

	public InvalidResourceException(Throwable arg0) {
		super(arg0);
	}

	public InvalidResourceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
