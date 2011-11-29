package ui.loginchain;

import controllers.interfaces.UserIN;
import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.Usecase;

/**
 * In this class the current user will log in to the system.
 * 
 */
public class LoginToSystem extends LoginCommand
{
	UserIN user;

	public LoginToSystem(UserinterfaceData data, UserIN user) {
		super(data);
		this.user = user;
	}

	/**
	 * 
	 */
	@Override
	public Usecase Execute() {
		data.getLoginController().logIn(user);
		System.out.println(user.getName() + " was succesfully logged in");
		return new SelectUsecase(data);
	}

}
