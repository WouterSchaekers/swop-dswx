package ui.loginchain;

import ui.UserinterfaceData;
import ui.usecase;
import controllers.LoginController;

/**
 * In this class the logincontroller of the user that is allowed to log in will be created.
 */
public class createLoginController extends LoginCommand
{

	public createLoginController(UserinterfaceData uiData, LoginData loginData) {
		super(uiData,loginData);
	}
	
	/**
	 * Creation of the login controller 
	 * Returns the next step of the chain, where all the names of the system are displayed.
	 */
	@Override
	public usecase Execute() {
		data.setLoginc(new LoginController(data.getDataPasser()));
		loginData.setLoginController(data.getLoginController());
		return new displayAllNames(data, loginData);
	}

}
