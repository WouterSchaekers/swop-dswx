package scheduler.timetables;

import java.util.*;
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
	private HashMap<Schedulable, TimeTable> timetables;
	private Collection<Schedulable>[] toSchedule;
	private int duration;
	
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
	 * Empty constructor; <b>NOT DEFAULT</b>.
	 * Will not initialise the current systemtime if it already has been by another Scheduler.
	 * If not, it will initialise it at System.currentTimeMillis(); 
	 */
	public Scheduler () {
		if(currentSystemTime == null)
			currentSystemTime = new Date(System.currentTimeMillis());
	}
	
	/**
	 * This method will schedule an appointment recursively for every required
	 * resource.
	 * 
	 * @param used
	 *            The inbetween TimeTable; to be passed through on every
	 *            recursive call.
	 * @param stillToSchedule
	 *            Resource collection still to schedule.
	 * @return
	 */
	private void schedule(TimeTable used, Collection<Schedulable>... stillToSchedule) {
		for (Schedulable s : stillToSchedule[0]) {
			TimeTable temp = this.timetables.get(s);
			this.timetables.remove(s);
			this.timetables.put(s, used.getIntersect(temp));
		}

		Schedulable[] Ls = sortAvailableness(stillToSchedule[0]);
	}
	
	/**
	 * 
	 * @param collection
	 * @return
	 */
	private Schedulable[] sortAvailableness(Collection<Schedulable> collection) {
		Schedulable[] returnValue = new Schedulable[collection.size()];
		int i = 0;
		for (Schedulable s : collection)
			returnValue[i++] = s;
		Arrays.sort(returnValue, c);
		// TODO Auto-generated method stub
		return null;
	}
	
	@Basic
	public Date getCurrentSystemTime() {
		return currentSystemTime;
	}
	
	public void setNewSystemTime(Date newTime) {
		currentSystemTime = newTime;
	}
	
	/**
	 * A comparator to compare 2 Schedulables
	 */
	Comparator<Schedulable> c = new Comparator<Schedulable>()
	{
		public int compare(Schedulable o1, Schedulable o2) {
			return 0;
		}
	};
}
