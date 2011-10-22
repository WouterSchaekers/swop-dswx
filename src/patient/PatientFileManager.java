package patient;

import java.util.ArrayList;
import java.util.HashMap;
import users.Doctor;

/**
 * This class can be used to manage and interact with patient files.
 * @author Stefaan
 *
 */
public class PatientFileManager
{
	public static HashMap<Doctor,PatientFile> openFiles = new HashMap<Doctor,PatientFile>();
	public static ArrayList<PatientFile> patientFiles = new ArrayList<PatientFile>();
	
	/**
	 * This function allows a doctor to open a patient file.
	 * 
	 * @param doc
	 *            The doctor that wants to open a file.
	 * @param pfile
	 *            The patient file the doctor wants to open.
	 * @throws IllegalStateException
	 *             If the doctor already has opened another file without closing
	 *             it.
	 */
	public void openPatientFile(Doctor doc, PatientFile pfile) throws IllegalStateException{
		if(openFiles.containsKey(doc)) // the doc does not have another file open yet.
			openFiles.put(doc, pfile);
	
		throw new IllegalStateException("Only one file per doctor can be opened at a time!");
	}

	/**
	 * This function allows a doctor to close a file he already had opened.
	 * 
	 * @param doc
	 *            The doctor that wants to close the file.
	 * @param pfile
	 *            The file the doctor wants to close.
	 * @throws IllegalArgumentException
	 *             If the specified file is not currently opened by the doctor.
	 */
	public void closePatientFile(Doctor doc, PatientFile pfile) throws IllegalArgumentException {
		if(doctorHasFileOpened(doc,pfile)) { 
			openFiles.remove(doc);
		}
		throw new IllegalArgumentException("A doctor can only close a file that he's already opened!!");
	}

	/**
	 * This function checks if Doctor doc has PatientFile file opened right now.
	 * 
	 * @param doc
	 *            The doctor who needs checking.
	 * @param file
	 *            The file the doctor alledgedly has opened up.
	 * @return True if doc currently has opened file.
	 */
	public boolean doctorHasFileOpened(Doctor doc, PatientFile file) {
		return openFiles.containsKey(doc) && openFiles.get(doc).equals(file);
	}
	
	/**
	 * This function allows someone to request the patient file of a patient.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @return The patient file of that patient.
	 */
	public PatientFile getPatientFile(String name) {
		return null;
	}
	
}
