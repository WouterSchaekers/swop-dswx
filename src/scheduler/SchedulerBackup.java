package scheduler;

import java.util.*;
import exceptions.ImpossibleToScheduleException;
import patient.PatientFile;
import machine.MachinePool;
import task.requirement.Requirement;
import users.UserManager;
import task.Schedulable;

// TODO: Fix this class and test it thoroughly
public class SchedulerBackup
{
	// all scheduled resources
	private TreeMap<TimePoint, Schedulable> timeTable;
	private Collection<Appointment> appointments;
	// 1 hour after admission <...>
	private final long TIMEAFTERCURRENTDATE = 60 * 60 * 1000;
	private UserManager userManager;
	private MachinePool machinePool;
	private Date startDate;
	private long startupTime = 1320735600001L;

	/**
	 * Default constructor will initialise all fields.
	 */
	public SchedulerBackup(UserManager usermanager, MachinePool machinepool) {
		this.userManager = usermanager;
		this.machinePool = machinepool;
		timeTable = new TreeMap<TimePoint, Schedulable>(
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
			Collection<Requirement> res, long duration)
			throws ImpossibleToScheduleException {
		cleanUp();
		ScheduledElement startPointFree = find(res, duration);
		Collection<Schedulable> elementsToSchedule = startPointFree.getResources();
		Date freeDate = startPointFree.getStartTime();
		HashMap<TimePoint, Schedulable> scheduledElements = new HashMap<TimePoint, Schedulable>();
		for (Schedulable element : elementsToSchedule) {
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

	// DO NOT TOUCH, OR I KILL YOU!!!
	// public ScheduledElement find(Collection<Requirement> requirements,
	// long duration) {
	// Collection<Resource> availableResources = getResources();
	// Date firstDateThatMayBeScheduled = closedRoundedHour(new
	// Date(now().getTime() + getTimeBuffer()));
	// if (timeTable.size() == 0) {
	// Collection<Resource> scheduledResources = satisfied(
	// availableResources, requirements);
	// if(!scheduledResources.isEmpty()){
	// return new ScheduledElement(scheduledResources,
	// firstDateThatMayBeScheduled, new
	// Date(firstDateThatMayBeScheduled.getTime()+duration));
	// }
	// else{
	// return null;
	// }
	// }
	// // candyDate: hehe, little joke
	// Date candyDate = timeTable.firstKey().getDate();
	// if (firstDateThatMayBeScheduled.after(candyDate)) {
	// candyDate = firstDateThatMayBeScheduled;
	// }
	// Date traverser = candyDate;
	// System.out.println(availableResources.size());
	// TimePoint traverseTimePoint = timeTable.firstKey();
	// while (traverseTimePoint.getDate().before(candyDate)) {
	// switch (traverseTimePoint.getType()) {
	// case start:
	// availableResources.remove(timeTable.get(traverseTimePoint));
	// case stop:
	// availableResources.add(timeTable.get(traverseTimePoint));
	// }
	// }
	// while(timeDifference(candyDate, traverser) < duration){
	//
	// }
	// return null;
	// }

	public ScheduledElement find(Collection<Requirement> reqs, long duration)
			throws ImpossibleToScheduleException {
		if (timeTable.isEmpty()) {
			Date now = closestRoundHour(new Date(now().getTime()
					+ getTimeBuffer()));
			Collection<Schedulable> r = satisfied(getResources(), reqs);
			if (r.isEmpty())
				throw new ImpossibleToScheduleException("wtf");
			for (Schedulable resource : r) {
				timeTable.put(new TimePoint(TimeType.start, now), resource);
				timeTable.put(
						new TimePoint(TimeType.stop, new Date(now.getTime()
								+ duration)), resource);
			}
			return new ScheduledElement(r, now, new Date(now.getTime()
					+ duration));
		}

		Date now = new Date(now().getTime() + getTimeBuffer());
		TimePoint traverser;
		if (now.before(timeTable.firstEntry().getKey().getDate())) {
			now = timeTable.firstEntry().getKey().getDate();
			traverser = timeTable.firstEntry().getKey();
		} else {
			traverser = timeTable.higherKey(new TimePoint(TimeType.start, now));
		}
		Collection<Schedulable> resourcesInSystem = getResources();
		Collection<Schedulable> used = null;
		Cut(resourcesInSystem, getAllScheduledAt(traverser.getDate()));
		TimePoint backup = null;
		Collection<Schedulable> resourcesbackup = null;
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
					resourcesbackup = new ArrayList<Schedulable>(resourcesInSystem);
					backup = traverser;

				}
			}
			traverser = timeTable.higherKey(traverser);
			used = satisfied(resourcesInSystem, reqs);
			if (!used.isEmpty() && traverser == null) {
				// yeya hotfix
				for (Schedulable r : used) {
					timeTable.put(
							new TimePoint(TimeType.start, backup.getDate()), r);
					timeTable.put(new TimePoint(TimeType.start, new Date(backup
							.getDate().getTime() + duration)), r);

				}
				return new ScheduledElement(used, backup.getDate(), new Date(
						backup.getDate().getTime() + duration));
			}
			if (traverser == null) {
				Date t = timeTable.lastEntry().getKey().getDate();
				for (Schedulable resource : used) {
					timeTable.put(new TimePoint(TimeType.start, t), resource);
					timeTable.put(
							new TimePoint(TimeType.stop, new Date(t.getTime()
									+ duration)), resource);
				}
				return new ScheduledElement(used, t, new Date(t.getTime()
						+ duration));
			}

		}
		for (Schedulable resource : used) {
			timeTable.put(new TimePoint(TimeType.start, backup.getDate()),
					resource);
			timeTable.put(new TimePoint(TimeType.stop, new Date(backup
					.getDate().getTime() + duration)), resource);
		}
		return new ScheduledElement(used, backup.getDate(), new Date(
				backup.getTime() + duration));
	}

