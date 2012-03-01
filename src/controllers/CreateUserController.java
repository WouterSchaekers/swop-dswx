package controllers;

import system.HospitalState;
import users.HospitalAdmin;
import users.User;
import users.UserManager;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class CreateUserController extends NeedsLoginController
{
	public CreateUserController(LoginController loginController,
			HospitalState hospitalState)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, loginController);
	}

	public void createNurse(String nurse)
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException {
		hospitalState.getUserManager().createNurse(nurse);
	}

	public void createDoctor(String nurse, LoginController l) throws UserAlreadyExistsException, InvalidNameException
			 {
		hospitalState.getUserManager().createDoctor(nurse);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
