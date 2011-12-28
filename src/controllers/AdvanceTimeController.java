package controllers;

import scheduler.HospitalDate;
import scheduler.TimeLord;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;

public class AdvanceTimeController
{

	private LoginController loginController;
	private TimeLord	timelord;
	public AdvanceTimeController(LoginController loginController, DataPasser data) throws InvalidLoginControllerException {
		if(!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		this.loginController=loginController;
		timelord = data.getTimeLord();
	}

	private boolean isValidLoginController(LoginController loginController) {
		
		return false;
	}

	public void setNewSystemTime(HospitalDate hospitalDate) {
		this.timelord.setSystemTime(hospitalDate);
		
	}

}
