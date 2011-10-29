package controllers;

import java.util.Collection;
import medicaltest.Result;
import patient.Diagnosis;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Doctor;
import users.User;

/**
 * This class can be used to consult and interact with a patientfile.
 */
public class ConsultPatientFileController
{
	private PatientFile file; // the patientfile associated with this controller
	private PatientFileManager pfm; // the pfm of this controller

	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	@SuppressWarnings("unused")
	private ConsultPatientFileController() {}
	
	
	/**
	 * Default constructor.
	 * @param pfm
	 * The patientfilemanager for this controller.
	 * @param u
	 * The user to whom this controller belongs to.
	 */
	public ConsultPatientFileController(PatientFileManager pfm, User u) {
		if (!(u instanceof Doctor))
			throw new IllegalArgumentException("User" + u.getName()
					+ " is not a doctor");
		this.pfm = pfm;
	}

	/** 
	 * @return The patientfile of this controller.
	 */
	public PatientFile getPatientFile() {
		return this.file;
	}

	/**
	 * @return The diagnosis of the patientfile of this controller.
	 */
	public Collection<Diagnosis> getDiagnosis() {
		return file.getDiagnosis();
	}

	/**
	 * @return The testresults of the patientfile of this controller.
	 */
	public Collection<Result> getAllResults() {
		return this.pfm.getAllResults();

	}

	/**
	 * This method disassociates this controller with its current patientfile.
	 */
	public void closeFile() {
		this.file = null;
	}
	

	/**
	 * @return all registered patients in the patientfilemanager of this checkincontroller.
	 */
	public Collection<PatientFile> getAllRegisteredPatients() {
		return pfm.getAllPatientFiles();
	}

}
