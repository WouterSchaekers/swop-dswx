package scheduler;

import java.util.Date;
import patient.PatientFile;
import users.Doctor;

public class Appointment
{
	public Appointment(PatientFile patient, Doctor doctor, Date date) {
		setPatientFile(patient);
		setDoctor(doctor);
		setDate(date);
	}

	private PatientFile patient;

	public void setPatientFile(PatientFile patient) {
		this.patient = patient;
	}

	public PatientFile getPatientFile() {
		return patient;
	}

	private Doctor doctor;

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isOpen() {
		return false;
	}
}
