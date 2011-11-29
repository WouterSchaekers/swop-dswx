package controllers;

import java.util.Collection;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Doctor;
import users.User;

/**
 * This class can be used to consult and interact with a patientfile.
 */
public class ConsultPatientFileController
{
	private PatientFileOpenController pfoc; // the patientfile associated with
											// this controller
	private PatientFileManager pfm; // the pfm of this controller

	/**
	 * Default constructor.
	 * 
	 * @param pfm
	 *            The patientfilemanager for this controller.
	 * @param u
	 *            The user to whom this controller belongs to.
	 */
	public ConsultPatientFileController(PatientFileManager pfm, User u,
			LoginController lc, PatientFileOpenController pfoc) {
		if (!(u instanceof Doctor))
			throw new IllegalArgumentException("User" + u.getName()
					+ " is not a doctor");
		if (!(pfoc.getLoginController().getUser().equals(lc.getUser())))
			throw new IllegalStateException("Invalid acces!");

		this.pfm = pfm;
		this.pfoc = pfoc;
	}

	public PatientFileOpenController getPfoc() {
		return this.pfoc;
	}

	/**
	 * @return all registered patients in the patientfilemanager of this
	 *         checkincontroller.
	 */
	public Collection<PatientFile> getAllRegisteredPatients() {
		return pfm.getAllPatientFiles();
	}

}
