package users;

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
		return usertype.Nurse;
	}
}
