package patient;

import java.util.Collection;
import java.util.HashMap;

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
	 * 
	 * @param name
	 * @return
	 */
	public void addPatientFile(PatientFile pf) {
		patientFiles.put(pf.getName(), pf);
	}
	
	public PatientFile registerPatient(String name) {
		PatientFile returnval = new PatientFile(name, this);
		returnval.checkin();
		patientFiles.put(name, returnval);
		return new PatientFile(name, this);
	}
	
	public PatientFile checkin(String name) {
		PatientFile returnval = openPatientFile(name);
		returnval.checkin();
		return returnval;
	}
	
	public Collection<PatientFile> getAllPatientFiles() {
		return patientFiles.values();
	}

	public boolean containsFileOf(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
