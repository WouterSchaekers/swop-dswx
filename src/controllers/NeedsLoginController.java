package controllers;

import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public abstract class NeedsLoginController extends HospitalController
{

	protected LoginController loginController;

	public NeedsLoginController(LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(controller.hospital);
		if (isValidLoginController(controller))
			this.loginController = controller;
		else
			throw new InvalidLoginControllerException("This loginController is invalid.");
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
		if (!validUser(i))
			return false;
		if (this.loginController == null)
			return true;
		else if (!this.loginController.equals(loginController)) {
			return false;
		}
		return true;
	}

	abstract boolean validUser(User u);

}
