package users;

import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.Appointment;
import scheduler.Scheduler;

public class Nurse extends User
{
	public Nurse(String name) {
		super(name);
	}

	/**
	 * 
	 * @param patient
	 */

//	public Appointment makeAppointment(PatientFile patient, Doctor doctor, int appointmentDuration, Scheduler appointmentScheduler) {
//		Appointment appointment = appointmentScheduler.addAppointment(patient, doctor, appointmentDuration);
//		return appointment;
//	}

	@Override
	public usertype type() {
		// TODO Auto-generated method stub
		return usertype.Nurse;
	}
}
