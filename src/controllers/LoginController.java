package controllers;

import java.util.ArrayList;
import java.util.Collection;
import system.Hospital;
import users.User;
import users.UserManager;
import controllers.interfaces.UserIN;

/**
 * This class will be used as a login controller. There will be a 1:1 relation
 * between the amount of logged in users and the amount of controllers. Each
 * logincontroller will remember what user they logged in.
 * 
 */
public class LoginController
{
	private UserManager um; // the usermanager for this controller.
	// true if the user of this controller is logged in.
	private boolean loggedIn = false;
	private User user = null; // the user this controller has logged in.

	/**
	 * Default constructor.
	 * 
	 * @param data
	 *            The datapasser for this logincontroller
	 * @throws IllegalArgumentException
	 *             if (!isValidData(data))
	 */
	public LoginController(Hospital hospitalState) throws IllegalArgumentException {
		if (!isValidData(hospitalState))
			throw new IllegalArgumentException("datapasser is invalid!");
		this.um = hospitalState.getUserManager();
	}

	/**
	 * This method checks if data is a valid datapasser for the logincontroller.
	 * 
	 * @param data
	 *            The data to check
	 * @return False if data == null || data.getUserManager() == null
	 */
	private boolean isValidData(Hospital data) {
		return !(data == null || data.getUserManager() == null);
	}

	/**
	 * @return A collection of all users currently in the system.
	 */
	public Collection<UserIN> getAllUsers() {
		ArrayList<UserIN> users = new ArrayList<UserIN>();
		for (User u : um.getAllUsers())
			users.add(u);
		return users;

	}

	/**
	 * This method will log the user of this logincontroller in.
	 * 
	 * @throws IllegalArgumentException
	 *             if the user == null
	 */
	public void logIn(UserIN user) throws IllegalArgumentException {
		if (!isValidUser((User) user))
			throw new IllegalArgumentException("The given user is null!");
		this.user = (User) user;
		loggedIn = true;
	}

	/**
	 * This method will check if the given user is a valid user for this
	 * logincontroller.
	 * 
	 * @param u
	 *            The user.
	 * @return True if u == null;
	 */
	private boolean isValidUser(User u) {
		return !(u == null);
	}

	/**
	 * @return True if the user of this controller is logged in.
	 */
	public boolean loggedIn() {
		return loggedIn;
	}

	/**
	 * @return The user of this logincontroller.
	 */
	User getUser() {
		return this.user;
	}

	/**
	 * 
	 * @return
	 */
	public UserIN getUserIN() {
		return user;
	}

	/**
	 * Equality test
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LoginController))
			return false;
		LoginController that = (LoginController) o;
		return this.loggedIn == that.loggedIn & this.user.equals(that.user);
	}

}
