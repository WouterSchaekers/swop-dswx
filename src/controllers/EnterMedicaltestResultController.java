package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import result.Result;
import system.Hospital;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterMedicaltestResultController extends NeedsLoginController
{
	public EnterMedicaltestResultController(Hospital hospital,
			LoginController loginController)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(hospital, loginController);
	}

	public Collection<MedicalTest> getAllMedicalTests()
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController {
		return null;
		// TODO: fix
	}

	public void addResultTo(Result r, MedicalTest medicalTest) {
		// TODO: implement
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
