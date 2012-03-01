package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import system.HospitalState;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterMedicaltestResultController extends
		NeedsLoginAndPatientFileController
{
	public EnterMedicaltestResultController(HospitalState state,
			LoginController loginController, PatientFileOpenController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException, InvalidPatientFileOpenController {
		super(state, loginController, pfoc);
	}

	public Collection<MedicalTest> allMedicalTests()
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController {

		return null;

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
