package controllers;

public class superController
{

	protected LoginController lc;
	
	public superController(LoginController lc) throws IllegalArgumentException{
		if(this.validLoginController(lc))
			this.lc = lc;
		throw new IllegalArgumentException("logincontroller is invalid!");
	}
	
	public boolean validLoginController(LoginController lc) {
		return lc != null && lc.loggedIn();
	}
}
