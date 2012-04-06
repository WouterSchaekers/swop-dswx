package controllers;

import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * This class is to be extended by all controllers that need both a login
 * controller and a consult patient file controller to work.
 */
abstract class NeedsLoginAndPatientFileController extends NeedsLoginController
{
	protected ConsultPatientFileController consultPatientFileController_;

	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that needs a patient file-
	 *            and login controller.
	 * @param consultPatientFileController
	 *            The consult patient file controller that the owner of the new
	 *            controller has.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller seems to be invalid or
	 *             unauthorized to call functions on the newly created
	 *             controller.
	 * @throws InvalidHospitalException
	 * @throws InvalidConsultPatientFileController
	 *             If the given consult patient file controller seems to be
	 *             invalid for the user that's trying to create new controller.
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	NeedsLoginAndPatientFileController(LoginController loginController,
			ConsultPatientFileController consultPatientFileController) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidConsultPatientFileController {
		super(loginController);
		if (!isValidPatientFileOpenController(consultPatientFileController)) {
			throw new InvalidConsultPatientFileController("The given consult patientfile controller is invalid!");
		}
		this.consultPatientFileController_ = consultPatientFileController;
	}

	/**
	 * Checks if the given consult patient file controller is a valid one.
	 * 
	 * @return True if the given consult patient file controller is valid.
	 */
	boolean isValidPatientFileOpenController(ConsultPatientFileController consultPatientFileController) {
		if (consultPatientFileController == null)
			return false;
		if(!this.loginController_.equals(consultPatientFileController.loginController_))
			return false;
		if (this.consultPatientFileController_ != null)
			return this.consultPatientFileController_.equals(consultPatientFileController);
		return true;
	}
}