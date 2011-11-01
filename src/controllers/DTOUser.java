package controllers;

import users.User;
import users.User.usertype;

public class DTOUser
{
	private User u;
	private String name;

	public DTOUser(User u) {
		this.u = u;
		this.name = u.getName();

	}

	public usertype type() {
		return u.type();
	}

	public String getName() {
		return name;
	}

	User getUser() {
		return u;
	}
}
