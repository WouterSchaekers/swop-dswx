package ui.loginchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;

/**
 * Class to check whether a person is allowed to log in.
 */

public class isAllowedToLogin extends usecase
{

	public isAllowedToLogin(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * If the person hasn't a logincontroller or when the person isn't logged in.
	 * 		Then the person is allowed to log in into the system and goes to the next step in the chain.
	 * Else 
	 * 		The person is not allowed to log in and the system will return to the beginning of the chain. 
	 */

	@Override
	public usecase Execute() {
		if(data.getLoginController()==null||!data.getLoginController().loggedIn())
			return new createLoginController(data);
		return new SelectUsecase(data);
	}

}
