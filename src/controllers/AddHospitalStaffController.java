package controllers;

import java.util.Collection;
import users.HospitalAdmin;
import users.User;
import users.UserFactory;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

@controllers.PUBLICAPI
public class AddHospitalStaffController extends NeedsLoginController
{
	@controllers.PUBLICAPI
	public AddHospitalStaffController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * Adds new personnel to this hospital.
	 */
	@controllers.PUBLICAPI
	public void addStaff(UserFactory fact) throws UserAlreadyExistsException, InvalidNameException {
		this.hospital.getUserManager().createUser(fact);
	}

	/**
	 * Use to get all user factories.
	 */
	@controllers.PUBLICAPI
	public Collection<UserFactory> getFactories() {
		return this.hospital.getUserManager().getUserFacotories();
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}
