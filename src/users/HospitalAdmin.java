package users;

import exceptions.InvalidNameException;

public class HospitalAdmin extends User
{

	protected HospitalAdmin(String name) throws InvalidNameException {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.HospitalAdmin;
	}

}
