package controllers;

import system.HospitalState;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to enter the result of a treatment.
 */
public class EnterTreatmentResultController extends
		NeedsLoginAndPatientFileController
{

	public EnterTreatmentResultController(HospitalState hospitalState,
			LoginController controller, PatientFileOpenController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException, InvalidPatientFileOpenController {
		super(hospitalState, controller, pfoc);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
