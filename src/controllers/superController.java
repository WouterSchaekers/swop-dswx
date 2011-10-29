package controllers;

/**
 * This is the supercontroller for the checkin- and medicaltestcontroller. 
 */
public class superController
{

	protected LoginController lc; // the logincontroller for this supercontroller and it's children.
	
	/**
	 * Use of empty consturctor is <B>NOT</B> allowed!
	 */
	@SuppressWarnings("unused")
	private superController(){}
	
	/**
	 * Default constructor.
	 * @param lc
	 * The logincontroller to be associated with this superclass and it's children.
	 * @throws IllegalArgumentException
	 * if the logincontroller is invalid.
	 */
	public superController(LoginController lc) throws IllegalArgumentException{
		if(this.validLoginController(lc))
			this.lc = lc;
		throw new IllegalArgumentException("Logincontroller is invalid!");
	}
	
	/**
	 * This method will check if a logincontroller is a valid logincontroller for this class and it's children.
	 * @param lc
	 * The logincontroller to check.
	 * @return True if lc is a valid logincontroller.
	 */
	public boolean validLoginController(LoginController lc) {
		return lc != null && lc.loggedIn();
	}
}
