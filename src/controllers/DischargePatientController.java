package controllers;

import patient.PatientFile;
import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.DischargePatientException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

public class DischargePatientController extends
		NeedsLoginAndPatientFileController
{

	public DischargePatientController(Hospital hospital,
			LoginController loginController, ConsultPatientFileController cpfc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(hospital, loginController, cpfc);
		if (cpfc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Trying to discharge an invalid patientfile!");
	}

	public void dischargePatient() throws DischargePatientException {
		// TODO: check if unfinished treatments/test/unapproved diag?
		hospital.getPatientFileManager().checkOut(
				(PatientFile) pfoc.getPatientFile());
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}