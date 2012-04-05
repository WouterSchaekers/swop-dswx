package controllers;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import users.Nurse;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

/**
 * This class is used to check patients in into the hospital. If you would like
 * to register a new patient, please use the register patient file controller.
 */
@controllers.PUBLICAPI
public class CheckinController extends NeedsLoginController
{

	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The logincontroller of the user that wants to check a patient
	 *            in into the hospital.
	 * @throws InvalidLoginControllerException
	 *             If the user to whom the given login controller belongs is not
	 *             a Nurse or if the login controller is invalid for any other
	 *             reason.
	 * @throws InvalidHospitalException
	 * @see NeedsLoginController
	 * @see HospitalController
	 */
	@controllers.PUBLICAPI
	public CheckinController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * Use this method to check in a patient with an existing patient file.
	 * 
	 * @throws InvalidPatientFileException
	 *             If the given patient file is not registered in the hospital,
	 *             or is already checked in.
	 */
	@controllers.PUBLICAPI
	public void checkIn(PatientFileIN patientFile) throws InvalidPatientFileException {
		hospital.getPatientFileManager().checkIn((PatientFile) patientFile);
	}

	/**
	 * Use this method to display a list of the possible patients that can be
	 * checked in into this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new LinkedList<PatientFileIN>(hospital.getPatientFileManager().getAllPatientFiles());
	}

	/**
	 * @return True if the given user is a nurse.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}
}