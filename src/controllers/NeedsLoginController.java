package controllers;

import users.User;
import exceptions.InvalidLoginControllerException;

public abstract class NeedsLoginController
{

	protected LoginController loginControler;
	public NeedsLoginController(LoginController controller) throws InvalidLoginControllerException
	{
		if(isValidLoginController(controller))
			this.loginControler=controller;
		else
			throw new InvalidLoginControllerException("");
	}
	/**
	 * Checks if the provided logingcontroller provides sufficient rights to
	 * create/use this hospitalEquipementController
	 * 
	 * @param loginController
	 * @return
	 */
	protected boolean isValidLoginController(LoginController loginController) {
		User i = loginController.getUser();
		if (i == null)
			return false;
		if(!validUser(i))
			return false;
		if (this.loginControler == null)
			return true;
		else if (!this.loginControler.equals(loginController)) {
			return false;
		}
		return true;
	}
	abstract boolean validUser(User u);
	
}
