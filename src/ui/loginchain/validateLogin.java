package ui.loginchain;

import java.util.Collection;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.DTOUser;
import controllers.LoginController;

/**
 * This class will see if the name entered by the current user is allowed to log in.
 */
public class validateLogin extends LoginCommand
{
	private LoginController lc;
	private String name;
	/**
	 * Default constructor.
	 * @param uiData
	 * The data from the userinterface (contains the username of the user trying to log in!).
	 * @param loginData
	 * The data from the logincontroller (contains the logincontroller!).
	 */
	public validateLogin(UserinterfaceData uiData,LoginData loginData) {
		super(uiData, loginData);
		lc = loginData.getLoginController();
	}

	/**
	 * Method to find out if the map with the name of all the registered users contains the name of the current user.
	 * If not the system will return to the use case display all names, where he sees all names and later he can reenter his name.
	 * If the map contains the name of the current user, the system will proceed to the next step in the chain. 
	 */
	@Override
	public usecase Execute() {
		Collection<DTOUser> c = lc.getAllUsers();
		for(DTOUser u: c)
			if(u.getName().equalsIgnoreCase(loginData.getUsername()))
				return new loginToSystem(data,u);
		return new displayAllNames(data,loginData);
	}

}
