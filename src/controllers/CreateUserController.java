package controllers;

import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;
import users.HospitalAdmin;
import users.UserManager;

public class CreateUserController
{
	private LoginController loginc;
	private UserManager manager;
	public CreateUserController(LoginController loginController,DataPasser dataPasser) {
		if(!isValidLoginController(loginController))
			throw new IllegalArgumentException("Invalid loginController");
		if(!isValidUserManager(dataPasser.getUserManager()))
			throw new IllegalArgumentException("invalid user manager");
		this.loginc=loginController;
		this.manager = dataPasser.getUserManager();
	}
	private boolean isValidUserManager(UserManager manager) {
		return true;
		
		
	}
	private boolean isValidLoginController(LoginController loginController) {
		if(loginController.getUser() instanceof HospitalAdmin)
			return true;
		return false;
	}
	public void createNurse(String nurse,LoginController l,DataPasser dataPasser) throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException
	{
		if(!l.equals(loginc))
			throw new IllegalArgumentException("invalid login controller");
		if(!dataPasser.getUserManager().equals(manager))
			throw new IllegalArgumentException("invalid usermanager controller");
		dataPasser.getUserManager().createNurse(nurse);
	}
	public void createDoctor(String nurse,LoginController l,DataPasser m) throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException
	{
		if(!l.equals(loginc))
			throw new IllegalArgumentException("invalid login controller");
		if(!m.getUserManager().equals(manager))
			throw new IllegalArgumentException("invalid usermanager");
		m.getUserManager().createDoctor(nurse);
	}

}
