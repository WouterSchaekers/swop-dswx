package controllers;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import users.Doctor;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * A controller that allows you to open patient files.
 */
@controllers.PUBLICAPI
public class ConsultPatientFileController extends NeedsLoginController
{
	private PatientFile patientFile_;

	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to consult a
	 *            patient file.
	 * @throws InvalidLoginControllerException
	 *             If the user to whom the given login controller belongs to is
	 *             not a doctor or invalid in any other way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public ConsultPatientFileController(LoginController loginController) throws InvalidHospitalException,
			InvalidLoginControllerException {
		super(loginController);
	}

	/**
	 * Use to list all patient files of patients who have ever been registered
	 * in this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new LinkedList<PatientFileIN>(hospital.getPatientFileManager().getAllPatientFiles());
	}

	/**
	 * Use to open the given patient file.
	 * 
	 * @param patientFile
	 *            The patient file you would like to open.
	 */
	@controllers.PUBLICAPI
	public void openPatientFile(PatientFileIN patientFile) {
		if (patientFile_ != null)
			throw new IllegalStateException("controller already has a patientfile open");
		if (patientFile instanceof PatientFile)
			this.patientFile_ = (PatientFile) patientFile;
		else
			throw new IllegalArgumentException(patientFile + " is not a valid patient file");
	}

	/**
	 * @return The patient file that is opened with this
	 *         ConsultPatientFileController.
	 */
	@controllers.PUBLICAPI
	public PatientFileIN getPatientFile() {
		return this.patientFile_;
	}

	/**
	 * Closes the patient file this doctor has opened.
	 */
	@controllers.PUBLICAPI
	public void closePatientFile() {
		this.hospital = null;
		this.patientFile_ = null;
	}

	/**
	 * @return True if the given user is a doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
