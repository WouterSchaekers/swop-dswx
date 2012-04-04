package ui.usecases;

import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.LoginController;
import controllers.interfaces.CampusIN;
import controllers.interfaces.UserIN;

public class Login extends UseCase
{

	private LoginController loginc;
	public static final Displayer<CampusIN> campusDisplayer=new Displayer<CampusIN>()
	{

		@Override
		public void display(CampusIN t) {
			System.out.print("Campus:"+t.getName());
			
		}
	};

	public Login(UIData data) throws Exception {
		super(data,1);
		this.loginc = new LoginController(data.hospital());
		if(data.getLoginController()!=null&&data.getLoginController().loggedIn())
			throw new Exception();
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
		printLn("Login screen v1.1:");
		printLn("These are the available users:");
		Selector<UserIN> uiselector = new Selector<UserIN>(loginc.getAllUsers(), userDisplayer);
		Selector<CampusIN> campusSelector = new Selector<CampusIN>(loginc.getLocations(),campusDisplayer);
		UserIN selected = uiselector.get();
		if(selected==null)
		{
			System.out.println("No user was logged in.");
			return new MainMenu(data);
		}
		userDisplayer.display(selected);
		printLn("Was sucesfully selected, please choose a campus.");
		CampusIN selectedCampus = campusSelector.get();
		if(selectedCampus == null)
		{
			System.out.println("No campus selected.");
			return new MainMenu(data);
		}
		data.setLoginController(loginc);
		loginc.logIn(selected,selectedCampus);
		return new MainMenu(data);
	}
	@Override
	public String toString()
	{
		return "Login ";
	}
}
