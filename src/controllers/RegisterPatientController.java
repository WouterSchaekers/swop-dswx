package controllers;

import controllers.interfaces.PatientFileIN;
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
	public PatientFileIN registerNewPatient(String name)
			throws InvalidNameException, InvalidPatientFileException {

	
		return hospital.getPatientFileManager().registerPatient(name, ((Nurse)loginController_.getUser()).getLocationAt(hospital.getTimeKeeper().getSystemTime()));

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}