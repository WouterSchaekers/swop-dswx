package controllers;

import patient.PatientFile;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to enter the diagnose of a patient.
 */
@controllers.PUBLICAPI
public class EnterDiagnoseController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 */
	@controllers.PUBLICAPI
	public EnterDiagnoseController(LoginController lc, ConsultPatientFileController patientFileOpenController)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc, patientFileOpenController);
	}

	/**
	 * Enters a diagnose to the patientfile that is currently opened in the
	 * system.
	 * 
	 * @throws InvalidDiagnoseException
	 * @throws InvalidDoctorException
	 * @throws InvalidComplaintsException
	 */
	@controllers.PUBLICAPI
	public DiagnoseIN enterDiagnose(String complaints, String diag) throws InvalidDiagnoseException,
			InvalidDoctorException, InvalidComplaintsException {
		return ((PatientFile) cpfc.getPatientFile()).createDiagnose(complaints, diag, (Doctor) lc.getUser(), null);
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
	 * @throws InvalidComplaintsException
	 */
	@controllers.PUBLICAPI
	public DiagnoseIN enterDiagnoseWithSecondOpinion(String diag, String complaints, DoctorIN choice)
			throws InvalidDiagnoseException, InvalidDoctorException, InvalidComplaintsException {
		return ((PatientFile) cpfc.getPatientFile()).createDiagnose(complaints, diag, (Doctor) lc.getUser(),
				(Doctor) choice);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}