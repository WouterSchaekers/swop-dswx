package users;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import system.Location;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.UserIN;
import exceptions.UserAlreadyExistsException;

public class UserManager
{

	private Map<User, Location> _users;

	/**
	 * Default constructor.
	 */
	public UserManager() {
		_users = new HashMap<User, Location>();
	}

	@Basic
	public LinkedList<UserIN> getAllUsers() {
		return new LinkedList<UserIN>(this._users.keySet());
	}

	/**
	 * Use to add schedulable users only!
	 */
	public void addUser(SchedulableUser user) throws UserAlreadyExistsException {
		if (_users.keySet().contains(user))
			throw new UserAlreadyExistsException(user.name);
		_users.put(user, ((SchedulableUser) user).getLocation());
	}

	/**
	 * Use to add unschedulable users only!
	 */
	public void addUser(User user, Location location)
			throws UserAlreadyExistsException {
		if (_users.containsKey(user))
			throw new UserAlreadyExistsException(user.name);
		_users.put(user, location);
	}

}