package controllers;

import scheduler.HospitalDate;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class AdvanceTimeController extends NeedsLoginController
{
	public AdvanceTimeController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * A new systemtime is set.
	 * 
	 * @param hospitalDate
	 * @throws InvalidLoginControllerException
	 */
	public void setNewSystemTime(HospitalDate hospitalDate)
			throws InvalidLoginControllerException {
		this.hospital.getTimeKeeper().setSystemTime(hospitalDate);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
