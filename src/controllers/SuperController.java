package controllers;

/**
 * This is the supercontroller for the checkin- and medicaltestcontroller.
 */
public class SuperController
{

	protected LoginController lc; // the logincontroller for this supercontroller and it's children.

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller to be associated with this superclass and
	 *            it's children.
	 * @throws IllegalArgumentException
	 *             if the logincontroller is invalid.
	 */
	public SuperController(LoginController lc) throws IllegalArgumentException {
		if (this.validLoginController(lc))
			this.lc = lc;
		else 
			throw new IllegalArgumentException("Logincontroller is invalid!");
	}

	/**
	 * This method will check if a logincontroller is a valid logincontroller
	 * for this class and it's children.
	 * 
	 * @param lc
	 *            The logincontroller to check.
	 * @return True if lc is a valid logincontroller.
	 */
	public boolean validLoginController(LoginController lc) {
		return lc != null && lc.loggedIn();
	}
}
