package controllers;

import users.UserManager;

/**
 * This class manages all the managers used in this software package.
 * 
 * @author Stefaan, Dieter
 * 
 */
public class Metamanager
{
	private UserManager userManager = new UserManager();
	public Metamanager() {
		
	}

	
	public UserManager getUserManager()
	{
		return userManager;
	}
}
