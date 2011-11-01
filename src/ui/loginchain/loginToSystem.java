package ui.loginchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;
import controllers.DTOUser;

/**
 * In this class the current user will log in to the system.
 *
 */
public class loginToSystem extends LoginCommand
{
	DTOUser user;
	public loginToSystem(UserinterfaceData data,DTOUser user) {
		super(data);
		this.user=user;
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method will log in the current user in to the system.
	 */
	@Override
	public usecase Execute() {
		data.getLoginController().logIn(user);
		System.out.println(user.getName()+" was succesfully logged in");
		return new SelectUsecase(data);
	}

}
