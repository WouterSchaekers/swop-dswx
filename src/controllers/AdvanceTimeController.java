package controllers;

import scheduler.HospitalDate;
import scheduler.TimeLord;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidLoginControllerException;

public class AdvanceTimeController extends NeedsLoginController
{

	private TimeLord	timelord;
	public AdvanceTimeController(LoginController loginController, DataPasser data) throws InvalidLoginControllerException {
		super(loginController);
		timelord = data.getTimeLord();
	}

	

	public void setNewSystemTime(HospitalDate hospitalDate) {
		this.timelord.setSystemTime(hospitalDate);
		
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
