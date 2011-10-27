package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import medicaltest.Result;

/**
 * This class can be used to manage and interact with patient files.
 * 
 * @author Stefaan
 * 
 */
public class PatientFileManager
{
	private HashMap<String, PatientFile> patientFiles = new HashMap<String, PatientFile>();

	/**
	 * This function allows a doctor to open a patient file.
	 * 
	 * @param name
	 *            name of the patient
	 */
	public PatientFile openPatientFile(String name) {
		return patientFiles.get(name);
	}

	/**
	 * This method adds a patientfile to the database of this manager.
	 * 
	 * @param pf
	 *            The patientfile that needs to be added.
	 */
	public void addPatientFile(PatientFile pf) {
		patientFiles.put(pf.getName(), pf);
	}
	
	/**
	 * This method registers a new patient in the hospital database.
	 * 
	 * @param name
	 *            The name of the new patient.
	 * @return The patientfile for the new patient.
	 */
	public PatientFile registerPatient(String name) {
		PatientFile returnval = new PatientFile(name, this);
		returnval.checkin();
		patientFiles.put(name, returnval);
		return new PatientFile(name, this);
	}

	/**
	 * This method checks a patient, who's been already registered, in.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return The patientfile of the patient.
	 */
	public PatientFile checkin(String name) {
		PatientFile returnval = openPatientFile(name);
		returnval.checkin();
		return returnval;
	}
	
	/**
	 * @return All the patientfiles this patientfilemanager manages.
	 */
	public Collection<PatientFile> getAllPatientFiles() {
		return patientFiles.values();
	}

	/**
	 * This method checks if the patientfile of a certain patient exists.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return True if the file exists.
	 */
	public boolean containsFileOf(String name) {
		return patientFiles.containsKey(name);
	}

	/**
	 * @return A list of all patients (as String)
	 */
	public String getPatientFilesAsString() {
		Collection<PatientFile> cpf = this.patientFiles.values();
		String returnval = "";

		for (PatientFile pf : cpf)
			returnval += "* " + pf + "\n";

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
	public Collection<Result> getAllResultsFrom(String name) {
		return patientFiles.get(name).getAllResults();
	}
	
}
