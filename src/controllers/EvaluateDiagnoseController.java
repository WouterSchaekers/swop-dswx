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
public class EvaluateDiagnoseController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The login controller of the user that wants to evaluate
	 *            diagnosis with this new controller.
	 * @param cpfc
	 *            The consult patient file controller that contains the patient
	 *            file of the patient of whom you want to evaluate diagnosis
	 *            from.
	 * @throws InvalidLoginControllerException
	 *             If the user that is trying to execute method calls on this
	 *             controller is not a doctor or if the given login controller
	 *             is invalid in any other way.
	 * @throws DischargePatientException
	 *             If the patient that's in the consult patient file controller
	 *             is currently discharged.
	 * @throws InvalidHospitalException
	 * @throws InvalidPatientFileOpenController
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public EvaluateDiagnoseController(LoginController lc, ConsultPatientFileController cpfc)
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
	 *             If the selected diagnose does not require a second opinion or
	 *             the user that's calling this method is not the given doctor
	 *             of whom the attending doctor required a second opinion from.
	 */
	@controllers.PUBLICAPI
	public void approveDiagnose(DiagnoseIN selected) throws ApproveDiagnoseException {
		if (isValidDiagnose(selected))
			((Diagnose) selected).approve();
		else
			throw new ApproveDiagnoseException("Trying to approve a diagnose that does not exist!");
	}

	/**
	 * Disapprove diagnose! if an exception is thrown the domain remains
	 * unchanged.
	 * 
	 * @param selected
	 *            The selected diagnose.
	 * @param newDiag
	 *            The replacement diagnose.
	 * @param newComplaints
	 *            The replacement complaint
	 * @throws ApproveDiagnoseException
	 *             The passed diagnose is not marked for second opinion
	 */
	public void disapproveDiagnose(DiagnoseIN selected, String newDiag) throws ApproveDiagnoseException {
		((Diagnose) selected).disapprove(newDiag, selected.getComplaintsIN());
	}

	/**
	 * Use to display pending diagnosis for the doctor who currently is logged
	 * on.
	 */
	@controllers.PUBLICAPI
	public Collection<DiagnoseIN> getPendingDiagnosis() {
		return consultPatientFileController_.getPatientFile().getPendingDiagnosisForIN((Doctor) loginController_.getUser());
	}

	private boolean isValidDiagnose(DiagnoseIN d) {
		return consultPatientFileController_.getPatientFile().getAllDiagnosisIN().contains(d);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
