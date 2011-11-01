package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;
import resources.BloodAnalyser;
import resources.Machine;
import resources.MachinePool;
import resources.Resource;
import resources.UltraSoundScanner;
import resources.XRayScanner;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;
import users.UserManager;
import patient.PatientFile;

/**
 * 
 * @author Stefaan is een fucking noob
 * 
 */
// TODO: Fix this class and test it thoroughly
public class Scheduler
{
	// all scheduled resources
	private TreeMap<TimePoint, Resource> timeTable;
	private Collection<Appointment> appointments;
	// 1 hour after admission <...>
	private final int TIMEAFTERCURRENTDATE = 60 * 60 * 1000;
	private UserManager userManager;
	private MachinePool machinePool;
	private Date startDate;
	private long startupTime = 1320735600000L;

	/**
	 * Default constructor will initialise all fields.
	 */
	public Scheduler(UserManager usermanager, MachinePool machinepool) {
		this.userManager = usermanager;
		this.machinePool = machinepool;
		timeTable = new TreeMap<TimePoint, Resource>();
		appointments = new ArrayList<Appointment>();
		startDate = new Date();
	}

	/**
	 * Will add an appointment to the time table in the correct slot.
	 * 
	 * @param patient
	 *            The patient who the appointment is for.
	 * @param res
	 *            The resource scheduled for the appointment
	 * @param duration
	 *            The length of the appointment
	 * @return The appointment.
	 * @throws ImpossibleToScheduleException
	 */
	public Appointment addAppointment(PatientFile patient,
			Collection<Requirement> res, int duration)
			throws ImpossibleToScheduleException {
		ScheduledElement startPointFree = findFreeSlot(res, duration);
		Collection<Resource> scheduledElements = startPointFree.getResources();
		Date goodDate = startPointFree.getDate();
		TimePoint start = new TimePoint(TimeType.start, goodDate, now());
		TimePoint stop = new TimePoint(TimeType.stop, new Date(goodDate.getTime() + duration), now());
		for (Resource element : scheduledElements) {
			timeTable.put(new TimePoint(TimeType.start, goodDate, now()),
					element);
			timeTable.put(
					new TimePoint(TimeType.stop, new Date(goodDate.getTime()
							+ duration), now()), element);
		}
		Appointment appointment = new Appointment(patient, scheduledElements, start, stop);
		appointments.add(appointment);
		return appointment;
	}

	/**
	 * This function will get the first free slot for an appointment.
	 * 
	 * @param res
	 *            All the resources that should be in the time table (incl new
	 *            resource)
	 * @param durationnow
	 *            () Length of the reservation.
	 * @return The first free slot for an appointment of duration duration.
	 * @throws ImpossibleToScheduleException
	 */
	public ScheduledElement findFreeSlot(Collection<Requirement> required,
			int duration) throws ImpossibleToScheduleException {
		Date now = now();
		Date firstAfterNow = new Date(now.getTime() + TIMEAFTERCURRENTDATE);
		TimePoint potentialmatch = null;
		Collection<Resource> availableNow = getResources();
		Collection<Resource> scheduledNow = getAllScheduledAt(firstAfterNow);
		for (Resource r : scheduledNow)
			availableNow.remove(r);
		TimePoint point = new TimePoint(TimeType.start, firstAfterNow, now());
		TimePoint traverser = timeTable.higherKey(point);
		Collection<Resource> scheduledElements = new ArrayList<Resource>();
		// TODO: Denk! -> Done.
		if (traverser == null) {
			Date nextDate = closedHourOfDate(firstAfterNow);
			if (satisfied(availableNow, required, scheduledElements)) {
				return new ScheduledElement(scheduledElements, nextDate);
			} else {
				throw new ImpossibleToScheduleException(
						"There are not enough resources available for these requirements.");
			}
		}
		while (potentialmatch == null
				|| !(timeDifference(potentialmatch, traverser) < duration)) {
			switch (traverser.getType()) {
			case start:
				availableNow.remove(timeTable.get(traverser));
				break;
			case stop:
				availableNow.add(timeTable.get(traverser));
				break;
			}
			if (satisfied(availableNow, required, scheduledElements)
					&& potentialmatch == null) {
				potentialmatch = traverser;
			} else {
				traverser = timeTable.higherKey(potentialmatch);
			}

			traverser = timeTable.higherKey(traverser);
			if (traverser == null) {
				Date nextDate = closedHourOfDate(firstAfterNow);
				if (satisfied(availableNow, required, scheduledElements)) {
					break;
				} else {
					throw new ImpossibleToScheduleException(
							"There are not enough resources available for these requirements.");
				}
			}

		}
		throw new ImpossibleToScheduleException("The scheduler is broken.");
	}

