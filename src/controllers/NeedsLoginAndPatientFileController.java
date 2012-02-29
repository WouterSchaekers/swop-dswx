package controllers;

import system.HospitalState;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

//TODO: Stefaan maakt prentje en commentaar.
public abstract class NeedsLoginAndPatientFileController extends
		NeedsLoginController
{
	protected PatientFileOpenController pfoc;

	public NeedsLoginAndPatientFileController(HospitalState hospitalState,
			LoginController controller, PatientFileOpenController pfoc)
			throws InvalidLoginControllerException,
			InvalidHospitalStateException, InvalidPatientFileOpenController {
		super(hospitalState, controller);
		if (!isValidPatientFileOpenController(pfoc)) {
			throw new InvalidPatientFileOpenController("");
		}
		this.pfoc = pfoc;
	}

	boolean isValidPatientFileOpenController(PatientFileOpenController pfoc) {
		if (pfoc == null)
			return false;
		if (this.pfoc != null)
			return this.pfoc.equals(pfoc);
		return true;
	}

	/*
	 * 
	 */
	void checkValidity(LoginController loginc, PatientFileOpenController pfoc)
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController {
		super.checkValidity(loginc);
		if (!isValidPatientFileOpenController(pfoc))
			throw new InvalidPatientFileOpenController("abra");
	}
}