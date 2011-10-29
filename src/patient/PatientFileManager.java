package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import medicaltest.Result;

/**
 * This class can be used to manage and interact with patient files.
 */
public class PatientFileManager
{
	/**
	 * We use this hashmap to keep track of all patientfiles.
	 * Key: the patientfile
	 * Value: boolean: open or not(?)
	 */
	private HashMap<PatientFile, Boolean> patientFiles = new HashMap<PatientFile, Boolean>();

	/**
	 * This method adds a patientfile to the database of this manager.
	 * 
	 * @param pf
	 *            The patientfile that needs to be added.
	 */
	//TODO
	public void openPatientFile(){
		
	}
	
	public void checkIn(PatientFile patientFile){
		this.patientFiles.put(patientFile, true);
	}
	
	public void checkOut(PatientFile patientFile){
		this.patientFiles.put(patientFile, false);
	}
	
	/**
	 * This method registers a new patient in the hospital database.
	 * 
	 * @param name
	 *            The name of the new patient.
	 * @return The patientfile for the new patient.
	 */
	public void registerPatient(PatientFile patientFile) {
		patientFiles.put(patientFile, false);
	}
	
	/**
	 * @return All the patientfiles this patientfilemanager manages.
	 */
	public ArrayList<PatientFile> getAllPatientFiles() {
		ArrayList<PatientFile> allPatientFiles = new ArrayList<PatientFile>();
		Collection<PatientFile> patientFilesCollection = this.patientFiles.keySet();
		for(PatientFile patientFile : patientFilesCollection){
			allPatientFiles.add(patientFile);
		}
		return allPatientFiles;
	}

	/**
	 * This method checks if the patientfile of a certain patient exists.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return True if the file exists.
	 */
	public boolean containsFileOf(PatientFile patientFile) {
		boolean contains = false;
		Collection<PatientFile> patientFilesCollection = this.patientFiles.keySet();
		for(PatientFile currentPatientFile : patientFilesCollection){
			if(patientFile == currentPatientFile){
				contains = true;
			}
		}
		return contains;
	}

	/**
	 * @return A list of all patients (as String)
	 */
	public String getPatientFilesAsString() {
		String returnval = "";
		Collection<PatientFile> patientFilesCollection = this.patientFiles.keySet();
		for (PatientFile patientFile : patientFilesCollection)
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
	 * @return All results from <B>all</B> patients this patientfilemanager manages.
	 */
	public Collection<Result> getAllResults() {
		Collection<PatientFile> patientFiles = this.getAllPatientFiles();
		Collection<Result> results = new ArrayList<Result>();
		
		for (PatientFile pf : patientFiles) {
			Collection<Result> patientResults = pf.getAllResults();
			for (Result res : patientResults)
				results.add(res);
		}
		
		return results;
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
	
}
