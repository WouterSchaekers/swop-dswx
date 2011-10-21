package users;

import java.util.ArrayList;
import patient.Patient;
import scheduler.Appointment;

public class Nurse extends User
{
	public Nurse(String name) {
		super(name);
	}


	public void registerPatient(Patient patient) {
		
		//Database.addPatient(patient);
	}

	public Appointment makeAppointment(Patient patient, Doctor doctor) {
		return null;

	}
}
