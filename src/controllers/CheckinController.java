package controllers;

import patient.PatientFile;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;

/**
 * This class is used to register and check in patients in the hospital.
 */
public class CheckinController extends NeedsLoginController
{
	
	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller for this checkincontroller.
	 * @param pfm
	 *            The patientfilemanager for this checkincontroller.
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 */
	public CheckinController(LoginController lc)
			throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * This method can be used to check a patient in that's already been
	 * registered in the past.
	 * 
	 * @param patientFile
	 *            The patient to be checked in.
	 */
	public void checkIn(PatientFile patientFile) {
		hospital.getPatientFileManager().checkIn(patientFile);
	}

	/**
	 * This method can be used to register a new patient.
	 * 
	 * @return the patientfile
	 * @throws InvalidNameException
	 * @throws InvalidLoginControllerException
	 */
	public PatientFile signUpNewPatient(String name)
			throws InvalidNameException {
		return hospital.getPatientFileManager().registerPatient(name);
	}
	
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}
}