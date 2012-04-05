package users;

import controllers.interfaces.UserIN;
import exceptions.InvalidNameException;

public abstract class User implements UserIN
{	
	protected String name;

	User(String name) throws InvalidNameException {
		if (!isValidName(name))
			throw new InvalidNameException(
					"Invalid name given in constructor of User!");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private boolean isValidName(String n) {
		return !n.equals("");
	}
	public abstract UserFactory getType();
	
	@Override
	public boolean equals(Object o) {
		return o instanceof User && ! ((User)o).getName().equals(this.name);
	}
}
