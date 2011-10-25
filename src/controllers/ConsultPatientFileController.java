package controllers;

import java.util.Collection;
import medicaltest.Result;
import diagnosis.Diagnosis;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Doctor;
import users.User;

public class ConsultPatientFileController
{
	private PatientFile file;
	private PatientFileManager pfm;

	public ConsultPatientFileController(PatientFileManager tette, User u) {
		if (!(u instanceof Doctor))
			throw new IllegalArgumentException("User" + u.getName()
					+ " is not a doctor");
		this.pfm = tette;
	}

	/**
	 * @pre pfm contains name
	 * @param name
	 * @return
	 */
	public PatientFile ConsultPatientFile(String name) {
		PatientFile t = pfm.openPatientFile(name);
		this.file = t;
		return t;
	}

	public PatientFile getPatientFile() {
		return this.file;
	}

	public Collection<Diagnosis> getDiagnosis() {
		return file.getDiagnosis();
	}

	public Collection<Result> getAllResults() {
		return this.file.getAllResults();

	}

	public void closeFile() {
		this.file = null;
	}
}
