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

		System.out.println("Hi, what would you like to do?");
		try {
			((UserManager) m.getManager("UserManager")).CreateDoctor("Joseph");
			((UserManager) m.getManager("UserManager")).CreateDoctor("Willy");
			((UserManager) m.getManager("UserManager")).CreateDoctor("Greg");
			((UserManager) m.getManager("UserManager")).CreateDoctor("Jeffrey");
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
		UserInterface t = new UserInterface(m);
		t.start();

	}
}
