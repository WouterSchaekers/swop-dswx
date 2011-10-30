package ui.loginchain;

import ui.DataBlob;
import ui.SelectUsecase;
import ui.usecase;
import controllers.UserDTO;

public class loginToSystem extends usecase
{
	UserDTO user;
	public loginToSystem(DataBlob data,UserDTO user) {
		super(data);
		this.user=user;
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		data.getLoginController().logIn(user);
		System.out.println(user.getName()+" was succesfully logged in");
		return new SelectUsecase(data);
	}

}
