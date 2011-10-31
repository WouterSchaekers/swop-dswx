package scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import resources.Resource;
import patient.PatientFile;

public class Scheduler
{
	// all scheduled resources
	private TreeMap<TimePoint, Collection<Appointment>> resources;
	// all available resources
	private Collection<Resource> availableResources;
	// 1 hour after admission <...>
	private final int TIMEAFTERCURRENTDATE = 60 * 60 * 1000;
	private Calendar curDate;

	/**
	 * Default constructor will initialise all fields.
	 */
	public Scheduler() {
		resources = new TreeMap<TimePoint, Collection<Appointment>>();
		availableResources = new ArrayList<Resource>();
		curDate = new GregorianCalendar(2011, 11, 8, 8, 0);
	}

	/**
	 * @return The current system time in millis.
	 */
	public long getTime() {
		return curDate.getTimeInMillis();
	}

	/**
	 * Will add an appointment to the time table.
	 * 
	 * @param patient
	 *            The patient who the appointment is for.
	 * @param res
	 *            The resource scheduled for the appointment
	 * @param duration
	 *            The length of the appointment
	 * @return The appointment.
	 */
	public Appointment addAppointment(PatientFile patient, Collection<Resource> res, int duration) {
		TimePoint startPointFree = findFreeSlot(res, duration);
		
		Date endDate = new Date(startPointFree.getDate().getTime() + duration);
		TimeType stoptype = TimeType.stop;
		TimePoint stopPoint = new TimePoint(stoptype, endDate);
		
		Appointment newAppointment = new Appointment(patient, res, startPointFree, stopPoint);
		Collection<Appointment> oldAppointments = this.resources.get(startPointFree);

		if (oldAppointments == null) {
			oldAppointments = new ArrayList<Appointment>();
		}

		oldAppointments.add(newAppointment);
		this.resources.put(startPointFree, oldAppointments);
		return newAppointment;
	}

	/**
	 * This function will get the first free slot for an appointment.
	 * 
	 * @param res
	 *            All the resources that should be in the time table (incl new
	 *            resource)
	 * @param duration
	 *            Length of the reservation.
	 * @return The first free slot for an appointment of duration duration.
	 */
	private TimePoint findFreeSlot(Collection<Resource> res, int duration) {
		Collection<Date> allDates = this.resources.keySet();
		
		return null;
	}

	/**
	 * @return The minimum amount of time that needs to be between the admission
	 *         and execution of an appointment.
	 */
	public int getTimeBuffer() {
		return TIMEAFTERCURRENTDATE;
	}

}