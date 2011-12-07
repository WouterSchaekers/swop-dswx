package scheduler.timetables;

import java.util.*;
import exceptions.QueueException;
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
		currentSystemTime = systemDate;
	}
	
	/**
	 * Empty constructor; <b>NOT DEFAULT</b>. Will not initialise the current
	 * systemtime if it already has been by another Scheduler. If not, it will
	 * initialise it at System.currentTimeMillis();
	 */
	public Scheduler () {
		if(currentSystemTime == null)
			currentSystemTime = new Date(System.currentTimeMillis());
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
	public Date schedule(long duration, Collection<Schedulable>... resourcesToSchedule) throws QueueException {
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
	private Date schedule(long duration, LinkedList<TimeTable> used) throws QueueException {
		if (duration < 0) 
			throw new IllegalArgumentException("Invalid duration parameter in private schedule-method!");
		
		if(stillToSchedule.isEmpty())
			return null;
		
		List<Schedulable> resourceQueue = getNextResourceQueue();
		Collection<Schedulable> sortedResources = sortByFirstFreeSlot(resourceQueue); // XXX: moet er wel gesort worden?
		Schedulable firstQueueElement = resourceQueue.remove(0);
		Collection<TimeTable> allTheTimeTables = new LinkedList<TimeTable>();
		Iterator<Schedulable> schedIterator = resourceQueue.iterator();
		
		while(schedIterator.hasNext()) {
			allTheTimeTables.add(schedIterator.next().getTimeTable());
			resourceQueue.remove(0);
		}
		
		TimeTable curIntersection = firstQueueElement.getTimeTable().intersectAll(resourceQueue);
		
		
		return schedule(duration, null);
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
		LinkedList<Schedulable> queue = new LinkedList<Schedulable>();
		if (!stillToSchedule.isEmpty()) 
			throw new QueueException("Error while updating resource queue: nothing left to schedule!");
		
		Collection<Schedulable> col = stillToSchedule.remove(0);
		
		return queue;
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
