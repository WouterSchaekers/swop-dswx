package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterMedicaltestResultController extends
		NeedsLoginAndPatientFileController
{
	public EnterMedicaltestResultController(Hospital hospital,
			LoginController loginController, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(hospital, loginController, pfoc);
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
