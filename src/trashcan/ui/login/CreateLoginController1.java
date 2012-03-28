package ui.login;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
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
		try {
			data.setLoginc(new LoginController(data.getDataPasser()));
			return new DisplayAllNames(data, loginData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
