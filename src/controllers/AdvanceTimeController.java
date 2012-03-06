package controllers;

import scheduler.HospitalDate;
import scheduler.TimeLord;
import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class AdvanceTimeController extends NeedsLoginController
{

	private TimeLord timelord;

	public AdvanceTimeController(LoginController loginController,
			Hospital hospitalState)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, loginController);
		timelord = hospitalState.getSystemTime();
	}

	/**
	 * A new systemtime is set.
	 * 
	 * @param hospitalDate
	 * @throws InvalidLoginControllerException 
	 */
	public void setNewSystemTime(HospitalDate hospitalDate) throws InvalidLoginControllerException {
		this.timelord.setSystemTime(hospitalDate);

	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
