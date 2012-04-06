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

/**
 * This class can be used to manage patient files.
 */
public class PatientFileManager
{
	private Collection<PatientFile> patientFiles = new LinkedList<PatientFile>();

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
	
	/**
	 * This method will check a patient in into the system.
	 * 
	 * @param patientFile
	 *            The patient file of the patient who needs to be checked in.
	 * @throws InvalidPatientFileException
	 *             if the given patient file has already been checked in or is
	 *             not valid.
	 */
	public void checkIn(PatientFile patientFile) throws InvalidPatientFileException {
		if (!isValidPatientFile(patientFile) )
			throw new InvalidPatientFileException(
					"Invalid patient file was given to the checkin method in patient file manager!");
		patientFile.checkIn();
	}

	/**
	 * This method will checkout and discharge a patient.
	 * 
	 * @param patientFile
	 *            The patient file of the patient who is checking out.
	 * @throws DischargePatientException
	 * @see PatientFile.discharge()
	 * @throws InvalidPatientFileException
	 *             If the given patient file is not a valid patient file.
	 */
	public void checkOut(PatientFile patientFile) throws DischargePatientException, InvalidPatientFileException {
		if (!isValidPatientFile(patientFile))
			throw new InvalidPatientFileException(
					"Invalid patient file was given to the checkout method in PatientFileManager!");
		patientFile.discharge();
	}

	/**
	 * Checks if the patient file of a certain patient exists in this patient
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

	@Basic
	public Collection<PatientFile> getAllPatientFiles() {
		return new ArrayList<PatientFile>(patientFiles);
	}

	/**
	 * Use in controller layer to get all patient files from this patient file
	 * manager.
	 */
	public Collection<PatientFileIN> getPatientFileINs() {
		Collection<PatientFileIN> rv = new LinkedList<PatientFileIN>();
		for (PatientFile pf : this.patientFiles)
			rv.add(pf);
		return rv;
	}

	/**
	 * @return True if the given patient file is a valid one.
	 */
	private boolean isValidPatientFile(PatientFile patientFile) {
		return patientFile != null && patientFiles.contains(patientFile);
	}

	/**
	 * This method registers a new patient in the hospital database.
	 * 
	 * @param name
	 *            The name of the new patient.
	 * @param location
	 *            The location of this patient.
	 * @return The created patient file for the new patient.
	 * @throws InvalidNameException
	 *             If the given name of the new patient is invalid.
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
}
