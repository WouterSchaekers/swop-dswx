package controllers;

import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Allows you to add hospital staff to the hospital.
 */
public class AddHospitalStaffController extends NeedsLoginController
{

	/**
	 * Default constructor for this controller.
	 */
	public AddHospitalStaffController(Hospital hospital,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
