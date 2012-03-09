package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import result.Result;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterMedicaltestResultController extends NeedsLoginController
{
	public EnterMedicaltestResultController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(lc);
	}

	public Collection<MedicalTest> getAllMedicalTests()
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController {
		return null;
		// TODO: fix
	}

	public void addResultTo(String report, MedicalTest medicalTest) {
		// TODO: implement
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
