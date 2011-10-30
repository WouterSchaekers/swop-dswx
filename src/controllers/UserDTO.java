package controllers;

import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;
import users.User.usertype;

public class UserDTO
{
	private User u;
	private String name;

	public UserDTO(User u) {
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
