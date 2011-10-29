package ui;

import users.UserAlreadyExistsException;
import users.UserManager;

/**
 * The UI.
 * 
 */
public class uistarter
{
	public static void main(String[] args) {

		System.out.println("Hi, what would you like to do?");
		UserManager u = new UserManager();
		try {
			u.CreateDoctor("Joseph");
			u.CreateDoctor("Willy");
			u.CreateDoctor("Greg");
			u.CreateDoctor("Jeffrey");
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
		UserInterface t = new UserInterface();
		t.start();

	}
}
