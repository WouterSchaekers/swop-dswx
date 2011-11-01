package patient;

import java.util.ArrayList;
import java.util.Collection;
import resources.Result;

/**
 * This class can be used to manage and interact with patient files.
 */
public class PatientFileManager
{

	private ArrayList<PatientFile> patientFiles = new ArrayList<PatientFile>();
	
	/**
	 * This method will check a patient in into the system.
	 * @param patientFile
	 * The patientfile of the patient who needs to be checked in.
	 */
	public void checkIn(PatientFile patientFile){
		patientFile.checkIn();
	}
	
	/**
	 * This method will checkout and discharge a patient.
	 * @param patientFile
	 * The patientfile of the patient who is checking out.
	 */
	public void checkOut(PatientFile patientFile){
		patientFile.discharge();
	}
	
	/**
	 * This method registers a new patient in the hospital database.
	 * 
	 * @param name
	 *            The name of the new patient.
	 * @return The patientfile for the new patient.
	 */
	public void registerPatient(String name) {
		patientFiles.add(new PatientFile(name));
	}
	
	/**
	 * @return All the patientfiles this patientfilemanager manages.
	 */
	public ArrayList<PatientFile> getAllPatientFiles() {
		return patientFiles;
	}

	/**
	 * This method checks if the patientfile of a certain patient exists.
	 * 
	 * @param patientFile
	 * the patientfile to check for existence.
	 * @return True if the file exists in this patient file manager.
	 */
	public boolean containsFileOf(PatientFile patientFile) {
		return patientFiles.contains(patientFile);
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
	 * @return The amount of patientfiles this patientfilemanager manages.
	 */
	public int getPatientFileSize() {
		return patientFiles.size();
	}
	
	/**
	 * This function can be used to get the testresult of a certain patient.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return A collection of all the testresults of this patient.
	 */
	public Collection<Result> getAllResultsFrom(PatientFile patientFile) {
		return patientFile.getAllResults();
	}
	
	
	/**
	 * This method will fetch the patientfile from a certain patient.
	 * @param name
	 * The name of the patient.
	 * @return The patientfile of the requested patient
	 * or null if no such patient is found.
	 */
	public PatientFile getPatientFileFrom(String name) {
		for(PatientFile pf: patientFiles) 
			if (pf.getName().equalsIgnoreCase(name))
				return pf;
		return null;
	}
	
	/**
	 * 
	 * @param pf
	 * @return
	 */
	public boolean patientIsDischarged(PatientFile pf) {
		return pf.isDischarged();
	}
}
