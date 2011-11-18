package exceptions;

public class InvalidSerialException extends Exception
{
	public InvalidSerialException() {
	}

	public InvalidSerialException(String arg0) {
		super(arg0);
	}

	public InvalidSerialException(Throwable arg0) {
		super(arg0);
	}

	public InvalidSerialException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}