package controllers;

import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

 abstract class NeedsLoginController extends HospitalController
{

	protected LoginController lc;

	 NeedsLoginController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc.hospital);
		if (isValidLoginController(lc))
			this.lc = lc;
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
	public boolean isValidLoginController(LoginController loginController) {
		User i = loginController.getUser();
		if (i == null)
			return false;
		if (!validUser(i))
			return false;
		if (this.lc == null)
			return true;
		else if (!this.lc.equals(loginController)) {
			return false;
		}
		return true;
	}

	abstract boolean validUser(User u);

}
