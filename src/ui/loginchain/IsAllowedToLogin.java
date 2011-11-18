package ui.loginchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.Usecase;

/**
 * Class to check whether a person is allowed to log in.
 */
public class IsAllowedToLogin extends LoginCommand
{
	public IsAllowedToLogin(UserinterfaceData uiData) {
		super(uiData,new LoginData(""));
	}
	
	/**
	 * If the person hasn't got a logincontroller or when the person isn't logged in.
	 * 		Then the person is allowed to log in into the system and goes to the next step in the chain.
	 * Else 
	 * 		The person is not allowed to log in and the system will return to the beginning of the chain. 
	 */
	@Override
	public Usecase Execute() {
		if(data.getLoginController()==null)
			return new CreateLoginController1(data, loginData);
		return new SelectUsecase(data);
	}

}
