package users;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import system.Whereabouts;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.UserIN;
import exceptions.UserAlreadyExistsException;

public class UserManager
{

	private Map<User, Whereabouts> _users;

	/**
	 * Default constructor.
	 */
	public UserManager() {
		_users = new HashMap<User, Whereabouts>();
	}

	@Basic
	protected LinkedList<User> getAllUsers() {
		return new LinkedList<User>(this._users.keySet());
	}

	@Basic
	public LinkedList<UserIN> getAllUserINs() {
		return new LinkedList<UserIN>(this._users.keySet());
	}

	public void addUser(User user, Whereabouts whereabouts)
			throws UserAlreadyExistsException {
		if (_users.keySet().contains(user))
			throw new UserAlreadyExistsException(user.name);
		_users.put(user, whereabouts);
	}

}