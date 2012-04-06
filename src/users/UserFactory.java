package users;

import controllers.interfaces.LocationIN;
import controllers.interfaces.UserFactoryIN;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;

/**
 * Class that represents a type of user in the system
 */
public abstract class UserFactory implements UserFactoryIN
{
	/**
	 * Abstract method that is package only so that the only way User objects
	 * can be created is by giving a userfactory to the usermanager.
	 * 
	 * @throws InvalidLocationException
	 */
	abstract User create() throws InvalidNameException, InvalidLocationException;

	/**
	 * Used by the UserTypeManager to make sure that all the factories it returs
	 * have no initialization
	 * 
	 * @return
	 */
	abstract UserFactory newInstance();

	@users.UserAPI
	public abstract void setName(String name);

	@users.UserAPI
	public abstract void setLocation(LocationIN name);
}
