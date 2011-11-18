package exceptions;

public class InvalidTaskException extends Exception
{
	public InvalidTaskException() {
	}

	public InvalidTaskException(String arg0) {
		super(arg0);
	}

	public InvalidTaskException(Throwable arg0) {
		super(arg0);
	}

	public InvalidTaskException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
