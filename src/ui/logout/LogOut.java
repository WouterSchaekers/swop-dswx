package ui.logout;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.Usecase;

/**
 * This class will log out the current user of the system.
 */
public class LogOut extends Usecase
{

	public LogOut(UserinterfaceData data) {
		super(data);
	}

	/**
	 * If the login controller is not null, then the user was logged in and will
	 * be logged out. The login controller will be set to null to indicate that
	 * nobody is logged in. At the end of this method the end of the chain is
	 * achieved, the system will return to the beginning of the chain.
	 */
	@Override
	public Usecase Execute() {
		if (data.getLoginController() != null)
			System.out.println(data.getLoginController().getUserIN().getName()
					+ "succesfully logged out.");
		this.data.reset();
		return new SelectUsecase(data);
	}

}
