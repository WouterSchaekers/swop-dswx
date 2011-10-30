package controllers;

import java.util.ArrayList;
import java.util.Collection;
import users.User;
import users.UserManager;

/**
 * This class will be used as a login controller. There will be a 1:1 relation
 * between the amount of logged in users and the amount of controllers. Each
 * logincontroller will remember what user they logged in.
 */
public class LoginController
{
	private UserManager um; // the usermanager for this controller.
	private boolean loggedIn = false; // true if the user of this controller is
										// logged in.
	private USERDTO user = null; // the user this controller has logged in.
	public USERDTO getUserDTO(){
		return user;
	}
	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	@SuppressWarnings("unused")
	private LoginController() {
	} 

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
	 * @return All users in the system.
	 */
	public Collection<User> getAllUsers() {
		return um.getAllUsers();
	}
	public Collection<USERDTO>getAllUsers2(){
		Collection<USERDTO> RV = new ArrayList<USERDTO>();
		for(User u:um.getAllUsers())
			RV.add(new USERDTO(u));
		return RV;
	}
	/**
	 * This method will log the user of this logincontroller in.
	 */
	public void logIn(USERDTO user) {
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
}
