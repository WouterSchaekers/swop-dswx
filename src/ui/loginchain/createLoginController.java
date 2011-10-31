package ui.loginchain;

import ui.UserinterfaceData;
import ui.usecase;
import controllers.LoginController;

/**
 * In this class the logincontroller of the user that is allowed to log in will be created.
 *
 */
public class createLoginController extends usecase
{

	public createLoginController(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creation of the login controller 
	 * Returns the next step of the chain, where all the names of the system are displayed.
	 */

	@Override
	public usecase Execute() {
			data.setLoginc(new LoginController(data.getDataPasser()));
		return new displayAllNames(data);
	}

}
