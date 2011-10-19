package controllers;

import users.User;
import users.UserManager;

public class LoginController extends Controller
{

	public LoginController(Metamanager t) {
		super(t);
	}

	public void getUsers() {
		q.push("Available Users: \n");
		for (User u : ((UserManager) this.m.getManager("UserManager"))
				.getAllusers())
			q.push("* " + u.getName());

	}

	public void login(String name) {
		((UserManager) this.m.getManager("UserManager")).login(name);
		q.push(" was succesfully logged in");
	}
}
