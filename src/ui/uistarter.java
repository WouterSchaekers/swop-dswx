package ui;

import users.UserAlreadyExistsException;
import users.UserManager;
import controllers.Metamanager;

/**
 * The UI.
 *
 */
public class uistarter
{
	public static void main(String[] args) {
		Metamanager m = new Metamanager();

		try {
			((UserManager)m.getManager("UserManager")).CreateDoctor("Joseph");
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
		UserInterface t = new UserInterface(m);
		t.start();
		
	}
}
