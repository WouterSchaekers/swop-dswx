package exceptions;

public class InvalidCategoryNameException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCategoryNameException(String args){
		super(args);
	}
}