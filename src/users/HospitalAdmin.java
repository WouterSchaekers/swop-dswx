package users;

import controllers.interfaces.HospitalAdminIN;
import exceptions.InvalidNameException;

public class HospitalAdmin extends User implements HospitalAdminIN
{

	protected HospitalAdmin(String name) throws InvalidNameException {
		super(name);
	}


}
