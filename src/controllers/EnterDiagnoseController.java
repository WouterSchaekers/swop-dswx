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

/**
 * Use this controller to enter the diagnose of a patient.
 */
public class EnterDiagnoseController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor. Initialises this controller.
	 * 
	 * @param lc
	 *            The LoginController of the Doctor trying to enter the diagnose
	 *            of a patient.
	 * @param patientFileOpenController
	 *            The controller of the patient file opened by the doctor whom
	 *            the logincontroller belongs to.
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 * @throws InvalidPatientFileOpenController
	 */
	public EnterDiagnoseController(LoginController lc,
			ConsultPatientFileController patientFileOpenController)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(lc, patientFileOpenController);
	}

	/**
	 * Enters the given diagnose and marks it for second opinion.
	 * 
	 * @param diag
	 *            The diagnose to be entered.
	 * @param choice
	 *            The doctor who needs to approve this diagnose before any
	 *            treatments can be scheduled.
	 * @return The diagnose object that was created.
	 * @throws InvalidDoctorException
	 * @throws InvalidDiagnoseException
	 */
	public DiagnoseIN enterDiagnoseWithSecondOpinion(String diag,
			DoctorIN choice) throws InvalidDoctorException,
			InvalidDiagnoseException {

		Diagnose d = null;// PatientFile.createDiagnoseSecondOp(diag,
		// (Doctor) lc.getUser(), (Doctor) choice,
		// hospital.getTaskManager());
		((PatientFile) pfoc.getPatientFile()).addDiagnosis(d);
		return d;
	}

	/**
	 * Enters a diagnose that does not need a second opinion.
	 * 
	 * @param diag
	 *            The diagnose to be entered.
	 * @return The diagnose object that was created.
	 * @throws InvalidLoginControllerException
	 * @throws InvalidPatientFileOpenController
	 * @throws InvalidDiagnoseException
	 * @throws InvalidDoctorException
	 */
	public DiagnoseIN enterDiagnose(String diag)
			throws InvalidLoginControllerException,
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