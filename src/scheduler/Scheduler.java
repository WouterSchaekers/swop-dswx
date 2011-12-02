package scheduler;

import java.util.*;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.*;
import machine.*;
import task.Resource;
import task.requirement.Requirement;
import users.*;
import users.User.*;
import patient.*;

public class Scheduler
{
	// all scheduled resources
	private TreeMap<TimePoint, Resource> timeTable;
	private Collection<Appointment> appointments;
	
	// 1 hour after admission <...>
	private final long TIMEAFTERCURRENTDATE = 60 * 60 * 1000;
	
	private Date curInternalDate;
	private Collection<Resource> allResources = new ArrayList<Resource>();
	private ConstraintProcessor constrProc = new ConstraintProcessor();

	/**
	 * Default constructor; will initilize all fields.
	 * 
	 * @param usermanager
	 *            The UserManager of which's User this Scheduler should consider
	 *            to be Resources.
	 * @param machinepool
	 *            The MachinePool that contains the mechanical Resources for
	 *            this Scheduler.
	 * @param curdate
	 *            The system's wanted current time.
	 */
	public Scheduler(UserManager usermanager, MachinePool machinepool, Date curdate) {
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
		this.curInternalDate = curdate;
		
		Collection<User> cu = usermanager.getAllUsers();
		for(User u: cu)
			if(!u.type().equals(usertype.HospitalAdmin))
				allResources.add(u);
		
		Collection<Machine> cm = machinepool.getAllMachines();
		for(Machine m: cm)
			allResources.add(m);
	}

	public ScheduledElement find(Collection<Requirement> reqs, long duration)
			throws ImpossibleToScheduleException {
		
		if (timeTable.isEmpty()) {
			Date now = nextRoundHour(new Date(this.curInternalDate.getTime() + getTimeBuffer()));
			
			// check de constraints
			Collection<Resource> r = constrProc.satisfied(getResources(), reqs);
			for (Resource resource : r) {
				timeTable.put(new TimePoint(TimeType.start, now), resource);
				timeTable.put(
						new TimePoint(TimeType.stop, new Date(now.getTime()
								+ duration)), resource);
			}
			return new ScheduledElement(r, now, new Date(now.getTime()
					+ duration));
		}

		Date now = new Date(this.curInternalDate.getTime() + getTimeBuffer());
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
			used = constrProc.satisfied(resourcesInSystem, reqs);
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
			used = constrProc.satisfied(resourcesInSystem, reqs);
			if (!used.isEmpty() && traverser == null) {
				// yeya hotfix
				for (Resource r : used) {
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
				for (Resource resource : used) {
					timeTable.put(new TimePoint(TimeType.start, t), resource);
					timeTable.put(
							new TimePoint(TimeType.stop, new Date(t.getTime()
									+ duration)), resource);
				}
				return new ScheduledElement(used, t, new Date(t.getTime()
						+ duration));
			}

		}
		for (Resource resource : used) {
			timeTable.put(new TimePoint(TimeType.start, backup.getDate()),
					resource);
			timeTable.put(new TimePoint(TimeType.stop, new Date(backup
					.getDate().getTime() + duration)), resource);
		}
		return new ScheduledElement(used, backup.getDate(), new Date(
				backup.getTime() + duration));
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

	//XXX: TODO
	/**
	 * This method allows other classes to notify the scheduler that something
	 * has changed within the system.
	 */
	public void getNotified() {

	}
	
	@Basic
	public long getTimeBuffer() {
		return TIMEAFTERCURRENTDATE;
	}

	@Basic
	private Collection<Resource> getResources() {
		return new ArrayList<Resource>(this.allResources);
	}
	
	/**
	 * This function will get the first free slot for an appointment.
	 * 
	 * @param required
	 *            All the resources that should be in the time table (incl new resource)
	 * @param duration
	 *            Length of the reservation.
	 * @return The first free slot for an appointment of duration duration.
	 */
	public Date nextRoundHour(Date date) {
		long totalTime = date.getTime();
		while (totalTime % 3600000 != 0) {
			totalTime++;
		}
		return new Date(totalTime);
	}

	/**
	 * Calculates the amount of milliseconds between 2 dates.
	 * @return ~
	 */
	private long timeDifference(Date date1, Date date2) {
		if (date1 == null)
			return Long.MIN_VALUE;
		if (date1.getTime() > date2.getTime())
			return date1.getTime() - date2.getTime();
		else
			return date2.getTime() - date1.getTime();
	}
	
	private void Cut(Collection<Resource> resourcesInSystem, Collection<Resource> allScheduledAt) {
		for (Resource r : allScheduledAt)
			resourcesInSystem.remove(r);
	}
}
