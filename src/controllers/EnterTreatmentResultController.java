package controllers;

import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import system.HospitalState;
import users.User;

public class EnterTreatmentResultController extends
		NeedsLoginAndPatientFileController
{

	public EnterTreatmentResultController(HospitalState hospitalState,
			LoginController controller, PatientFileOpenController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException, InvalidPatientFileOpenController {
		super(hospitalState, controller, pfoc);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean validUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
