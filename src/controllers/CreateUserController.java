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
	private LoginController loginc;
	private UserManager manager;

	public CreateUserController(LoginController loginController,
			HospitalState hospitalState)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, loginController);
		if (!isValidUserManager(hospitalState.getUserManager()))
			throw new IllegalArgumentException("invalid user manager");
		this.loginc = loginController;
		this.manager = hospitalState.getUserManager();
	}

	private boolean isValidUserManager(UserManager manager) {
		return true;

	}

	public void createNurse(String nurse, LoginController l)
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException {
		if (!l.equals(loginc))
			throw new IllegalArgumentException("invalid login controller");
		if (!hospitalState.getUserManager().equals(manager))
			throw new IllegalArgumentException("invalid usermanager controller");
		hospitalState.getUserManager().createNurse(nurse);
	}

	public void createDoctor(String nurse, LoginController l)
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException {
		if (!l.equals(loginc))
			throw new IllegalArgumentException("invalid login controller");
		if (!hospitalState.getUserManager().equals(manager))
			throw new IllegalArgumentException("invalid usermanager");
		hospitalState.getUserManager().createDoctor(nurse);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
