package controllers;

import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

/**
 * Allows you to add hospital staff to the hospital.
 */
public class AddHospitalStaffController extends NeedsLoginController
{

	/**
	 * Default constructor for this controller.
	 */
	public AddHospitalStaffController(Hospital hospitalState,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
