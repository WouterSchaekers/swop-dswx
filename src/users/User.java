package users;

import task.Resource;

public abstract class User implements Resource
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
