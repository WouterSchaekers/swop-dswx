package controllers;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.PatientFileIN;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;

/**
 * This class is used to check patients in into the hospital. If you would like
 * to register a new patient, please use the register patient file controller.
 */
public class CheckinController extends NeedsLoginController
{

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller for this checkincontroller.
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 */
	public CheckinController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException {
		super(lc);
	}

	/**
	 * Use this method to check in a patient with an existing patient file.
	 * 
	 * @throws InvalidNameException
	 */
	public void checkIn(PatientFileIN pf) throws InvalidNameException {
		hospital.getPatientFileManager().checkIn(
				hospital.getPatientFileManager().getPatientFileFrom(
						pf.getPatientName()));
	}

	/**
	 * Use this method to display a list of the possible patients to be checked
	 * in into this hospital.
	 */
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new ArrayList<PatientFileIN>(hospital.getPatientFileManager()
				.getAllPatientFiles());
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}
}