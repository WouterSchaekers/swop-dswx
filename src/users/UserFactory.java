package users;

import controllers.interfaces.UserFactoryIN;
import exceptions.InvalidNameException;
/**
 * Class that represents a type of user in the system
 */
public abstract class UserFactory implements UserFactoryIN
{
	/**
	 * Abstract method that is package only so that the only way User objects can be created is by 
	 * giving a userfactory to the usermanager.
	 */
	abstract User create() throws InvalidNameException;
	/**
	 * Used by the UserTypeManager to make sure that all the factories it returs have no
	 * initialization
	 * @return
	 */
	abstract UserFactory newInstance();
}
