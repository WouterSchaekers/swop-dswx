package controllers;

import scheduler.HospitalDate;
import scheduler.TimeLord;
import system.HospitalState;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class AdvanceTimeController extends NeedsLoginController
{

	private TimeLord	timelord;
	public AdvanceTimeController(LoginController loginController, HospitalState hospitalState) throws InvalidLoginControllerException, InvalidHospitalStateException {
		super(hospitalState, loginController);
		timelord = hospitalState.getSystemTime();
	}
	
	/**
	 * A new systemtime is set.
	 * @param hospitalDate
	 */
	public void setNewSystemTime(HospitalDate hospitalDate) {
		this.timelord.setSystemTime(hospitalDate);
		
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
