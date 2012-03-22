package exceptions;

public class ConditionNotMetException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ConditionNotMetException(String exception){
		super(exception);
	}
}