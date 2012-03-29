package scheduler.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import system.Hospital;
import users.SchedulableUser;
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
public class TaskManager
{

	private Hospital hospital_;
	/**
	 * Contains all the tasks. Use their states to categorise them.
	 */
	private Collection<Task<?>> tasks_;

	public TaskManager(Hospital hospital) {
		hospital_ = hospital;
		tasks_ = new LinkedList<Task<?>>();
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
	public <T extends TaskDescription> Task<?> add(T description)
			throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException {
		//TODO: fix 
		// Task<?> scheduledTask = task.scheduleIn(this.hospital_);
		Task<? extends TaskDescription> scheduledTask = null;
		tasks_.add(scheduledTask);
		return scheduledTask;
	}

	/**
	 * Gets all the scheduled tasks in this taskmanager.
	 * 
	 * @return
	 */
	public Collection<Task<?>> getScheduledTasks() {
		return new ArrayList<Task<?>>(tasks_);
	}
	
	Hospital getHospital() {
		return this.hospital_;
	}
	
	/**
	 * Used in the controller layer to give sensible information to the user.
	 * 
	 * @return The scheduled TODOs for a certain schedulable.
	 */
	@SuppressWarnings("unchecked")
	public <T extends TaskDescription> Collection<Task<T>> getUnfinishedTasksFrom(SchedulableUser from) {
		Collection<Task<T>> rv = new LinkedList<Task<T>>();
		for(Task<?> task : tasks_)
			if(task.isScheduled() && task.getData().getResources().contains(from))
				rv.add((Task<T>) task);
		return rv;
	}

}
