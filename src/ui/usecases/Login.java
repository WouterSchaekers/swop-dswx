package ui.usecases;

import controllers.LoginController;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;

public class Login extends UseCase
{

	private LoginController loginc;

	public Login(UIData data) throws InvalidHospitalException {
		super(data);
		this.loginc = new LoginController(data.hospital());
	}

	public static final Selector.Displayer<UserIN> userDisplayer = new Selector.Displayer<UserIN>()
	{

		@Override
		public void display(UserIN t) {
			System.out.println("" + t.getTypeIN().toTitle() + ": " + t.getName() + ".");

		}

	};

	@Override
	public UseCase execute() {
		printLn("Login screen v1.0:");
		printLn("These are the available users:");
		Selector<UserIN> selektor = new Selector<UserIN>(loginc.getAllUsers(), userDisplayer);
		UserIN selected = selektor.get();
		if(selected==null)
		{
			System.out.println("No user was logged in.");
			return new MainMenu(data);
		}
		userDisplayer.display(selected);
		printLn("Was sucesfully logged in.");
		data.setLoginController(loginc);
		return new MainMenu(data);
	}

}
