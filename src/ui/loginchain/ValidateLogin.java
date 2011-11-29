package ui.loginchain;

import java.util.Collection;
import ui.UserinterfaceData;
import ui.Usecase;
import controllers.DTOUser;
import controllers.LoginController;

/**
 * This class will see if the name entered by the current user is allowed to log
 * in.
 */
public class ValidateLogin extends LoginCommand
{
	private LoginController lc;

	/**
	 * Default constructor.
	 * 
	 * @param uiData
	 *            The data from the userinterface (contains the username of the
	 *            user trying to log in!).
	 * @param loginData
	 *            The data from the logincontroller (contains the
	 *            logincontroller!).
	 */
	public ValidateLogin(UserinterfaceData uiData, LoginData loginData) {
		super(uiData, loginData);
		lc = uiData.getLoginController();
	}

	/**
	 * Method to find out if the map with the name of all the registered users
	 * contains the name of the current user. If not the system will return to
	 * the use case display all names, where he sees all names and later he can
	 * reenter his name. If the map contains the name of the current user, the
	 * system will proceed to the next step in the chain.
	 */
	@Override
	public Usecase Execute() {
		Collection<DTOUser> c = lc.getAllUsers();
		for (DTOUser u : c)
			if (u.getName().equalsIgnoreCase(loginData.getUsername()))
				return new LoginToSystem(data, u);
		return new DisplayAllNames(data, loginData);
	}

}
