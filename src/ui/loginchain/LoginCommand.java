package ui.loginchain;

import ui.UserinterfaceData;
import ui.usecase;

public abstract class LoginCommand extends usecase
{
	
	UserinterfaceData loginData;
	
	public LoginCommand(UserinterfaceData uiData) {
		super(uiData);
	}
	
	public LoginCommand(UserinterfaceData uiData, UserinterfaceData loginData) {
		super(uiData);
		this.loginData = loginData;
	}

}
