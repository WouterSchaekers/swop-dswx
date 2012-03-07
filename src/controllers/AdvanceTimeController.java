package controllers;

import scheduler.HospitalDate;
import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class AdvanceTimeController extends NeedsLoginController
{

	public AdvanceTimeController(LoginController loginController,
			Hospital hospital) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, loginController);
	}

	/**
	 * A new systemtime is set.
	 * 
	 * @param hospitalDate
	 * @throws InvalidLoginControllerException
	 */
	public void setNewSystemTime(HospitalDate hospitalDate)
			throws InvalidLoginControllerException {
		this.hospital.getSystemTime().setSystemTime(hospitalDate);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
