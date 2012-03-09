package controllers;

import patient.Diagnose;
import patient.PatientFile;
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

	public EnterDiagnoseController(LoginController lc,
			ConsultPatientFileController patientFileOpenController)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(lc, patientFileOpenController);
	}

	public DiagnoseIN enterDiagnoseWithSecondOpinion(String diag, DoctorIN choice)
			throws InvalidDoctorException, InvalidDiagnoseException {

		Diagnose d = PatientFile.createDiagnoseSecondOp(diag,
				(Doctor) lc.getUser(), (Doctor) choice,
				hospital.getTaskManager());
		((PatientFile) pfoc.getPatientFile()).addDiagnosis(d);
		return d;
	}

	public DiagnoseIN enterDiagnose(String diag) throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		Diagnose d = new Diagnose((Doctor) lc.getUser(), diag);
		((PatientFile) pfoc.getPatientFile()).addDiagnosis(d);
		return d;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}