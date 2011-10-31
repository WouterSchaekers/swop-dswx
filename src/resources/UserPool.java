package resources;

import java.util.Collection;
import users.User;

/**
 * This class is merely a collection of all users in existence.
 */
public class UserPool
{
	private Collection<User> allUsers;

	public void addUser(User m) {
		allUsers.add(m);
	}

	public void removeUser(User m) {
		allUsers.remove(m);
	}
	
	public Collection<User> getAllUsers() {
		return this.allUsers;
	}
}
