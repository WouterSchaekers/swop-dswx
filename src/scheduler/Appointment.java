package scheduler;

import patient.PatientFile;
import task.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * This class will represent an appointment (== a scheduled resource)
 */
public class Appointment
{
	private PatientFile patient;
	private HashMap<TimePoint, Resource> resources;
	private Date date;
	private long duration;
	
	/**
	 * Default constructor.
	 * 
	 * @param patient
	 *            The patientfile of the patient to whom this appointment
	 *            belongs to.
	 * @param res
	 *            All resources needed for this appointment.
	 * @param date
	 *            The date and time on which this appointment is to take place.
	 */
	public Appointment(PatientFile patient, HashMap<TimePoint, Resource> res, Date date, long dur) {
		setPatientFile(patient);
		this.resources = res;
		this.date = date;
		this.duration = dur;
	}
	
	/**
	 * This method allows to change the associated patientfile of this appointment.
	 * @param patient
	 * The new patientfile for this appointment.
	 */
	public void setPatientFile(PatientFile patient) {
		this.patient = patient;
	}

	/**
	 * @return The patientfile for this appointment.
	 */
	public PatientFile getPatientFile() {
		return patient;
	}

	/**
	 * @return The resource of this appointment.
	 */
	public Collection<Resource> getResource() {
		return this.resources.values();
	}
	
	public HashMap<TimePoint, Resource> getScheduling(){
		return resources;
	}
	
	/**
	 * @return The duration of this appointment.
	 */
	public long getAppointmentDuration() {
		return duration; 
	}
	
	public Date getDate(){
		return date;
	}

}
