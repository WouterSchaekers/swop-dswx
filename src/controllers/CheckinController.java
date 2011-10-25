package controllers;

import java.util.Collection;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Nurse;

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

	public void checkin(String name) {
		pfm.checkin(name);
	}

	public void signUpNewPatient(String name) {
		pfm.registerPatient(name);
	}

}
