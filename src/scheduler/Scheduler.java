package scheduler;

import java.util.*;
import machine.BloodAnalyser;
import machine.Machine;
import machine.MachinePool;
import machine.UltraSoundScanner;
import machine.XRayScanner;
import scheduler.Requirement;
import task.resource.Resource;
import users.*;
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
		timeTable = new TreeMap<TimePoint, Resource>(
				new Comparator<TimePoint>()
				{
					@Override
					public int compare(TimePoint arg0, TimePoint arg1) {
						int t = arg0.compareTo(arg1);
						if (t == 0) {
							if (arg0.getid() - arg1.getid() < 0) {
								t = -1;
							} else if (arg0.getid() - arg1.getid() > 0) {
								t = 1;
							} else {
								t = 0;
							}
						}
						return t;
					}
				});
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
		cleanUp();
		ScheduledElement startPointFree = findFreeSlot(res, duration);
		Collection<Resource> elementsToSchedule = startPointFree.getResources();
		Date freeDate = startPointFree.getDate();
		HashMap<TimePoint, Resource> scheduledElements = new HashMap<TimePoint, Resource>();
		for (Resource element : elementsToSchedule) {
			TimePoint startPoint = new TimePoint(TimeType.start, freeDate);
			TimePoint endPoint = new TimePoint(TimeType.stop, new Date(
					freeDate.getTime() + duration));
			timeTable.put(startPoint, element);
			timeTable.put(endPoint, element);
			scheduledElements.put(startPoint, element);
			scheduledElements.put(endPoint, element);
		}
		Appointment appointment = new Appointment(patient, scheduledElements,
				freeDate, duration);
		appointments.add(appointment);
		return appointment;
	}

	/**
	 * This function will get the first free slot for an appointment.
	 * 
	 * @param required
	 *            All the resources that should be in the time table (incl new
	 *            resource)
	 * @param duration
	 *            Length of the reservation.
	 * @return The first free slot for an appointment of duration duration.
	 * @throws ImpossibleToScheduleException
	 */
	public ScheduledElement findFreeSlot(Collection<Requirement> required,
			int duration) throws ImpossibleToScheduleException {
		Date firstAfterNow = new Date(now().getTime() + getTimeBuffer());
		Date potentialMatch = firstAfterNow;
		Collection<Resource> availableNow = getResources();
		Collection<Resource> scheduledNow = getAllScheduledAt(firstAfterNow);
		for (Resource r : scheduledNow)
			availableNow.remove(r);
		TimePoint point = new TimePoint(TimeType.start, firstAfterNow);
		TimePoint traverser = this.timeTable.higherKey(point);
		Collection<Resource> scheduledElements = new ArrayList<Resource>();
		if (traverser == null) {
			Date nextDate = closedHourOfDate(firstAfterNow);
			scheduledElements.clear();
			if (satisfied(availableNow, required, scheduledElements)) {
				System.out.println("satisfied");
				// XXX: fixed the not adding to the tree
				for (Resource r : scheduledElements) {
					this.timeTable.put(new TimePoint(TimeType.stop, new Date(
							nextDate.getTime() + duration)), r);
					this.timeTable.put(new TimePoint(TimeType.start, nextDate),
							r);
				}
				return new ScheduledElement(scheduledElements, nextDate);
			} else {
				throw new ImpossibleToScheduleException(
						"There are not enough resources available for these requirements.");
			}
		}
		while (timeDifference(potentialMatch, traverser.getDate()) < duration) {
			switch (traverser.getType()) {
			case start:
				availableNow.remove(this.timeTable.get(traverser));
				break;
			case stop:
				availableNow.add(this.timeTable.get(traverser));
				break;
			}
			if (satisfied(availableNow, required, scheduledElements)) {
				potentialMatch = new Date(traverser.getTime());
			} else {
				point = new TimePoint(TimeType.start, potentialMatch);
				traverser = this.timeTable.higherKey(point);
			}

			traverser = this.timeTable.higherKey(traverser);
			if (traverser == null) {
				Date nextDate = closedHourOfDate(firstAfterNow);
				scheduledElements.clear();
				if (satisfied(availableNow, required, scheduledElements)) {
					return new ScheduledElement(scheduledElements, nextDate);
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

	private long timeDifference(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime())
			return date1.getTime() - date2.getTime();
		else
			return date2.getTime() - date1.getTime();
	}

	private boolean satisfied(Collection<Resource> availableNow,
			Collection<Requirement> required,
			Collection<Resource> scheduledElements) {
		Collection<Resource> resourcesAv = availableNow;
		for (Requirement r : required)
			if (r.isMetBy(resourcesAv)) {
				System.out.println("before: " + resourcesAv.size());
				ArrayList<ArrayList<Resource>> returnedResources = r.removeUsedResoursesFrom(resourcesAv, scheduledElements);
				resourcesAv = returnedResources.get(0);
				scheduledElements = returnedResources.get(1);
				System.out.println("after: " + resourcesAv.size());
			} else {
				scheduledElements.clear();
				return false;
			}
		return true;
	}

	private Collection<Resource> getAllScheduledAt(Date now) {
		Collection<Resource> scheduledResources = new ArrayList<Resource>();
		if (timeTable.size() == 0)
			return scheduledResources;
		TimePoint traverser = timeTable.firstKey();
		while (traverser != null && now.after(traverser.getDate())) {
			switch (traverser.getType()) {
			case start:
				scheduledResources.add(timeTable.get(traverser));
				break;
			case stop:
				scheduledResources.remove(timeTable.get(traverser));
				break;
			}
			traverser = timeTable.higherKey(traverser);
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
	public void removeAppointment(Appointment appointment) {
		HashMap<TimePoint, Resource> resources = appointment.getScheduling();
		for (TimePoint timePoint : resources.keySet()) {
			timeTable.remove(timePoint);
		}
		appointments.remove(appointment);
	}

	/**
	 * This method will clean up the timetable = remove expired appointments.
	 */
	private void cleanUp() {
		Date curDate = now();
		TimePoint curTimePoint = null;
		try {
			curTimePoint = timeTable.firstKey();
		} catch (NoSuchElementException e) {
		}
		HashMap<Resource, TimePoint> startResources = new HashMap<Resource, TimePoint>();
		while (curTimePoint != null && curTimePoint.getDate().before(curDate)) {
			Resource curResource = timeTable.get(curTimePoint);
			if (curTimePoint.getType() == TimeType.start) {
				startResources.put(curResource, curTimePoint);
			} else {
				if (startResources.containsKey(curResource)) {
					TimePoint correspondingTimePoint = startResources
							.get(curResource);
					timeTable.remove(curTimePoint);
					timeTable.remove(correspondingTimePoint);
				}
			}
			curTimePoint = timeTable.higherKey(curTimePoint);
		}
	}

	public int getAmountOfElementsScheduled() {
		return timeTable.size();
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
				RV.add(new XRayScannerResource((XRayScanner) M));
		}
		return RV;
	}
}