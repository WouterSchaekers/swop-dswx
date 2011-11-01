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
	 * @param m
	 *            The usermanager associated with this logincontroller.
	 */
	public LoginController(DataPasser data) {
		this.um = data.getUserManager();
	}
	
	/**
	 * @return A collection of all users currently in the system.
	 */
	public Collection<DTOUser> getAllUsers(){
		Collection<DTOUser> RV = new ArrayList<DTOUser>();
		for(User u:um.getAllUsers())
			RV.add(new DTOUser(u));
		return RV;
	}
	
	/**
	 * This method will log the user of this logincontroller in.
	 */
	public void logIn(DTOUser user) {
		this.user = user;
		loggedIn = true;
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
	public DTOUser getUserDTO(){
		return user;
	}

}
