package exceptions;

public class AlreadyScheduledException extends Exception
{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyScheduledException(String exception){
		super(exception);
	}
}