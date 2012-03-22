package controllers;

import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

/**
 * This class is the superclass of all controller that need both a logincontroller and a patientfilecontroller.
 */
public abstract class NeedsLoginAndPatientFileController extends
		NeedsLoginController
{
	protected ConsultPatientFileController pfoc;

	/**
	 * Default constructor.
	 * @param hospital
	 * The hospital state for this controller.
	 * @param controller
	 * @param pfoc
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 * @throws InvalidPatientFileOpenController
	 */
	public NeedsLoginAndPatientFileController(LoginController lc, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc);
		if (!isValidPatientFileOpenController(pfoc)) {
			throw new InvalidPatientFileOpenController("");
		}
		this.pfoc = pfoc;
	}

	boolean isValidPatientFileOpenController(ConsultPatientFileController pfoc) {
		if (pfoc == null)
			return false;
		if (this.pfoc != null)
			return this.pfoc.equals(pfoc);
		return true;
	}
}