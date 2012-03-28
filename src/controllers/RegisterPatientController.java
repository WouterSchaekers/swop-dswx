package controllers;

import users.Nurse;
import users.User;
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
	 * @see NeedsLoginController
	 */
	public RegisterPatientController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException {
		super(lc);
	}

	/**
	 * Registers a new patient in this hospital.
	 * 
	 * @throws InvalidPatientFileException
	 */
	public void registerNewPatient(String name)
			throws InvalidNameException, InvalidPatientFileException {
		hospital.getPatientFileManager().registerPatient(name, ((Nurse)lc.getUser()).getLocation());
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}