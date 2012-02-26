package controllers;

import patient.Diagnose;
import patient.PatientFile;
import system.HospitalState;
import users.Doctor;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterDiagnoseController extends NeedsLoginAndPatientFileController
{
	private LoginController loginController;

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
	public EnterDiagnoseController(HospitalState hospitalState, LoginController loginc,
			PatientFileOpenController patientFileOpenController)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidHospitalStateException {
		super(hospitalState, loginc, patientFileOpenController);
		this.loginController = loginc;
	}

	/**
	 * check if a logincontroler is a valid key to the use of the
	 * diagnosecontroller.
	 * 
	 * @param loginc
	 * @return
	 */

	public DiagnoseIN enterDiagnose(LoginController loginController2,
			PatientFileOpenController patientFileOpenController, String diag,
			DoctorIN choice, HospitalState hospitalState) throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		if (!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if (!isValidPatientFileOpenController(patientFileOpenController))
			throw new InvalidPatientFileOpenController("");
		Diagnose d =PatientFile.createDiagnoseSecondOp(diag, (Doctor)loginController2.getUser(),(Doctor) choice, data.getTaskmanager());
		((PatientFile) patientFileOpenController.getPatientFile())
				.addDiagnosis(d);
		return d;
	}

	public void enterDiagnose(LoginController loginController2,
			PatientFileOpenController patientFileOpenController, String diag)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		if (!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if (!isValidPatientFileOpenController(patientFileOpenController))
			throw new InvalidPatientFileOpenController("");
		Diagnose d = new Diagnose((Doctor) loginController2.getUser(), diag);
		((PatientFile) patientFileOpenController.getPatientFile())
				.addDiagnosis(d);

	}

}
