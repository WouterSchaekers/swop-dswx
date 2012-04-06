package system;

import users.DoctorFactory;
import users.NurseFactory;
import users.UserManager;
import users.WarehouseAdminFactory;
import exceptions.InvalidNameException;

public class StandarUserManagerBuilder
{
	private String hospitalAdmin_ = "admin";

	public UserManager create() {
		UserManager usm;
		try {
			usm = new UserManager(hospitalAdmin_);
		} catch (InvalidNameException e) {
			throw new Error(e);
		}
		usm.addType(new NurseFactory());
		usm.addType(new DoctorFactory());
		usm.addType(new WarehouseAdminFactory());

		return usm;

	}
}
