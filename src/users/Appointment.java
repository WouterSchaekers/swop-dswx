package users;

import java.util.Date;


public class Appointment {
	public Appointment(Patient patient, Doctor doctor, Date date){
		setPatient(patient);
		setDoctor(doctor);
		setDate(date);
	}
	private Patient patient;
	public void setPatient(Patient patient){
		this.patient = patient;
	}
	public Patient getPatient() {
		return patient;
	}
	private Doctor doctor;
	public void setDoctor(Doctor doctor){
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
}
