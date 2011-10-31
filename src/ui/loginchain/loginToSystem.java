package ui.loginchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;
import controllers.UserDTO;

/**
 * In this class the current user will log in to the system.
 *
 */
public class loginToSystem extends usecase
{
	UserDTO user;
	public loginToSystem(UserinterfaceData data,UserDTO user) {
		super(data);
		this.user=user;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public usecase Execute() {
		data.getLoginController().logIn(user);
		System.out.println(user.getName()+" was succesfully logged in");
		return new SelectUsecase(data);
	}

}
