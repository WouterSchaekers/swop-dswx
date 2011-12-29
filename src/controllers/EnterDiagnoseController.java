package controllers;

import patient.Diagnose;
import patient.PatientFile;
import controllers.interfaces.DoctorIN;
import users.Doctor;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterDiagnoseController
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
	 */
	public EnterDiagnoseController(LoginController loginc,
			PatientFileOpenController patientFileOpenController)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController {
		if (!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("");
		if (!isValidPatientFileOpenController(patientFileOpenController, loginc))
			throw new InvalidPatientFileOpenController("");
		this.loginController = loginc;
	}

	private boolean isValidPatientFileOpenController(
			PatientFileOpenController patientFileOpenController,
			LoginController loginc) {
		if (patientFileOpenController == null)
			return false;
		if (!patientFileOpenController.validLoginController(loginc))
			return false;
		if (patientFileOpenController.getPatientFile() == null)
			return false;
		return true;
	}

	/**
	 * check if a logincontroler is a valid key to the use of the
	 * diagnosecontroller.
	 * 
	 * @param loginc
	 * @return
	 */
	private boolean isValidLoginController(LoginController loginc) {
		if (loginc == null)
			return false;
		if (this.loginController != null
				&& !loginc.equals(this.loginController))
			return false;
		if (!(this.loginController.getUser() instanceof Doctor))
			return false;
		return true;
	}

	public void enterDiagnose(LoginController loginController2,
			PatientFileOpenController patientFileOpenController, String diag,
			DoctorIN choice) throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		if (!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if (!isValidPatientFileOpenController(patientFileOpenController,
				loginController2))
			throw new InvalidPatientFileOpenController("");
		Diagnose d = new Diagnose((Doctor) loginController2.getUser(), diag);
		d.markForSecOp((Doctor) choice);
		((PatientFile) patientFileOpenController.getPatientFile())
				.addDiagnosis(d);

	}

	public void enterDiagnose(LoginController loginController2,
			PatientFileOpenController patientFileOpenController, String diag)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController, InvalidDiagnoseException,
			InvalidDoctorException {
		if (!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if (!isValidPatientFileOpenController(patientFileOpenController,
				loginController2))
			throw new InvalidPatientFileOpenController("");
		Diagnose d = new Diagnose((Doctor) loginController2.getUser(), diag);
		((PatientFile) patientFileOpenController.getPatientFile())
				.addDiagnosis(d);

	}

}
