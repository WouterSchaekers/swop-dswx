package controllers;

import system.Hospital;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to enter the result of a treatment.
 */
public class EnterTreatmentResultController extends
		NeedsLoginAndPatientFileController
{

	public EnterTreatmentResultController(Hospital hospital,
			LoginController controller, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(hospital, controller, pfoc);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
