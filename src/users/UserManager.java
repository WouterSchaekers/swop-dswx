package users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.UserIN;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidUserFactory;
import exceptions.UserAlreadyExistsException;

/**
 * A class representing the collection of all the users in the hospital/campus.
 * 
 * *
 */
public class UserManager
{
	private Collection<User> users_;
	private UserTypeManager userTypeManager = new UserTypeManager();

	/**
	 * Default constructor.
	 * 
	 * @param name
	 * The name of the hospital administrator.
	 * @throws InvalidNameException
	 *             When the provided name is invalid for the hospitaladmin that
	 *             must be created in this usermanager
	 */
	public UserManager(String name) throws InvalidNameException {
		users_ = new ArrayList<User>();
		users_.add(new HospitalAdmin(name));
	}

	public void addType(UserFactory fact) {
		userTypeManager.add(fact);
	}

	@Basic
	public LinkedList<User> getAllUsers() {
		return new LinkedList<User>(this.users_);
	}
	
	@Basic
	public Collection<UserIN> getAllUserINs() {
		return new LinkedList<UserIN>(this.users_);
	}

	public User createUser(UserFactory factory) throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException, InvalidUserFactory {
		if(!userTypeManager.contains(factory))
			throw new InvalidUserFactory("The user of this type can not be created in this usermanager.");
		User u = factory.create();
		addUser(u);
		return u;
	}

	public Collection<UserFactory> getUserFactories() {
		return userTypeManager.types();
	}

	/**
	 * Use to add unschedulable users only!
	 */
	private void addUser(User user) throws UserAlreadyExistsException {
		if (users_.contains(user))
			throw new UserAlreadyExistsException(user.name);
		users_.add(user);
	}

}