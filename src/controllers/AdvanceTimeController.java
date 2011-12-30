package controllers;

import scheduler.HospitalDate;
import scheduler.TimeLord;
import users.HospitalAdmin;
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
		if(loginController==null)
			return false;
		if(!(loginController.getUser() instanceof HospitalAdmin))
			return false;
		if(this.loginController!=null && this.loginController.equals(loginController))
			return false;
		return true;
	}

	public void setNewSystemTime(HospitalDate hospitalDate) {
		this.timelord.setSystemTime(hospitalDate);
		
	}

}
