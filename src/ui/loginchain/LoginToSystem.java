package ui.loginchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.Usecase;
import controllers.DTOUser;

/**
 * In this class the current user will log in to the system.
 *
 */
public class LoginToSystem extends LoginCommand
{
	DTOUser user;
	public LoginToSystem(UserinterfaceData data,DTOUser user) {
		super(data);
		this.user=user;
	}

	/**
	 * 
	 */
	@Override
	public Usecase Execute() {
		data.getLoginController().logIn(user);
		System.out.println(user.getName()+" was succesfully logged in");
		return new SelectUsecase(data);
	}

}
