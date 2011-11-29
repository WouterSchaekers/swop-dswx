package users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserManager
{

	private Map<String, User> users;

	public UserManager() {
		users = new HashMap<String, User>();
	}

	/**
	 * This method is used to add users to the system, ensures valid adding etc.
	 * 
	 * @param newUser
	 * @throws UserAlreadyExistsException
	 */
	private void AddUser(User newUser) throws UserAlreadyExistsException {
		if (users.containsKey(newUser) || newUser == null)
			throw new UserAlreadyExistsException(newUser.name);
		this.users.put(newUser.name, newUser);
	}

	public Doctor CreateDoctor(String name) throws UserAlreadyExistsException {
		Doctor newUser = new Doctor(name);
		AddUser(newUser);
		return newUser;
	}

	public Collection<User> getAllUsers() {
		return users.values();
	}

	public Nurse CreateNurse(String string) throws UserAlreadyExistsException {
		Nurse newUser = new Nurse(string);
		AddUser(newUser);
		return newUser;
	}

}