	private void Cut(Collection<Schedulable> resourcesInSystem, Collection<Schedulable> allScheduledAt) {
		for (Schedulable r : allScheduledAt)
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
	public Date closestRoundHour(Date date) {
		long totalTime = date.getTime();
		while (totalTime % 3600000 != 0) {
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
	private Collection<Schedulable> satisfied(Collection<Schedulable> availableNow, Collection<Requirement> required) {
		Collection<Schedulable> avResHere = new ArrayList<Schedulable>(availableNow);
		Collection<Schedulable> scheduledElementsTemp = new ArrayList<Schedulable>();
		for (Requirement r : required) {
			if (r.isMetBy(avResHere)) {
				for (Schedulable s : r.resourcesNeededFrom(avResHere)) {
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

	private Collection<Schedulable> getAllScheduledAt(Date now) {
		Collection<Schedulable> scheduledResources = new ArrayList<Schedulable>();
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
		return new Date(returntime);
	}

	/**
	 * @return The minimum amount of time that needs to be between the admission
	 *         and execution of an appointment.
	 */
	public long getTimeBuffer() {
		return TIMEAFTERCURRENTDATE;
	}

	/**
	 * This method can end an appointment after it has taken place.
	 * 
	 * @param appointment
	 *            The appointment to be ended.
	 */
	public void removeAppointment(Appointment appointment) {
		HashMap<TimePoint, Schedulable> resources = appointment.getScheduling();
		for (TimePoint timePoint : resources.keySet()) {
			timeTable.remove(timePoint);
		}
		appointments.remove(appointment);
	}

	/**
	 * This method will clean up the timetable = remove expired appointments.
	 * For the next iteration
	 * 
	 */
	// XXX: Fix in next iteration
	private void cleanUp() {
		Date curDate = now();
		TimePoint curTimePoint = null;
		try {
			curTimePoint = timeTable.firstKey();
		} catch (NoSuchElementException e) {
		}
		HashMap<Schedulable, TimePoint> startResources = new HashMap<Schedulable, TimePoint>();
		while (curTimePoint != null && curTimePoint.getDate().before(curDate)) {
			Schedulable curResource = timeTable.get(curTimePoint);
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

	/**
	 * @return All possible resources.
	 */
	private Collection<Schedulable> getResources() {
		ArrayList<Schedulable> RV = new ArrayList<Schedulable>();

		for (Schedulable u : userManager.getAllUsers()) {
			RV.add(u);
		}
		for (Schedulable M : machinePool.getAllMachines()) {

			RV.add(M);
		}
		return RV;
	}

	// public static void main(String[] argsfuckedupstupidname) {
	// UserManager usm = new UserManager();
	// MachinePool mp = new MachinePool();
	// Scheduler scheduler = new Scheduler(usm, mp);
	// Requirement xRayRequirement1 = new AresourceRequirement(
	// XRayScanner.class);
	// Requirement xRayRequirement2 = new AresourceRequirement(
	// XRayScanner.class);
	// Requirement bloodAnalyzerRequirement = new AresourceRequirement(
	// BloodAnalyser.class);
	// mp.addMachine(new XRayScanner("The galaxy and beyond."));
	// mp.addMachine(new XRayScanner("Stefan's ass."));
	// Collection<Requirement> requirements = new ArrayList<Requirement>();
	// requirements.add(xRayRequirement1);
	// requirements.add(xRayRequirement2);
	// Add this to fail the find method.
	// requirements.add(bloodAnalyzerRequirement);
	// ScheduledElement scheduledElement = scheduler.find(requirements, 0);
	// System.out.println(scheduledElement.getStartTime());
	// }
}