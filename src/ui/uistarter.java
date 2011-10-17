package ui;

import users.UserAlreadyExistsException;
import users.UserManager;
import controllers.Metamanager;

public class uistarter
{
	public static void main(String[] args) {
		Metamanager m = new Metamanager();
		m.intializeSystem();
		try {
			((UserManager)m.getManager("UserManager")).CreateDoctor("Joseph");
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInterface t = new UserInterface(m);
		t.start();
		
	}
}
