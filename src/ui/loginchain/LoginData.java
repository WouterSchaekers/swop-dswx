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
	
	/**
	 * @return the logincontroller stored in this logindata object.
	 */
	public void setLoginController(LoginController lc) {
		this.lc = lc;
	}

	/**
	 * This method sets the name for this data.
	 * @param name
	 * The new name
	 */
	public void setUsername(String name) {
		this.username = name;
	}
}
