package patient;

import java.util.HashMap;
import users.Doctor;

/**
 * This class can be used to manage and interact with patient files.
 * @author Stefaan
 *
 */
public class PatientManager
{
	public static HashMap<Doctor,PatientFile> openFiles = new HashMap<Doctor,PatientFile>();
	
	public void openPatientFile(Doctor doc, PatientFile pfile) throws IllegalStateException{
		if(openFiles.containsKey(doc)) // the doc does not have another file open yet.
			openFiles.put(doc, pfile);
	
		throw new IllegalStateException("Only one file per doctor can be opened at a time!");
	}

	public void closePatientFile(Doctor doc, PatientFile pfile) throws IllegalArgumentException {
		if(openFiles.get(doc).equals(pfile)) { // the doc has this file open
			openFiles.remove(doc);
		}
		throw new IllegalArgumentException("A doctor can only close a file that he's already opened!!");
	}

	public boolean doctorHasFileOpened(Doctor doc, PatientFile file) {
		return openFiles.containsKey(doc) && openFiles.get(doc).equals(file);
	}
}
