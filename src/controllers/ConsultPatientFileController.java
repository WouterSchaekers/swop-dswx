package controllers;

import java.util.Collection;
import medicaltest.Result;
import patient.Diagnosis;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Doctor;
import users.User;

public class ConsultPatientFileController
{
	private PatientFile file;
	private PatientFileManager pfm;

	public ConsultPatientFileController(PatientFileManager pfm, User u) {
		if (!(u instanceof Doctor))
			throw new IllegalArgumentException("User" + u.getName()
					+ " is not a doctor");
		this.pfm = pfm;
	}

	/**
	 * @pre pfm contains name
	 * @param name
	 * @return
	 */
	public void ConsultPatientFile(PatientFile patientFile) {
		this.file = patientFile;
	}

	public PatientFile getPatientFile() {
		return this.file;
	}

	public Collection<Diagnosis> getDiagnosis() {
		return file.getDiagnosis();
	}

	public Collection<Result> getAllResults() {
		return this.pfm.getAllResults();

	}

	public void closeFile() {
		this.file = null;
	}
}
