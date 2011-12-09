package users;

import controllers.interfaces.UserIN;
import exceptions.InvalidNameException;

public abstract class User implements UserIN
{
	public abstract usertype type();
	protected String name;

	protected User(String name) throws InvalidNameException {
		if(!isValidName(name))
			throw new InvalidNameException("Invalid name given in constructor of User!");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private boolean isValidName(String n) {
		return !n.equals("");
	}
	
	public enum usertype
	{
		Doctor, Nurse, HospitalAdmin;
	}
}
