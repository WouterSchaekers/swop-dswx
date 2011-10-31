package scheduler;

import patient.PatientFile;
import resources.Resource;
import java.util.Collection;

/**
 * This class will represent an appointment (== a scheduled resource)
 */
public class Appointment
{
	private TimePoint start;
	private TimePoint stop;
	private PatientFile patient;
	private Collection<Resource> resources;

	
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
	public Appointment(PatientFile patient, Collection<Resource> res, TimePoint start, TimePoint stop) {
		setPatientFile(patient);
		setResource(res);
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
	 * This method allows to change the associated resource of this appointment.
	 * @param res
	 * The new resources for this appointment.
	 */	
	public void setResource(Collection<Resource> res) {
		this.resources = res;
	}

	/**
	 * @return The resource of this appointment.
	 */
	public Collection<Resource> getResource() {
		return this.resources;
	}

	/**
	 * @return The start timepoint of this appointment.
	 */
	public TimePoint getStart() {
		return stop;
	}

	/**
	 * @return The stop timepoint of this appointment.
	 */
	public TimePoint setStop() {
		return stop;
	}
	
	/**
	 * This method changes the start time of this appointment.
	 */
	public void setSart(TimePoint s) {
		this.start = s;
	}
	
	/**
	 * This method changes the stop time of this appointment.
	 */
	public void setStop(TimePoint s) {
		this.stop = s;
	}
	
	/**
	 * @return The duration of this appointment.
	 */
	public long getAppointmentDuration() {
		return start.getDate().getTime() - stop.getDate().getTime(); 
	}

}
