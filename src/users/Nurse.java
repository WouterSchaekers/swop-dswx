package users;

import exceptions.InvalidNameException;

public class Nurse extends SchedulableUser
{
	public Nurse(String name) throws InvalidNameException {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Nurse;
	}
}