package controllers;

import java.util.Collection;
import users.HospitalAdmin;
import users.User;
import users.UserFactory;
import users.Users;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

public class AddHospitalStaffController extends NeedsLoginController
{
	public AddHospitalStaffController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * Adds new personnel to this hospital.
	 */
	public void addStaff(UserFactory fact) throws UserAlreadyExistsException, InvalidNameException {
		this.hospital.getUserManager().createUser(fact);
	}

	/**
	 * Use to get all user factories.
	 */
	public Collection<UserFactory> getFactories() {
		return Users.factories();
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}
