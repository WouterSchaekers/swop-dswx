package scheduler;

import java.util.*;
import patient.PatientFile;
import machine.MachinePool;
import task.requirement.Requirement;
import users.UserManager;
import task.Resource;

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
	private long startupTime = 1320735600001L;

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
		// cleanUp();
		ScheduledElement startPointFree = find(res, duration);
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

	public ScheduledElement find(Collection<Requirement> reqs, int duration)
			throws ImpossibleToScheduleException {
		if (timeTable.isEmpty()) {
			Date now = closedHourOfDate(new Date(now().getTime()
					+ getTimeBuffer()));
			TimePoint t = new TimePoint(TimeType.start, now);
			Collection<Resource> r = satisfied(getResources(), reqs);
			if (r.isEmpty())
				throw new ImpossibleToScheduleException("wtf");
			for (Resource resource : r) {
				timeTable.put(new TimePoint(TimeType.start, now), resource);
				timeTable.put(
						new TimePoint(TimeType.stop, new Date(now.getTime()
								+ duration)), resource);
			}
			return new ScheduledElement(r, now);
		}

		Date now = new Date(now().getTime() + getTimeBuffer());
		TimePoint traverser;
		if (now.before(timeTable.firstEntry().getKey().getDate())) {
			now = timeTable.firstEntry().getKey().getDate();
			traverser = timeTable.firstEntry().getKey();
		} else {
			traverser = timeTable.higherKey(new TimePoint(TimeType.start, now));
		}
		Collection<Resource> resourcesInSystem = getResources();
		Collection<Resource> used = null;
		Cut(resourcesInSystem, getAllScheduledAt(traverser.getDate()));
		TimePoint backup = null;
		Collection<Resource> resourcesbackup = null;
		while (backup == null
				|| timeDifference(backup.getDate(), traverser.getDate()) < duration) {
			switch (traverser.getType()) {
			case start:
				resourcesInSystem.remove(timeTable.get(traverser));
				break;
			case stop:
				resourcesInSystem.add(timeTable.get(traverser));
				break;
			}
			used = satisfied(resourcesInSystem, reqs);
			if (used.isEmpty()) {
				if (backup != null) {

					traverser = timeTable.higherKey(backup);
					backup = null;
					resourcesInSystem = resourcesbackup;
					// we have to jump back to where we came from and continue
					// our search !
					continue;
				}
				// backup == null
			} else {
				// used is not empty > if
				if (backup != null) {
					// backup needs to be maintained and traverser
				} else {
					// backup needs to be set now
					resourcesbackup = new ArrayList<Resource>(resourcesInSystem);
					backup = traverser;

				}
			}
			traverser = timeTable.higherKey(traverser);
			used = satisfied( resourcesInSystem, reqs);
			if(!used.isEmpty()&&traverser==null)
			{
				// yeya hotfix
				for(Resource r : used)
				{
					timeTable.put(new TimePoint(TimeType.start, backup.getDate()),r);
					timeTable.put(new TimePoint(TimeType.start,new Date( backup.getDate().getTime()+duration)),r);
					
				}
				return new ScheduledElement(used, backup.getDate());
			}
			if (traverser == null) {
				Date t = timeTable.lastEntry().getKey().getDate();
				for (Resource resource : used) {
					timeTable.put(new TimePoint(TimeType.start, t), resource);
					timeTable.put(
							new TimePoint(TimeType.stop, new Date(t.getTime()
									+ duration)), resource);
				}
				return new ScheduledElement(used, t);
			} else {
				//System.out.println("huh");
			}

		}
		for (Resource resource : used) {
			timeTable.put(new TimePoint(TimeType.start, backup.getDate()),
					resource);
			timeTable.put(new TimePoint(TimeType.stop, new Date(backup
					.getDate().getTime() + duration)), resource);
		}
		return new ScheduledElement(used, backup.getDate());
	}

	private void Cut(Collection<Resource> resourcesInSystem,
			Collection<Resource> allScheduledAt) {
		for (Resource r : allScheduledAt)
			resourcesInSystem.remove(r);

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

		if (timeTable.firstEntry() != null
				&& timeTable.firstEntry().getKey().getDate()
						.before(firstAfterNow))
			firstAfterNow = timeTable.firstEntry().getKey().getDate();
		Date potentialMatch = null;
		Collection<Resource> availableNow = getResources();
		Collection<Resource> scheduledNow = getAllScheduledAt(firstAfterNow);

		// XXX: dieter, fix de warnings plz

		Collection<Resource> scheduledAtthisTime = new ArrayList<Resource>();
		scheduledAtthisTime = satisfied(availableNow, required);
		if (scheduledAtthisTime.isEmpty())
			throw new ImpossibleToScheduleException(
					"there are not enough resources in this hopsital to ever meet your requirements, complain to the hospital admin that you need more stuff!");

		TimePoint point = new TimePoint(TimeType.start, firstAfterNow);
		TimePoint traverser = this.timeTable.higherKey(point);
		if (traverser == null) {
			Date nextDate = closedHourOfDate(firstAfterNow);
			scheduledAtthisTime = satisfied(availableNow, required);
			if (!scheduledAtthisTime.isEmpty()) {
				schedue(scheduledAtthisTime, nextDate, duration);
			}
			return new ScheduledElement(scheduledAtthisTime, nextDate);
		}

		while (timeDifference(potentialMatch, traverser.getDate()) < duration
				|| traverser != null) {
			switch (traverser.getType()) {
			case start:
				availableNow.remove(this.timeTable.get(traverser));
				break;
			case stop:
				availableNow.add(this.timeTable.get(traverser));
				break;
			}
			scheduledAtthisTime = satisfied(availableNow, required);
			if (!scheduledAtthisTime.isEmpty()) {
				if (potentialMatch == null)
					potentialMatch = new Date(traverser.getTime());
				traverser = this.timeTable.higherKey(traverser);

			} else {
				if (potentialMatch != null)
					point = new TimePoint(TimeType.start, potentialMatch);
				else
					point = traverser;
				traverser = this.timeTable.higherKey(point);
				potentialMatch = null;
			}
		}

		schedue(scheduledAtthisTime, potentialMatch, duration);

		return new ScheduledElement(scheduledAtthisTime, new Date(
				potentialMatch.getTime() + duration));
	}

	private void schedue(Collection<Resource> scheduledAtthisTime,
			Date potentialMatch, int duration) {
		for (Resource r : scheduledAtthisTime) {
			timeTable.put(new TimePoint(TimeType.start, potentialMatch), r);
			timeTable.put(
					new TimePoint(TimeType.stop, new Date(potentialMatch
							.getTime() + duration)), r);

		}
	}

	public Date closedHourOfDate(Date date) {
		long totalTime = date.getTime();
		while(totalTime % 3600000 != 0){
			totalTime++;
		}
		return new Date(totalTime);
	}

	private long timeDifference(Date date1, Date date2) {
		if (date1 == null)
			return Long.MIN_VALUE;
		if (date1.getTime() > date2.getTime())
			return date1.getTime() - date2.getTime();
		else
			return date2.getTime() - date1.getTime();
	}

	/**
	 * Returns true if the requested resources can be satisfied by the set of
	 * currently available resources. This method returns the elements that are
	 * required to schedule those resources.
	 * 
	 * @param availableNow
	 * @param required
	 * @return empty collection if the requirements can not be met a collection
	 *         with all the needed resources for the given requirements
	 */
	private Collection<Resource> satisfied(Collection<Resource> availableNow,
			Collection<Requirement> required) {
		Collection<Resource> avResHere = new ArrayList<Resource>(availableNow);
		Collection<Resource> scheduledElementsTemp = new ArrayList<Resource>();
		for (Requirement r : required) {
			if (r.isMetBy(avResHere)) {
				for (Resource s : r.resourcesNeededFrom(avResHere)) {
					scheduledElementsTemp.add(s);
					avResHere.remove(s);
				}
			} else {
				scheduledElementsTemp.clear();
				return scheduledElementsTemp;
			}
		}
		return scheduledElementsTemp;
	}

	private Collection<Resource> getAllScheduledAt(Date now) {
		Collection<Resource> scheduledResources = new ArrayList<Resource>();
		if (timeTable.size() == 0)
			return scheduledResources;
		TimePoint traverser = timeTable.firstKey();
		while (traverser != null && traverser.getDate().before(now)) {
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
		long returntime = startupTime + new Date().getTime()
				- startDate.getTime();
//		System.out.println(returntime);
		return new Date(returntime);
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

		for (Resource u : userManager.getAllUsers()) {
			RV.add(u);
		}
		for (Resource M : machinePool.getAllMachines()) {

			RV.add(M);
		}
		return RV;
	}
}