package controllers;

import java.util.Collection;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Nurse;

/**
 * This class is used to interact with a patientfilemanager.
 */
public class CheckinController extends superController
{
	private PatientFileManager pfm = null;

	public CheckinController(LoginController lc, PatientFileManager pfm) {
		super(lc);
		if (!(lc.getUser() instanceof Nurse))
			throw new IllegalArgumentException(
					"User registering the patient is not a nurse!");
		this.pfm = pfm;
	}

	public Collection<PatientFile> getAllRegisteredPatients() {
		return pfm.getAllPatientFiles();
	}

	public void checkIn(PatientFile patientFile) {
		pfm.checkIn(patientFile);
	}

	public void signUpNewPatient(PatientFile patientFile) {
		pfm.registerPatient(patientFile);
	}

}
