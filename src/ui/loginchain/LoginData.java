package ui.loginchain;

import controllers.LoginController;

public class LoginData
{
	String username;
	LoginController lc;
	
	public LoginData(String username, LoginController lc) {
		this.username = username;
		this.lc = lc;
	}
	
	/**
	 * @return The username stored in this logindata object.
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @return the logincontroller stored in this logindata object.
	 */
	public LoginController getLoginController() {
		return this.lc;
	}

}
