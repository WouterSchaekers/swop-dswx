package controllers;

import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.UserAlreadyExistsException;

public class AddHospitalStaffController extends NeedsLoginController
{
	public AddHospitalStaffController(LoginController loginController,
			Hospital hospital) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, loginController);
	}

	public void createNewStaffMember(User u, String locPref) throws UserAlreadyExistsException{
		// TODO: implement en String-comment in SelectLocationPreference
	}
	
	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
