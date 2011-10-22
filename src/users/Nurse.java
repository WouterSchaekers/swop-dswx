package users;

import patient.PatientFile;
import scheduler.Appointment;

public class Nurse extends User
{
	public Nurse(String name) {
		super(name);
	}


	public void registerPatient(PatientFile patient) {
		
		//Database.addPatient(patient);
	}

	public Appointment makeAppointment(PatientFile patient, Doctor doctor) {
		return null;

	}
}
