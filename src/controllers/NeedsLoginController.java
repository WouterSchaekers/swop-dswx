package controllers;

import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * A class that should be extended by every controller that requires a
 * login controller to work.
 */
abstract class NeedsLoginController extends HospitalController
{
	protected LoginController loginController_;

	/**
	 * Default constructor. Initialises the logincontroller and checks if it is
	 * valid.
	 * 
	 * @param loginController
	 *            The logincontroller for the newly created controller.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller is invalid (null or has no one
	 *             logged in).
	 * @throws InvalidHospitalException
	 *             If the hospital stored in the given login contoller seems to
	 *             not be valid.
	 * @see HospitalController
	 */
	NeedsLoginController(LoginController loginController) throws InvalidLoginControllerException, InvalidHospitalException {
		super(loginController.hospital);
		if (isValidLoginController(loginController))
			this.loginController_ = loginController;
		else
			throw new InvalidLoginControllerException("This loginController is invalid.");
	}

	/**
	 * Checks if the calls that will happen on this controller in the future
	 * will be authorized: is the user that's logged in authorized to use this
	 * controller?
	 * 
	 * @return True if the given login controller has all required fields
	 *         initialised and the logged in user is authorized to use the newly
	 *         created controller.
	 */
	 boolean isValidLoginController(LoginController loginController) {
		User controllerOwner = loginController.getUser();
		if (controllerOwner == null)
			return false;
		if (!validUser(controllerOwner))
			return false;
		if (this.loginController_ == null)
			return true;
		else if (!this.loginController_.equals(loginController)) {
			return false;
		}
		return true;
	}

	/**
	 * Abstract method: check if the logged in user is authorized to execute
	 * methods in the newly created controller.
	 * 
	 * @param u
	 *            The owner of the new controller.
	 * @return True if the user is allowed to use the new controller.
	 */
	abstract boolean validUser(User u);

}
