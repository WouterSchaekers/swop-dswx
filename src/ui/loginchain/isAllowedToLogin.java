package ui.loginchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;

/**
 * Class to check whether a person is allowed to log in.
 */

public class isAllowedToLogin extends LoginCommand
{

	public isAllowedToLogin(UserinterfaceData uiData, LoginData loginData) {
		super(uiData,loginData);
	}
	
	/**
	 * If the person hasn't got a logincontroller or when the person isn't logged in.
	 * 		Then the person is allowed to log in into the system and goes to the next step in the chain.
	 * Else 
	 * 		The person is not allowed to log in and the system will return to the beginning of the chain. 
	 */
	@Override
	public usecase Execute() {
		if(data.getLoginController()==null)
			return new createLoginController(data, loginData);
		return new SelectUsecase(data);
	}

}
