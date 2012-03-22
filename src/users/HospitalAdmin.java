package users;

import controllers.interfaces.HospitalAdminIN;
import exceptions.InvalidNameException;

public class HospitalAdmin extends User implements HospitalAdminIN
{
	public HospitalAdmin(String name) throws InvalidNameException {
		super(name);
	}
}
