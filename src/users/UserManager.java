package users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import controllers.Manager;

public class UserManager implements Manager
{

	private Map<String, User> users;
	private Map<User, Boolean> loggedin;

	/**
	 * This method is used to add users, ensures valid adding etc.
	 * 
	 * @param newUser
	 * @throws UserAlreadyExistsException
	 */
	private void AddUser(User newUser) throws UserAlreadyExistsException {
		if (users.containsKey(newUser))
			throw new UserAlreadyExistsException(newUser.name);
		this.users.put(newUser.name, newUser);
	}

	public Doctor CreateDoctor(String name) throws UserAlreadyExistsException {
		Doctor newUser = new Doctor(name);
		AddUser(newUser);
		return newUser;
	}

	public void DeleteUser(User u) {
		this.users.remove(u.name);
	}

	public UserManager() {
		users = new HashMap<String, User>();
	}

	public Collection<User> getAllusers() {
		return users.values();
	}

	public boolean loggedIn(User u) {
		return loggedIn(u);
	}

	public void login(String name) {
		//XXX:test if existsa
		loggedin.put(users.get(name), true);
	}
	public void logout(User u)
	{
		loggedin.put(u, false);
	}
	public User getUser(String name) {
		return users.get(name);

	}

}
