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
		UserManager u = m.getUserManager();
		try {
			u.CreateDoctor("Joseph");
			u.CreateDoctor("Willy");
			u.CreateDoctor("Greg");
			u.CreateDoctor("Jeffrey");
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
		UserInterface t = new UserInterface(m);
		t.start();

	}
}
