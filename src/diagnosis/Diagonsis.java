package diagnosis;

import patient.*;
import users.Doctor;

public class Diagonsis
{

	public Diagonsis(Patient patient) {
		this.patient = patient;
	}
	
	private Patient patient = null;
	private String diag = "";
	private boolean isValid = false;
	private boolean secOpFlag = false;
	
	public void editDiag(Doctor changer, String newDiag) throws IllegalStateException {
		PatientManager p = new PatientManager();
		PatientFile pf = this.getPatient().getFile();
		
		if(p.doctorHasFileOpened(changer, pf)) { // the changer has the patientfile open
			if (!isValid) { // diagnosis hasn't been approved -> allow change
				this.diag = newDiag;
				this.isValid = true; // the diagnosis is valid.
			}			
		} else {
			throw new IllegalStateException("Doctor has not opened the associated patientfile!");
		}
	}

	public Patient getPatient() {
		return this.patient;
	}
	
	public boolean getIsValid() {
		return this.isValid;
	}
	
	public void giveSecOp() {
		
	}
	
	public void reqSecOp() {
		
	}
}
