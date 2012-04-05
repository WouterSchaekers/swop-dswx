package controllers;

import scheduler.HospitalDate;
import scheduler.InvalidSystemTime;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

@controllers.PUBLICAPI
public class AdvanceTimeController extends NeedsLoginController
{
	@controllers.PUBLICAPI
	public AdvanceTimeController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * A new systemtime is set.
	 * 
	 * @param hospitalDate
	 * @throws InvalidSystemTime 
	 * If the provided hospitaldate is before the old systemtime, 
	 */
	@controllers.PUBLICAPI
	public void setNewSystemTime(HospitalDate hospitalDate) throws InvalidSystemTime {

		this.hospital.getTimeKeeper().setSystemTime(hospitalDate);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
	@controllers.PUBLICAPI
	public HospitalDate getTime()
	{
		return this.hospital.getTimeKeeper().getSystemTime();
	}
}
