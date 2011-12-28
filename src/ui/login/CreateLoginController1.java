package ui.login;

import ui.UserinterfaceData;
import ui.Usecase;
import controllers.LoginController;

/**
 * In this class the logincontroller of the user that is allowed to log in will
 * be created.
 */
public class CreateLoginController1 extends LoginCommand
{

	public CreateLoginController1(UserinterfaceData uiData, LoginData loginData) {
		super(uiData, loginData);
	}

	/**
	 * Creation of the login controller Returns the next step of the chain,
	 * where all the names of the system are displayed.
	 */
	@Override
	public Usecase Execute() {
		data.setLoginc(new LoginController(data.getDataPasser()));
		return new DisplayAllNames(data, loginData);
	}

}
