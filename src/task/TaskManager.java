package task;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import exceptions.*;
import scheduler.timetables.*;

/**
 * This class will notify a Scheduler when it gets an incoming signal from an
 * Observer. Also it will keep a record of scheduled and unscheduled Tasks.
 */
public class TaskManager
{
	private Scheduler scheduler; // the scheduler associated with this TM
	private Collection<Task> unscheduledTasks = new ArrayList<Task>();
	private Collection<Task> scheduledTasks = new ArrayList<Task>();

	/**
	 * Default Constructor.
	 * 
	 * @param scheduler
	 *            The scheduler to associated with this TM
	 * @throws InvalidSchedulerException
	 *             If(!canHaveAsScheduler(scheduler)
	 */
	public TaskManager(Scheduler scheduler) throws InvalidSchedulerException {
		if (!this.canHaveAsScheduler(scheduler)) {
			throw new InvalidSchedulerException(
					"Given scheduler is not a valid scheduler for this TM.");
		}
		this.scheduler = scheduler;
	}

	/**
	 * @return true if s is a valid scheduler for this TM.
	 */
	public boolean canHaveAsScheduler(Scheduler s) {
		return scheduler != null;
	}

	/**
	 * This method will notify the associated Scheduler of this TM that
	 * something has changed and that it needs to re-evaluate all constraints.
	 */
	public void notifyScheduler() {
		
	}
	
	@Basic
	public Collection<Task> getUnscheduledTasks() {
		return new ArrayList<Task>(unscheduledTasks);
	}

	@Basic
	public Collection<Task> getScheduledTasks() {
		return new ArrayList<Task>(scheduledTasks);
	}

}
