package controllers;

import system.Hospital;
import users.HospitalAdmin;
import users.User;
import users.UserManager;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class CreateUserController extends NeedsLoginController
{
	public CreateUserController(LoginController loginController,
			Hospital hospital)
			throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, loginController);
	}

	public void createNurse(String nurse)
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException {
		hospital.getUserManager().createNurse(nurse);
	}

	public void createDoctor(String nurse, LoginController l) throws UserAlreadyExistsException, InvalidNameException
			 {
		hospital.getUserManager().createDoctor(nurse);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
