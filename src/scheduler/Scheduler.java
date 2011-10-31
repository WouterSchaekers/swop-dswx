package scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import resources.Resource;
import patient.PatientFile;
import users.Doctor;
import users.Nurse;

public class Scheduler
{
	// all scheduled resources
	private TreeMap<Date, Collection<Appointment>> resources;
	// all available resources
	private Collection<Resource> availableResources;
	// 1 hour after admission <...>
	private final int TIMEAFTERCURRENTDATE = 60 * 60 * 1000;
	private Calendar curDate;

	/**
	 * Default constructor will initialise all fields.
	 */
	public Scheduler() {
		resources = new TreeMap<Date, Collection<Appointment>>();
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
	 * @param patient
	 * The patient who the appointment is for.
	 * @param res
	 * The resource scheduled for the appointment
	 * @param duration
	 * The length of the appointment
	 * @return The appointment.
	 */
	public Appointment addAppointment(PatientFile patient, Collection<Resource> res, int duration) {
		Date freeDate = getFirstDate(res, duration);
		Appointment newAppointment = new Appointment(patient, res, freeDate, duration);
		Collection<Appointment> oldAppointments = this.resources.get(freeDate);
		
		if (oldAppointments == null) {
			oldAppointments = new ArrayList<Appointment>();
		}
		
		oldAppointments.add(newAppointment);
		this.resources.put(freeDate, oldAppointments);
		return newAppointment;
	}

	/**
	 * 
	 * @param doctor
	 * @param duration
	 * @return
	 */
	private Date getFirstDate(Doctor doctor, int duration) {
		Collection<Date> allDates = this.appointments.keySet();
		for (Date curDate : allDates) {
			Collection<Appointment> colAppointments = this.appointments
					.get(curDate);
			for (Appointment curAppointment : colAppointments) {
				Date endDate = new Date(curAppointment.getDate().getTime()
						+ curAppointment.getAppointmentDuration());
				if (curAppointment.getDoctor() == doctor
						&& isFree(doctor, duration, endDate)) {
					return endDate;
				}
			}
		}
		return null;
	}

	private boolean isFree(Doctor doctor, int duration, Date date) {
		Collection<Date> allDates = this.appointments.keySet();
		Date endDate = new Date(date.getTime() + duration);
		for (Date curDate : allDates) {
			if (date.after(curDate)) {
				Collection<Appointment> colAppointments = this.appointments
						.get(curDate);
				for (Appointment curAppointment : colAppointments) {
					Date endCurDate = new Date(curDate.getTime()
							+ curAppointment.getAppointmentDuration());
					if (curAppointment.getDoctor() == doctor
							&& endCurDate.after(date)) {
						return false;
					}
				}
			} else if (curDate.after(date) && endDate.after(curDate)) {
				Collection<Appointment> colAppointments = this.appointments
						.get(curDate);
				for (Appointment curAppointment : colAppointments) {
					if (curAppointment.getDoctor() == doctor) {
						return false;
					}
				}
			}

		}
		return true;
	}
}