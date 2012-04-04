package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import users.Nurse;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.NoSuchPatientException;

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
	 * @param lc
	 *            The logincontroller for this checkincontroller.
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 */
	@controllers.PUBLICAPI
	public CheckinController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException {
		super(lc);
	}

	/**
	 * Use this method to check in a patient with an existing patient file.
	 * 
	 * @throws InvalidNameException
	 * @throws NoSuchPatientException 
	 * @throws InvalidPatientFileException 
	 */
	@controllers.PUBLICAPI
	public void checkIn(PatientFileIN pf) throws  NoSuchPatientException, InvalidPatientFileException {
		hospital.getPatientFileManager().checkIn((PatientFile)pf);
	}

	/**
	 * Use this method to display a list of the possible patients to be checked
	 * in into this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new ArrayList<PatientFileIN>(hospital.getPatientFileManager()
				.getAllPatientFiles());
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}
}