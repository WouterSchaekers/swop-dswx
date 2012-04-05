package ui.usecases;

import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import controllers.interfaces.UserIN;

public class Logout extends UseCase
{

	public Logout(UIData data) throws Exception {
		super(data, Integer.MAX_VALUE-1);
		if(data.getLoginController()==null)
			throw new Exception();
	}

	@Override
	public UseCase execute() {
		System.out.println();
		UserIN user =data.getLoginController().getUserIN();
		data.clear();
		printLn(user.getTypeIN().toTitle()+user.getName()+" was succesfully logged out");
		return new MainMenu(data);
	}
	@Override
	public String toString()
	{
		return "Logout";
	}
}
