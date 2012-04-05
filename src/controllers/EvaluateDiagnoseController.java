package controllers;

import java.util.Collection;
import patient.Diagnose;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import exceptions.ApproveDiagnoseException;
import exceptions.DischargePatientException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidConsultPatientFileController;

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
	 * @throws InvalidConsultPatientFileController
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public EvaluateDiagnoseController(LoginController lc, ConsultPatientFileController cpfc)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidConsultPatientFileController,
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
	 *             of whom the attending doctor required a second opinion.
	 */
	@controllers.PUBLICAPI
	public void approveDiagnose(DiagnoseIN selected) throws ApproveDiagnoseException {
		if (isValidDiagnose(selected))
			((Diagnose) selected).approveBy((Doctor) loginController_.getUser());
		else
			throw new ApproveDiagnoseException("Trying to approve a diagnose that does not exist!");
	}

	/**
	 * Disapproves the selected diagnose and replaces it with a new one.
	 * 
	 * @param selected
	 *            The selected diagnose.
	 * @param newDiag
	 *            The replacement for the selected diagnose.
	 * @return 
	 * @throws ApproveDiagnoseException
	 *             If the disapprove of the selected diagnose failed due to
	 *             authorization reasons, or invalid parameters.
	 */
	public DiagnoseIN disapproveDiagnose(DiagnoseIN selected, String newDiag) throws ApproveDiagnoseException {
		return ((Diagnose) selected).disapproveBy(newDiag, selected.getComplaintsIN(),
				(DoctorIN) (this.loginController_.getUserIN()));
	}

	/**
	 * Use to display pending diagnosis for the doctor who currently is logged
	 * on.
	 */
	@controllers.PUBLICAPI
	public Collection<DiagnoseIN> getPendingDiagnosis() {
		return consultPatientFileController_.getPatientFile().getPendingDiagnosisForIN(
				(Doctor) loginController_.getUser());
	}

	/**
	 * @return True if the given diagnose is one that is pending.
	 */
	private boolean isValidDiagnose(DiagnoseIN d) {
		return consultPatientFileController_.getPatientFile().getAllDiagnosisIN().contains(d);
	}

	/**
	 * True if the given user is a Doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
