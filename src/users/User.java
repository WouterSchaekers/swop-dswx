package users;

import controllers.interfaces.UserIN;
import task.Schedulable;

public abstract class User implements Schedulable,UserIN
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
