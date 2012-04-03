package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import users.Doctor;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * A controller that allows you to open patientfiles.
 */
@controllers.PUBLICAPI
public class ConsultPatientFileController extends NeedsLoginController
{
	private PatientFile pf;

	/**
	 * Default constructor.
	 */
	@controllers.PUBLICAPI
	public ConsultPatientFileController(LoginController lc)
			throws InvalidHospitalException, InvalidLoginControllerException {
		super(lc);
	}

	/**
	 * Use to list all patient files of patients who have not yet been
	 * discharged.
	 */
	@controllers.PUBLICAPI
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new ArrayList<PatientFileIN>(hospital.getPatientFileManager()
				.getAllPatientFiles());
	}

	/**
	 * Use to open a patient file you may have selected from the
	 * getActivePatientFiles() method.
	 */
	@controllers.PUBLICAPI
	public void openPatientFile(PatientFileIN pfdto) {
		if(pf!=null)
			throw new IllegalStateException("controller already has a patientfile open");
		if (pfdto instanceof PatientFile)
			this.pf = (PatientFile) pfdto;
		else
			throw new IllegalArgumentException(pfdto
					+ " is not a valid patient file");
	}

	/**
	 * @return The patient file that is opened with this ConsultPatientFileController.
	 */
	@controllers.PUBLICAPI
	public PatientFileIN getPatientFile() {
		return this.pf;
	}
	

	/**
	 * Closes the patient file this doctor has opened.
	 */
	@controllers.PUBLICAPI
	public void closePatientFile() {
		this.hospital = null;
		this.pf = null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
