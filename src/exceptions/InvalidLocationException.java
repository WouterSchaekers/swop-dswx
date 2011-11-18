package exceptions;

public class InvalidLocationException extends Exception
{
	public InvalidLocationException() {
	}

	public InvalidLocationException(String arg0) {
		super(arg0);
	}

	public InvalidLocationException(Throwable arg0) {
		super(arg0);
	}

	public InvalidLocationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}