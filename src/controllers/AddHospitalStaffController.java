package controllers;

import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import system.HospitalState;
import users.User;

public class AddHospitalStaffController extends NeedsLoginController
{

	public AddHospitalStaffController(HospitalState hospitalState,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean validUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
