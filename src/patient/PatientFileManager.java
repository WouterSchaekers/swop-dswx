package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import system.Location;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.DischargePatientException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;

/**
 * This class can be used to manage and interact with patient files.
 */
public class PatientFileManager
{
	private Collection<PatientFile> patientFiles = new ArrayList<PatientFile>();

	/**
	 * This method will check a patient in into the system.
	 * 
	 * @param patientFile
	 *            The patientfile of the patient who needs to be checked in.
	 */
	public void checkIn(PatientFile patientFile) {
		patientFile.checkIn();
	}

	/**
	 * This method will checkout and discharge a patient.
	 * 
	 * @param patientFile
	 *            The patientfile of the patient who is checking out.
	 * @throws DischargePatientException
	 * @throws InvalidPatientFileException 
	 */
	public void checkOut(PatientFile patientFile)
			throws DischargePatientException, InvalidPatientFileException {
		if(this.patientIsDischarged(patientFile))
			throw new DischargePatientException("Trying to discharge an already discharged patient!");
		patientFile.discharge();
	}

	/**
	 * This method registers a new patient in the hospital database.
	 * 
	 * @param name
	 *            The name of the new patient.
	 * @param location
	 *            The location of this patient.
	 * @return The patientfile for the new patient.
	 * @throws InvalidNameException
	 * @throws InvalidPatientFileException 
	 */
	public PatientFile registerPatient(String name, Location location) throws InvalidNameException, InvalidPatientFileException {	
		if(this.containsFileOf(name))
			throw new InvalidPatientFileException("Patient already exists in hospital!");
		PatientFile pf = new PatientFile(new Patient(name,location));
		patientFiles.add(pf);
		return pf;
	}

	@Basic
	public Collection<PatientFile> getAllPatientFiles() {
		return new ArrayList<PatientFile>(patientFiles);
	}

	/**
	 * Checks if the patientfile of a certain patient exists in this patient
	 * file manager.
	 * 
	 * @return True if the file exists in this patient file manager.
	 */
	private boolean containsFileOf(String name) {
		for(PatientFile pf : this.patientFiles)
			if(pf.getPatient().getName().equals(name))
				return true;
		return false;
	}

	/**
	 * @return A list of all patients (as String)
	 */
	public String getPatientFilesAsString() {
		String returnval = "";

		for (PatientFile patientFile : patientFiles)
			returnval += "* " + patientFile.toString() + "\n";

		return returnval;
	}

	/**
	 * This method will fetch the patientfile from a certain patient.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return The patientfile of the requested patient or null if no such
	 *         patient is found.
	 * @throws InvalidNameException
	 */
	public PatientFile getPatientFileFrom(String name)
			throws InvalidNameException {
		if (!isValidName(name))
			throw new InvalidNameException(
					"Invalid name for query in patientfile database!");
		for (PatientFile pf : patientFiles)
			if (pf.getPatient().getName().equalsIgnoreCase(name))
				return pf;
		return null;
	}

	private boolean patientIsDischarged(PatientFile pf)
			throws InvalidPatientFileException {
		if (this.containsFileOf(pf.getPatient().getName()))
			return pf.isDischarged();
		else
			throw new InvalidPatientFileException("PatientFile not in pfm!");
	}

	/**
	 * @return True if d is a valid name.
	 */
	private boolean isValidName(String n) {
		return !n.equals("");
	}
	
	/**
	 * @return A collection of patients that can be checked in into this hospital.
	 */
	public Collection<PatientFile> getDischargedPatients() {
		Collection<PatientFile> rv = new LinkedList<PatientFile>();
		for(PatientFile pf : this.patientFiles)
			if(pf.isDischarged())
				rv.add(pf);
		return rv;
	}

	/**
	 * This method is used in the warehouse to determine the amount of meals to
	 * be ordered.
	 * 
	 * @return The amount of patients that are currently staying in this
	 *         hospital.
	 */
	public int amountOfActivePatients() {
		return this.getActivePatients().size();
	}

	/**
	 * This method is used in the controllers that should be able to print a
	 * list of patients that are currently staying in this hospital.
	 * 
	 * @return A collection of patients that are currently staying in this
	 *         hospital.
	 */
	public Collection<PatientFile> getActivePatients() {
		Collection<PatientFile> rv = new LinkedList<PatientFile>();
		for (PatientFile pf : this.patientFiles) {
			if (!pf.isDischarged())
				rv.add(pf);
		}
		return rv;
	}

}
