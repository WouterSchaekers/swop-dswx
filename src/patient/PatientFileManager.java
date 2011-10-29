package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import users.Doctor;
import medicaltest.Result;

/**
 * This class can be used to manage and interact with patient files.
 */
public class PatientFileManager
{
	/**
	 * We use this hashmap to keep track of all patientfiles.
	 * Key: the patientfile
	 * Value: Collection of Doctor objects who have the key patientfile opened.
	 */
	private HashMap<PatientFile, Collection<Doctor>> patientFiles = new HashMap<PatientFile, Collection<Doctor>>();

	/**
	 * This method adds a patientfile to the database of this manager.
	 * 
	 * @param pf
	 *            The patientfile that needs to be added.
	 */
	
	/**
	 * This method will associate doc with pf as to indicate doc has pf open.
	 */
	public void openPatientFile(PatientFile pf, Doctor doc){
		Collection<Doctor> c = this.patientFiles.get(pf);
		if(c.equals(null))
			c = (Collection<Doctor>) (new ArrayList<Doctor>());
		c.add(doc);
		this.patientFiles.put(pf, c);		
	}
	
	/**
	 * This method will disassociate doc with pf.
	 * We assume there will be no illegal access of this function. In other words:
	 * doc will ALWAYS be in the collection of the patientfile.
	 */
	public void closePatientFile(PatientFile pf, Doctor doc){
		Collection<Doctor> c = this.patientFiles.get(pf);
		c.remove(doc);
		this.patientFiles.put(pf, c);		
	}
	
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
	public void registerPatient(PatientFile patientFile) {
		patientFiles.put(patientFile, null);
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
	 * @param patientFile
	 * the patientfile to check for existence.
	 * @return True if the file exists.
	 */
	public boolean containsFileOf(PatientFile patientFile) {
		Collection<PatientFile> patientFilesCollection = this.patientFiles.keySet();
		
		for(PatientFile curpf : patientFilesCollection)
			if(patientFile.equals(curpf))
				return true;
			
		return false;
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
