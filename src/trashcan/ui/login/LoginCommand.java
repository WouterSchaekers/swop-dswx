package ui.login;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class LoginCommand extends Usecase
{

	LoginData loginData;

	public LoginCommand(UserinterfaceData uiData) {
		super(uiData);
	}

	public LoginCommand(UserinterfaceData uiData, LoginData loginData) {
		super(uiData);
		this.loginData = loginData;
	}

}
