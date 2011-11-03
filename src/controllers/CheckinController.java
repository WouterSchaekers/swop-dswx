package controllers;

import patient.PatientFile;
import patient.PatientFileManager;
import users.Nurse;

/**
 * This class is used to register and check in patients in the hospital.
 */
public class CheckinController extends superController
{
	private PatientFileManager pfm = null; // the pfm for this checkincontroller

	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	private CheckinController() {
		super(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller for this checkincontroller.
	 * @param pfm
	 *            The patientfilemanager for this checkincontroller.
	 */
	public CheckinController(LoginController lc, PatientFileManager pfm) {
		super(lc);
		if (!(lc.getUser() instanceof Nurse))
			throw new IllegalArgumentException(
					"User registering the patient is not a nurse!");
		this.pfm = pfm;
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
	 * @return the patientfile
	 */
	public PatientFile signUpNewPatient(String name) {
		return pfm.registerPatient(name);
	}

}
