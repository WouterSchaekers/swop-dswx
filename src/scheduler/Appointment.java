package scheduler;

import java.util.Date;
import patient.PatientFile;
import resources.Resource;
import java.util.Collection;

public class Appointment
{
	public Appointment(PatientFile patient, Collection<Resource> res, Date date, int appointmentDuration) {
		setPatientFile(patient);
		setResource(res);
		setDate(date);
		setAppointmentDuration(appointmentDuration);
	}

	private PatientFile patient;

	public void setPatientFile(PatientFile patient) {
		this.patient = patient;
	}

	public PatientFile getPatientFile() {
		return patient;
	}

	private Collection<Resource> resources;

	public void setResource(Collection<Resource> res) {
		this.resources = res;
	}

	public Collection<Resource> getResource() {
		return this.resources;
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
	
	private int appointmentDuration;

	public int getAppointmentDuration() {
		return appointmentDuration;
	}

	public void setAppointmentDuration(int appointmentDuration) {
		this.appointmentDuration = appointmentDuration;
	}
}
