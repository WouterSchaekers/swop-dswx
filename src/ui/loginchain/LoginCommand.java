package ui.loginchain;

import ui.UserinterfaceData;
import ui.usecase;

public abstract class LoginCommand extends usecase
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
