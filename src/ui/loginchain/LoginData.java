package ui.loginchain;

public class LoginData
{
	String username;
	
	public LoginData(String username) {
		this.username = username;
	}
	
	/**
	 * @return The username stored in this logindata object.
	 */
	public String getUsername() {
		return this.username;
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
