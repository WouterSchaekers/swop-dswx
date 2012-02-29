package controllers;

import patient.PatientFile;
import system.HospitalState;
import users.Doctor;
import users.User;
import exceptions.DischargePatienException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

public class DischargePatientController extends
		NeedsLoginAndPatientFileController
{

	public DischargePatientController(HospitalState state,
			LoginController loginController,
			PatientFileOpenController patienfile)
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidHospitalStateException,
			InvalidPatientFileOpenController {
		super(state, loginController, patienfile);
	}

	public void dischargePatient(LoginController loginc,
			PatientFileOpenController pfc, HospitalState hospitalState)
			throws InvalidLoginControllerException,
			InvalidPatientFileException, DischargePatienException,
			InvalidPatientFileOpenController {
		super.checkValidity(loginc, pfc);
		hospitalState.getPatientFileManager().checkOut(
				(PatientFile) pfc.getPatientFile());

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}
