package exceptions;

public class InvalidSystemTimeException extends Exception
{
	private static final long serialVersionUID = -6770022887272574246L;

	public InvalidSystemTimeException(String string) {
		super(string);
	}
}