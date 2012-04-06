package controllers;

import users.Nurse;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;

/**
 * This controller allows you to register a new patient in the hospital.
 */
public class RegisterPatientController extends NeedsLoginController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to register a new
	 *            patient in this hospital.
	 * @throws InvalidLoginControllerException
	 *             If the owner of the given login controller is not a nurse or
	 *             if the controller is invalid in any other way.
	 * @throws InvalidHospitalException
	 * @see NeedsLoginController
	 * @see HospitalController
	 */
	@controllers.PUBLICAPI
	public RegisterPatientController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * Registers a new patient into the database of this hospital.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return The created patient file.
	 * @throws InvalidNameException
	 *             If the given name of the patient is invalid.
	 * @throws InvalidPatientFileException
	 *             If this hospital already contains a patient file of the given
	 *             patient.
	 */
	@controllers.PUBLICAPI
	public PatientFileIN registerNewPatient(String name) throws InvalidNameException, InvalidPatientFileException {
		return hospital.getPatientFileManager().registerPatient(name,
				((Nurse) loginController_.getUser()).getLocationAt(hospital.getTimeKeeper().getSystemTime()));

	}

	/**
	 * @return True if the given user is a nurse.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}