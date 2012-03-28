package scheduler.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import system.Hospital;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidSchedulingRequestException;

/**
 * This class manages new and old Tasks. If a new Task is created, it will
 * schedule it immediately, if possible. If the new Task can't be scheduled
 * straight away, it will go to the waiting queue that's being kept in this
 * class. The reason these Tasks can't be scheduled is that one of their
 * requirements has not been met yet. Mind that you need to have an association
 * to TaskManager from each Requirement class so that it can call the
 * updateQueue() function every time its state changes. Once an unscheduled task
 * meets its requirements, it will be scheduled.
 */
public class TaskManager extends Observable
{

	private Hospital hospital_;
	private Collection<TaskDescription> unscheduledDescriptions_;
	private Collection<ScheduledTask<?>> scheduledTasks_;

	public TaskManager(Hospital hospital) {
		hospital_ = hospital;
		unscheduledDescriptions_ = new LinkedList<TaskDescription>();
		scheduledTasks_ = new LinkedList<ScheduledTask<?>>();
	}

	/**
	 * Returns a scheduled task if the given taskdescription can be scheduled.
	 * 
	 * @param description
	 * @return
	 * @throws InvalidSchedulingRequestException
	 *             The requirements for this description have not yet been met
	 *             and the description has been stored.
	 * @throws CanNeverBeScheduledException
	 *             The requirements for this description can never be met and
	 *             the description is not stored.
	 */
	public <T extends TaskDescription> ScheduledTask<?> add(T description)
			throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException {
		UnscheduledTask<T> task = new UnscheduledTask<T>(description);

		try {
			ScheduledTask<?> scheduledTask = task.scheduleIn(hospital_);
			scheduledTasks_.add(scheduledTask);
			return scheduledTask;
		} catch (InvalidSchedulingRequestException e) {
			addForLater(description);
			throw e;
		}
	}

	/**
	 * Gets all the tasks that are not yet scheduled in this taskmanager for
	 * this hospital
	 * 
	 * @return
	 */
	public Collection<TaskDescription> getUnscheduledDescriptions() {
		return new ArrayList<TaskDescription>(unscheduledDescriptions_);
	}

	/**
	 * Gets all the scheduled tasks in this taskmanager.
	 * 
	 * @return
	 */
	public Collection<ScheduledTask<?>> getScheduledTasks() {
		return new ArrayList<ScheduledTask<?>>(scheduledTasks_);
	}

	private void addForLater(TaskDescription description) {
		unscheduledDescriptions_.add(description);

	}

}
