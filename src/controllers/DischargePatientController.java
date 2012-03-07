package controllers;

import patient.PatientFile;
import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.DischargePatienException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class DischargePatientController extends
		NeedsLoginAndPatientFileController
{

	public DischargePatientController(Hospital hospital,
			LoginController loginController,
			ConsultPatientFileController patienfile)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(hospital, loginController, patienfile);
	}

	public void dischargePatient() throws DischargePatienException {
		hospital.getPatientFileManager().checkOut(
				(PatientFile) pfoc.getPatientFile());

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}