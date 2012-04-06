package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import system.Hospital;
import users.SchedulableUser;
import exceptions.AlreadyScheduledException;
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
public class TaskManager implements Observer
{

	private Hospital hospital_;
	/**
	 * Contains all the tasks. Use their states to categorise them.
	 */
	private Collection<Task<?>> tasks_;

	public TaskManager(Hospital hospital) {
		hospital_ = hospital;
		hospital_.getTimeKeeper().addObserver(this);
		tasks_ = new LinkedList<Task<?>>();
	}

	/**
	 * Returns a Task created based on the given description.
	 * 
	 * @throws InvalidSchedulingRequestException
	 *             The requirements for this description have not yet been met
	 *             and the description has been stored.
	 * @throws CanNeverBeScheduledException
	 *             The requirements for this description can never be met and
	 *             the description is not stored.
	 */
	public <T extends TaskDescription> Task<T> add(T description) throws CanNeverBeScheduledException {
		Task<T> task = new Task<T>(description, hospital_);
		task.addObserver(this);
		task.init();
		tasks_.add(task);
		try {
			new Scheduler().schedule(task);
		} catch (AlreadyScheduledException e) {
			throw new Error(e.getMessage());
		} catch (InvalidSchedulingRequestException e) {
			task.addObserver(this);
		}
		return task;
	}

	/**
	 * Gets all tasks in this taskmanager.
	 * 
	 * @return
	 */
	public Collection<Task<?>> getAllTasks() {
		return new LinkedList<Task<?>>(tasks_);
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
		for (Task<?> task : tasks_)
			if (task.isScheduled() && task.getData().getResources().contains(from))
				rv.add((Task<T>) task);
		return rv;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Task<?>) {
			this.tryToScheduleOneTask((Task<?>) o);
		} else {
			this.updateTaskCollection();
		}
	}

	/**
	 * Tries to schedule the given task if it's been queued.
	 */
	private void tryToScheduleOneTask(Task<?> task) {
		if (!task.isQueued())
			return;
		try {
			new Scheduler().schedule(task);
		} catch (InvalidSchedulingRequestException e) {
			;
		} catch (CanNeverBeScheduledException e) {
			tasks_.remove(task);
			task.deInitialise();
		} catch (AlreadyScheduledException e) {
			throw new Error("Fatal scheduling error: queued task has already been scheduled?");
		}
	}

	/**
	 * Updates the states of the queued and scheduled tasks when the time
	 * advances.
	 */
	private void updateTaskCollection() {
		for (Task<?> t : this.tasks_) {
			if (t.isQueued()) {
				try {
					new Scheduler().schedule(t);
				} catch (InvalidSchedulingRequestException e) {
					;
				} catch (AlreadyScheduledException e) {
					throw new Error("Fatal scheduling error: queued task has already been scheduled?");
				} catch (CanNeverBeScheduledException e) {
					throw new Error("Fatal scheduling error while updating time... queued task can never be scheduled!");
				}
			} else if (t.isScheduled()) {
				if (t.getDate().before(hospital_.getTimeKeeper().getSystemTime())) {
					t.nextState(t.getData().clone());
				}
			}
		}
	}
}
