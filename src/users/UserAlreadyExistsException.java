package users;

public class UserAlreadyExistsException extends Exception
{
	String name;

	public UserAlreadyExistsException(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
