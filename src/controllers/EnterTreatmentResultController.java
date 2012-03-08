package controllers;

import java.util.Collection;
import result.Result;
import system.Hospital;
import treatment.Treatment;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to enter the result of a treatment.
 */
public class EnterTreatmentResultController extends NeedsLoginController
{

	public EnterTreatmentResultController(Hospital hospital,
			LoginController controller, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(hospital, controller);
	}

	public Collection<Treatment> getAllTreatments() {
		// TODO: implement
		return null;
	}

	public void addResultTo(Result r, Treatment t) {
		// TODO: implement
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
