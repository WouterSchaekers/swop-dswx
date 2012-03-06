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
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterDiagnoseController extends NeedsLoginAndPatientFileController
{
	/**
	 * Creates a Enter Diagnose Controller
	 * 
	 * @param loginc
	 *            The user that creates this enter diagnose controller
	 * @param patientFileOpenController
	 *            The patient that has
	 * @throws InvalidLoginControllerException
	 * @throws InvalidPatientFileOpenController
	 * @throws InvalidHospitalStateException
	 */
	public EnterDiagnoseController(Hospital hospitalState,
			LoginController loginc,
			PatientFileOpenController patientFileOpenController)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidHospitalStateException {
		super(hospitalState, loginc, patientFileOpenController);
	}

	/**
	 * check if a logincontroler is a valid key to the use of the
	 * diagnosecontroller.
	 * 
	 * @param loginc
	 * @return
	 * @throws InvalidDiagnoseException 
	 * @throws InvalidDoctorException 
	 */

	public DiagnoseIN enterDiagnose( String diag,
			DoctorIN choice) throws InvalidDoctorException, InvalidDiagnoseException
			 {
		
		Diagnose d = PatientFile.createDiagnoseSecondOp(diag,
				(Doctor) loginControler.getUser(), (Doctor) choice,
				hospitalState.getTaskManager());
		((PatientFile) pfoc.getPatientFile())
				.addDiagnosis(d);
		return d;
	}

	public void enterDiagnose(
			PatientFileOpenController patientFileOpenController, String diag)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		Diagnose d = new Diagnose((Doctor) loginControler.getUser(), diag);
		((PatientFile) patientFileOpenController.getPatientFile())
				.addDiagnosis(d);

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
