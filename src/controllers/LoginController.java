package controllers;

import java.util.ArrayList;
import java.util.Collection;
import users.User;
import users.UserManager;

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
	private DTOUser user = null; // the user this controller has logged in.

	/**
	 * Default constructor.
	 * 
	 * @param data
	 *            The datapasser for this logincontroller
	 * @throws IllegalArgumentException
	 *             if (!isValidData(data))
	 */
	public LoginController(DataPasser data) throws IllegalArgumentException {
		if (!isValidData(data))
			throw new IllegalArgumentException("datapasser is invalid!");
		this.um = data.getUserManager();
	}

	/**
	 * This method checks if data is a valid datapasser for the logincontroller.
	 * 
	 * @param data
	 *            The data to check
	 * @return False if data == null || data.getUserManager() == null
	 */
	private boolean isValidData(DataPasser data) {
		return !(data == null || data.getUserManager() == null);
	}

	/**
	 * @return A collection of all users currently in the system.
	 */
	public Collection<DTOUser> getAllUsers() {
		Collection<DTOUser> RV = new ArrayList<DTOUser>();
		for (User u : um.getAllUsers())
			RV.add(new DTOUser(u));
		return RV;
	}

	/**
	 * This method will log the user of this logincontroller in.
	 * 
	 * @throws IllegalArgumentException
	 *             if the user == null
	 */
	public void logIn(DTOUser user) throws IllegalArgumentException {
		if (!isValidUser(user))
			throw new IllegalArgumentException("The given user is null!");
		this.user = user;
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
	private boolean isValidUser(DTOUser u) {
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
		return this.user.getUser();
	}

	/**
	 * 
	 * @return
	 */
	public DTOUser getUserDTO() {
		return user;
	}

}
