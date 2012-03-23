package users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.UserIN;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

public class UserManager
{

	private Collection<User> users_;
	private UserTypeManager userTypeManager = new UserTypeManager();

	/**
	 * Default constructor.
	 */
	public UserManager() {
		users_ = new ArrayList<User>();
	}

	public void addType(UserFactory fact) {
		userTypeManager.add(fact);
	}

	@Basic
	public LinkedList<UserIN> getAllUsers() {
		return new LinkedList<UserIN>(this.users_);
	}

	public void createUser(UserFactory factory)
			throws UserAlreadyExistsException, InvalidNameException {
		addUser(factory.create());
	}

	public Collection<UserFactory> getUserFacotories() {
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