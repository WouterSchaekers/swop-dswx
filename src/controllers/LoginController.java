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
	private UserDTO user = null; // the user this controller has logged in.
	public UserDTO getUserDTO(){
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
	public Collection<UserDTO>getAllUsers2(){
		Collection<UserDTO> RV = new ArrayList<UserDTO>();
		for(User u:um.getAllUsers())
			RV.add(new UserDTO(u));
		return RV;
	}
	/**
	 * This method will log the user of this logincontroller in.
	 */
	public void logIn(UserDTO user) {
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
