package controllers;

import java.util.ArrayList;
import java.util.Collection;
import users.User;
import users.UserManager;

public class LoginController extends Controller
{

	public LoginController(Metamanager t) {
		super(t);
	}

	public Collection<String> getUserNames() {
		Collection<String> result = new ArrayList<String>();
		result.add("Available Users: \n");
		Collection<User> users= this.m.getUserManager().getAllusers();
		for (User u : users)
				result.add(u.getName());
		return result;
	}

	public Collection<String> login(String name) {
		this.m.getUserManager().login(name);
		return ;
	}
}
