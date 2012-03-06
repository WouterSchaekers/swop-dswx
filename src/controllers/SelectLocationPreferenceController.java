package controllers;

import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class SelectLocationPreferenceController extends NeedsLoginController
{
	public SelectLocationPreferenceController(Hospital hospital,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}