	public Date closedHourOfDate(Date date) {
		long totalTime = date.getTime();
		long timeToAdd = 60 * 60 * 1000 - totalTime % (60 * 60 * 1000);
		if (timeToAdd == 60 * 60 * 1000) {
			return new Date(totalTime);
		} else {
			return new Date(timeToAdd + totalTime);
		}
	}

	private long timeDifference(TimePoint potentialmatch, TimePoint traverser) {
		if (potentialmatch == null)
			return 0;
		if (potentialmatch.getTime() > traverser.getTime())
			return potentialmatch.getTime() - traverser.getTime();
		else
			return traverser.getTime() - potentialmatch.getTime();
	}

	private boolean satisfied(Collection<Resource> availableNow,
			Collection<Requirement> required,
			Collection<Resource> scheduledElements) {
		Collection<Resource> resourcesAv = availableNow;
		for (Requirement r : required)
			// TODO: complete isMetBy and removeUsedResoursesFrom
			if (r.isMetBy(availableNow)) {
				r.removeUsedResoursesFrom(resourcesAv, scheduledElements);
			} else {
				scheduledElements.clear();
				return false;
			}
		return true;
	}

	private Collection<Resource> getAllScheduledAt(Date now) {
		Collection<Resource> scheduledResources = new ArrayList<Resource>();
		TimePoint traverser = timeTable.firstKey();
		while (now.after(traverser.getDate())) {
			switch (traverser.getType()) {
			case start:
				scheduledResources.add(timeTable.get(traverser));
				break;
			case stop:
				scheduledResources.remove(timeTable.get(traverser));
				break;
			}
		}
		return scheduledResources;
	}

	private Date now() {
		return new Date(startupTime + new Date().getTime()
				- startDate.getTime());
	}

	/**
	 * @return The minimum amount of time that needs to be between the admission
	 *         and execution of an appointment.
	 */
	public int getTimeBuffer() {
		return TIMEAFTERCURRENTDATE;
	}

	/**
	 * This method can end an appointment after it has taken place.
	 * 
	 * @param appointment
	 *            The appointment to be ended.
	 */
	public void endAppointment(Appointment appointment) {
		this.timeTable.remove(appointment.getStart());
	}

	/**
	 * This method will clean up the timetable = remove expired appointments.
	 * 
	 * @throws Exception
	 */
	private void cleanUp() throws Exception {
		// TODO: implement
		throw new Exception("not implemented yet");
	}

	/**
	 * @return All possible resources.
	 */
	private Collection<Resource> getResources() {
		ArrayList<Resource> RV = new ArrayList<Resource>();
		for (User u : userManager.getAllUsers()) {
			if (u instanceof Nurse)
				RV.add(new NurseResource((Nurse) u));
			if (u instanceof Doctor)
				RV.add(new DoctorResource((Doctor) u));
			if (u instanceof HospitalAdmin)
				RV.add(new HospitalAdminResource((HospitalAdmin) u));
		}
		for (Machine M : machinePool.getAllMachines()) {
			if (M instanceof BloodAnalyser)
				RV.add(new BloodAnalyzerResource((BloodAnalyser) M));
			if (M instanceof UltraSoundScanner)
				RV.add(new UltraSoundScannerResource((UltraSoundScanner) M));
			if (M instanceof XRayScanner)
				RV.add(new XrayScannerResource((XRayScanner) M));
		}
		return RV;
	}

	public static void main(String[] args) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("bla", "blah");
		map.get(null);
	}
}