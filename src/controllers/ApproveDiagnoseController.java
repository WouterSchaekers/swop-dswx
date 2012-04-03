package controllers;

import java.util.Collection;
import patient.Diagnose;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import exceptions.ApproveDiagnoseException;
import exceptions.DischargePatientException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to give a second opinion on a diagnose of a patient.
 */
@controllers.PUBLICAPI
public class ApproveDiagnoseController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 * 
	 * @throws DischargePatientException
	 *             If the patient of the patient file the doctor currently has
	 *             opened has already been discharged.
	 */
	@controllers.PUBLICAPI
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
	@controllers.PUBLICAPI
	public void approveDiagnose(DiagnoseIN selected) throws ApproveDiagnoseException {
		if (isValidDiagnose(selected))
			((Diagnose) selected).approve();
		else
			throw new ApproveDiagnoseException("Trying to approve a diagnose that does not exist!");
	}

	/**
	 * Disapprove diagnose! if an exception is thrown the domain remains unchanged.
	 * @param selected
	 * The selected diagnose.
	 * @param newDiag
	 * The replacement diagnose.
	 * @param newComplaints
	 * The replacement complaint
	 * @throws ApproveDiagnoseException
	 * The passed diagnose is not marked for second opinion
	 */
	public void disapproveDiagnose(DiagnoseIN selected, String newDiag, String newComplaints) throws ApproveDiagnoseException 	{
		((Diagnose)selected).disapprove(newDiag, newComplaints);
		
	}

	/**
	 * Use to display pending diagnosis for the doctor who currently is logged
	 * on.
	 */
	@controllers.PUBLICAPI
	public Collection<DiagnoseIN> getPendingDiagnosis() {
		return cpfc.getPatientFile().getPendingDiagnosisForIN((Doctor) lc.getUser());
	}

	private boolean isValidDiagnose(DiagnoseIN d) {
		return cpfc.getPatientFile().getAllDiagnosisIN().contains(d);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
