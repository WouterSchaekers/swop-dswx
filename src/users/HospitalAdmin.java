package users;

import controllers.interfaces.HospitalAdminIN;
import exceptions.InvalidNameException;

public class HospitalAdmin extends User implements HospitalAdminIN
{
	 HospitalAdmin(String name) throws InvalidNameException {
		super(name);
	}

	@Override
	public UserFactory getType() {
		return new HospitalAdminFactory();
	}
}
