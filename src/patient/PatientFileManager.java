package patient;

import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import system.Location;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.PatientFileIN;
import exceptions.DischargePatientException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.NoSuchPatientException;

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
	 * @throws InvalidPatientFileException
	 * @throws NoSuchPatientException
	 */
	public void checkIn(PatientFile patientFile) throws InvalidPatientFileException {
		if (!isValidPatientFile(patientFile) || !patientFile.isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file was given to the checkin method in patient file manager!");
		patientFile.checkIn();
	}

	private boolean isValidPatientFile(PatientFile patientFile) {
		return patientFile != null && patientFiles.contains(patientFile);
	}

	/**
	 * This method will checkout and discharge a patient.
	 * 
	 * @param patientFile
	 *            The patientfile of the patient who is checking out.
	 * @throws DischargePatientException
	 * @throws InvalidPatientFileException
	 */
	public void checkOut(PatientFile patientFile) throws DischargePatientException, InvalidPatientFileException {
		if (!isValidPatientFile(patientFile))
			throw new InvalidPatientFileException(
					"Invalid patient file was given to the checkout method in PatientFileManager!");
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
	public PatientFile registerPatient(String name, Location location) throws InvalidNameException,
			InvalidPatientFileException {
		if (this.containsFileOf(name))
			throw new InvalidPatientFileException("Patient already exists in hospital!");
		PatientFile pf = new PatientFile(new Patient(name));
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
		for (PatientFile pf : this.patientFiles)
			if (pf.getPatient().getName().equals(name))
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
	 * Use to get all patientfiles in the controllers.
	 */
	public Collection<PatientFileIN> getPatientFileINs() {
		Collection<PatientFileIN> rv = new LinkedList<PatientFileIN>();
		for (PatientFile pf : this.patientFiles)
			rv.add(pf);
		return rv;
	}

	public static final Filter active = new Filter()
	{

		@Override
		public <T> boolean allows(T arg) {
			if (arg instanceof PatientFile)
				if (!((PatientFile) arg).isDischarged())
					return true;
			return false;
		}
	};
	public static final Filter inactive = new Filter()
	{

		@Override
		public <T> boolean allows(T arg) {
			if (arg instanceof PatientFile)
				if (((PatientFile) arg).isDischarged())
					return true;
			return false;
		}
	};
}
