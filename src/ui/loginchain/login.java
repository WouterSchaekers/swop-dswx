package ui.loginchain;

import ui.UserinterfaceData;
import ui.usecase;

/**
 * In this class the user can give his name to log in. 
 * When this is done the system will proceed to the next step of the chain to see if this user can is validated to log in. 
 */
public class login extends LoginCommand
{
	/**
	 * Constructor of the class where the map with all registered users is copied to map.
	 * 		|this.map = nameUserMap;
	 */
	public login(UserinterfaceData uiData, LoginData loginData) {
		super(uiData, loginData);
	}

	/**
	 * The user enters his name, the system proceeds to the next step of the chain to see if the name entered is valid.
	 * The map of all registered users is given to the next together with the name of the current user.
	 */
	@Override
	public usecase Execute() {
		System.out.println("Enter User name:");
		String name = input.nextLine();
		loginData.setUsername(name);
		return new validateLogin(data,loginData);
	}

}
