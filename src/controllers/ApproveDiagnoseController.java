package controllers;

import java.util.Collection;
import patient.Diagnose;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import exceptions.ApproveDiagnoseException;
import exceptions.DischargePatientException;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to give a second opinion on a diagnose of a patient.
 */
public class ApproveDiagnoseController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 * 
	 * @throws DischargePatientException
	 *             If the patient of the patient file the doctor currently has
	 *             opened has already been discharged.
	 */
	public ApproveDiagnoseController(LoginController lc, ConsultPatientFileController cpfc)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController,
			DischargePatientException {
		super(lc, cpfc);
		if (cpfc.getPatientFile().isDischarged())
			throw new DischargePatientException("Cannot approve a diagnose of a Patient that was already discharged!");
	}

	/**
	 * Approves the selected diagnose.
	 * 
	 * @throws ApproveDiagnoseException
	 */
	public void approveDiagnose(DiagnoseIN selected) throws ApproveDiagnoseException {
		if (isValidDiagnose(selected))
			((Diagnose) selected).approve();
		else
			throw new ApproveDiagnoseException("Trying to approve a diagnose that does not exist!");
	}

	/**
	 * Disapproves the selected diagnose and enters a replacement.
	 * 
	 * @throws ApproveDiagnoseException
	 * @throws InvalidComplaintsException 
	 * @throws InvalidDoctorException
	 */
	public void disapproveDiagnose(DiagnoseIN selected, String newDiag, String newComplaints)
			throws InvalidDiagnoseException, ApproveDiagnoseException, InvalidDoctorException, InvalidComplaintsException {
		((Diagnose)selected).disapprove(newDiag, newComplaints);
		
	}

	/**
	 * Use to display pending diagnosis for the doctor who currently is logged
	 * on.
	 */
	public Collection<DiagnoseIN> getPendingDiagnosis() {
		return cpfc.getPatientFile().getPendingDiagnosisFor((Doctor) lc.getUser());
	}

	private boolean isValidDiagnose(DiagnoseIN d) {
		return cpfc.getPatientFile().getAllDiagnosis().contains(d);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
