package controllers;

import java.util.Collection;
import users.User;
import users.UserManager;

public class LoginController
{
	private UserManager um;

	public LoginController(UserManager m) {
		this.um = m;
	}

	private User loggedIn = null;

	public Collection<User> getAllUsers() {
		return um.getAllUsers();
	}

	public void logIn(User u) {
		loggedIn = u;
	}

	public boolean loggedIn() {
		return loggedIn != null;
	}

	public User getUser() {
		return this.loggedIn;
	}
}
