package controllers;

import scheduler.HospitalDate;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * This controller allows you to advance the time in the hospital.
 */
@controllers.PUBLICAPI
public class AdvanceTimeController extends NeedsLoginController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to advance the
	 *            time.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller seems to be invalid or is not a
	 *             hospital administrator.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	@controllers.PUBLICAPI
	public AdvanceTimeController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * @return The current system time.
	 */
	@controllers.PUBLICAPI
	public HospitalDate getTime() {
		return this.hospital.getTimeKeeper().getSystemTime();
	}

	/**
	 * Allows you to set a new time in the hospital, given that it's in the
	 * future.
	 * 
	 * @param hospitalDate
	 *            The desired new time in the hospital.
	 * @throws InvalidSystemTime
	 *             If the provided hospitaldate is not in the future compared to
	 *             the previous system time.
	 */
	@controllers.PUBLICAPI
	public void setNewSystemTime(HospitalDate hospitalDate) throws InvalidHospitalDateException {
		this.hospital.getTimeKeeper().setSystemTime(hospitalDate);
	}

	/**
	 * @return True if the given user is a hospital administrator.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}