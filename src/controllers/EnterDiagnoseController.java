package controllers;

import patient.Diagnose;
import patient.PatientFile;
import system.Hospital;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterDiagnoseController extends NeedsLoginAndPatientFileController
{

	public EnterDiagnoseController(Hospital hospital, LoginController loginc,
			ConsultPatientFileController patientFileOpenController)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(hospital, loginc, patientFileOpenController);
	}

	public DiagnoseIN enterDiagnoseWithSecondOpinion(String diag, DoctorIN choice)
			throws InvalidDoctorException, InvalidDiagnoseException {

		Diagnose d = PatientFile.createDiagnoseSecondOp(diag,
				(Doctor) loginController.getUser(), (Doctor) choice,
				hospital.getTaskManager());
		((PatientFile) pfoc.getPatientFile()).addDiagnosis(d);
		return d;
	}

	public void enterDiagnose(String diag) throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		Diagnose d = new Diagnose((Doctor) loginController.getUser(), diag);
		((PatientFile) pfoc.getPatientFile()).addDiagnosis(d);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}