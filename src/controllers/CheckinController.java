package controllers;

import patient.PatientFile;
import patient.PatientFileManager;
import system.HospitalState;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;

/**
 * This class is used to register and check in patients in the hospital.
 */
public class CheckinController extends NeedsLoginController
{
	private PatientFileManager pfm = null; // the pfm for this checkincontroller
	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller for this checkincontroller.
	 * @param pfm
	 *            The patientfilemanager for this checkincontroller.
	 * @throws InvalidLoginControllerException 
	 * @throws InvalidHospitalStateException 
	 */
	public CheckinController(LoginController lc, HospitalState hospitalState) throws InvalidLoginControllerException, InvalidHospitalStateException {
		super(hospitalState, lc);
		this.pfm = hospitalState.getPatientFileManager();
	}

	/**
	 * This method can be used to check a patient in that's already been
	 * registered in the past.
	 * 
	 * @param patientFile
	 *            The patient to be checked in.
	 */
	public void checkIn(PatientFile patientFile) {
		pfm.checkIn(patientFile);
	}

	/**
	 * This method can be used to register a new patient.
	 * 
	 * @return the patientfile
	 * @throws InvalidNameException 
	 */
	public PatientFile signUpNewPatient(String name) throws InvalidNameException {
		return pfm.registerPatient(name);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
		//TODO: Check if correct
	}

}
