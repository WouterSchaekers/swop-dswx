package scheduler.timetables;

import java.util.*;
import exceptions.*;
import be.kuleuven.cs.som.annotate.Basic;
import task.Schedulable;

/**
 * This class will assign all schedulables their timetables.
 * 
 * <br><br>The global idea is: <br>
* <u><b>IN:</b></u><br>
* A collection of collections. Every collection contains all possible Schedulables of one kind. The highest level collection is a collection of all these collections.
* <br>If one would like to, for example, schedule 2 nurses, one would have to call the scheduler with, as parameters, 2 collections containing all nurses.
* <br>
* <br><u><b>OUT:</b></u><br>
* The time of the start of the scheduled appointment.
* 
* <br><br>The scheduler is also responsible for keeping track of the current system time.
 */
public class Scheduler
{
	private static Date currentSystemTime;
	private LinkedList<Collection<Schedulable>> stillToSchedule = new LinkedList<Collection<Schedulable>>();
	
	/**
	 * Default constructor; will set current system time.
	 * 
	 * @param systemDate
	 *            The wanted current system time.
	 */
	public Scheduler (Date systemDate) {
		if(systemDate == null)
			throw new IllegalArgumentException("systemDate in constructor of Scheduler is null!");
		currentSystemTime = systemDate;
	}

	/**
	 * This method will schedule one of each resources given in the parameters
	 * in the first available and valid timeslot.
	 * 
	 * @param resourcesToSchedule
	 *            Collections of all possible instances of a certain wanted
	 *            resource type. (see class-documentation of Scheduler for a
	 *            better explaination)
	 * @param duration
	 *            The wanted length of the reservation of each resource.
	 * @return The start date of the scheduled appointment.
	 */
	public Date schedule(long duration, Collection<Schedulable>... resourcesToSchedule) throws QueueException, InvalidDurationException {
		for (int i = 0; i < resourcesToSchedule.length; i++)
			this.stillToSchedule.add(resourcesToSchedule[i]);
		this.schedule(duration, new LinkedList<TimeTable>());
		return null;
	}
	
	/**
	 * This <b><i>PRIVATE</i></b> method will schedule an appointment
	 * recursively for every required resource.
	 * 
	 * @param duration
	 * 
	 * @param used
	 * 
	 * @effect getNextResourceQueue()
	 * @return The date of the scheduled appointment.
	 */
	private Date schedule(long duration, LinkedList<TimeTable> used) throws QueueException, InvalidDurationException {
		if (duration < 0) 
			throw new InvalidDurationException("Invalid duration in schedule-method!");
		
		if(stillToSchedule.isEmpty()) {
			// We now have intersected all the time lines of
			// all the requested types of resources.
			// The result is that we now, for each requested resourcetype
			// have a timetable that is only marked as "busy" if all
			// instances of that resourcetype are busy at that time.
			//
			// In other words: if a slot is free in the merged timetable,
			// there will be at least one instance of that resource type that
			// can be scheduled at that time.
			//
			// The only thing left to do now is to intersect all intersected
			// timetables with eachother and grab the first free slot that's
			// available in that timetable.
			//
			// Once we have that slot, we can check which one of our resources
			// is available at that specific time.
			//
			// After we have all of those, we only need to tell them to be
			// Busy at the found free slot and return the time of the start
			// of that slot to the method calling this method.
			//
			// should be easy enough ;)
			// Stefaan gaat dees dus morgen fixen. wie aan de code komt
			// zonder zijn permissie krijgt meppen. ^^

			// Collection<Schedulable> sortedResources = sortByFirstFreeSlot(used.);
			return null;
		}
		
		List<Schedulable> resourceQueue = getNextResourceQueue();
		Schedulable firstQueueElement = resourceQueue.remove(0);
		Collection<TimeTable> allTheTimeTables = new LinkedList<TimeTable>();
		Iterator<Schedulable> schedIterator = resourceQueue.iterator();
		
		while(schedIterator.hasNext()) {
			allTheTimeTables.add(schedIterator.next().getTimeTable());
			resourceQueue.remove(0);
		}
		
		TimeTable curTimeTableIntersection = firstQueueElement.getTimeTable().intersectAll(allTheTimeTables);
		used.add(curTimeTableIntersection);		
		
		return schedule(duration, used);
	}

	/**
	 * This method will sort a given collection of schedulables by first
	 * available slot.
	 * 
	 * @param collection
	 *            The collection of schedulables to be sorted.
	 * @return Every item in the given collection in an accordingly sorted
	 *         fashion.
	 */
	private Collection<Schedulable> sortByFirstFreeSlot(Collection<Schedulable> collection) {
		Schedulable[] returnValue = new Schedulable[collection.size()];
		
		int i = 0;
		for (Schedulable s : collection)
			returnValue[i++] = s;
		Arrays.sort(returnValue, comparatorOfSchedulables);
		return new LinkedList<Schedulable>(Arrays.asList(returnValue));
	}
	
	@Basic
	public Date getCurrentSystemTime() {
		return currentSystemTime;
	}
	
	public void setNewSystemTime(Date newTime) {
		currentSystemTime = newTime;
	}
	
	/**
	 * @return True if t is a valid timeslot for duration amount of time.
	 */
	private boolean isValidCandidateSlot(TimeSlot t, long duration) {
		return t.getLength() >= duration && t.getStartPoint().getTime() < currentSystemTime.getTime() + 60 * 1000 * 3600;
	}
	
	/**
	 * This method will update the resource queue so that it contains the
	 * resources from the next collection of stillToScehdule.
	 * 
	 * @effect stillToSchedule.remove();
	 * @throws QueueException
	 *             if the to-schedule-queue is empty.
	 */
	private LinkedList<Schedulable> getNextResourceQueue() throws QueueException {
		if (!stillToSchedule.isEmpty()) 
			throw new QueueException("Error while updating resource queue: nothing left to schedule!");
		return (LinkedList<Schedulable>)stillToSchedule.remove(0);
	}
	
	/**
	 * A comparator to compare 2 Schedulables.
	 * Will compare the startpoints.
	 */
	Comparator<Schedulable> comparatorOfSchedulables = new Comparator<Schedulable>()
	{
		public int compare(Schedulable o1, Schedulable o2) {
			TimePoint freeSlotO1 = o1.getTimeTable().getFirstFreeSlot(0).getStartPoint();
			TimePoint freeSlotO2 = o2.getTimeTable().getFirstFreeSlot(0).getStartPoint();
			return freeSlotO1.compareTo(freeSlotO2);
		}
	};
}
