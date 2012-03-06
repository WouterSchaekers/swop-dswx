package controllers;

import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class SelectLocationPreferenceController extends NeedsLoginController
{

	public SelectLocationPreferenceController(Hospital hospitalState,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
