package exceptions;

public class InvalidSchedulerException extends Exception
{
	public InvalidSchedulerException() {
	}

	public InvalidSchedulerException(String arg0) {
		super(arg0);
	}

	public InvalidSchedulerException(Throwable arg0) {
		super(arg0);
	}

	public InvalidSchedulerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}