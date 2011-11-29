package users;

import controllers.interfaces.UserIN;
import task.Resource;

public abstract class User implements Resource,UserIN
{
	public abstract usertype type();

	protected String name;

	protected User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public enum usertype
	{
		Doctor, Nurse, HospitalAdmin;
	}
}
