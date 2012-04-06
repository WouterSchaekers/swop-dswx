package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import exceptions.ApproveDiagnoseException;
import exceptions.DischargePatientException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Use this controller to give a second opinion on a diagnose of a patient.
 */
@controllers.PUBLICAPI
public class EvaluateDiagnoseController extends NeedsLoginController
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
	public EvaluateDiagnoseController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidConsultPatientFileController,
			DischargePatientException {
		super(lc);
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
	public void approveDiagnose(DiagnoseIN selected) throws ApproveDiagnoseException  {
			((Diagnose) selected).approveBy((Doctor) loginController_.getUser());
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
	@controllers.PUBLICAPI
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
		Collection<DiagnoseIN> pending = new ArrayList<DiagnoseIN>();
		for(PatientFile file:hospital.getPatientFileManager().getAllPatientFiles())
		{
			pending.addAll(file.getPendingDiagnosisForIN((DoctorIN) loginController_.getUser()));
		}
		return pending;
	}


	/**
	 * True if the given user is a Doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
