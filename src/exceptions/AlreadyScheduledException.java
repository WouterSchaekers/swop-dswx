package exceptions;

public class AlreadyScheduledException extends Exception
{
	private static final long serialVersionUID = 7719808395573292001L;
	
	public AlreadyScheduledException(String exception){
		super(exception);
	}
}