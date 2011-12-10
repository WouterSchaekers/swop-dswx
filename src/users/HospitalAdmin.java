package users;

import exceptions.InvalidNameException;

public class HospitalAdmin extends User
{

	protected HospitalAdmin(String name) throws InvalidNameException {
		super(name);
	}

	@Override
	public UserType type() {
		return UserType.HospitalAdmin;
	}

}
