package scheduler.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import scheduler.HospitalDate;
import scheduler.Scheduler;
import scheduler.task.unscheduled.UnscheduledTask;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import exceptions.QueueException;

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
	private Collection<UnscheduledTask> taskQueue = new LinkedList<UnscheduledTask>();
	private Scheduler myScheduler;

	/**
	 * Default constructor. Creates a new taskamanger and appoints it a
	 * sheduler.
	 * 
	 * @param s
	 *            The scheduler to be stored in this TaskManager for current
	 *            system time purposes.
	 */
	public TaskManager(Scheduler s) {
		this.myScheduler = s;
	}

	/**
	 * This method has to be called in order to update the Queue at the right
	 * times in the flow of the program. It will check if any of the Tasks in
	 * the Queue can be scheduled. If so, it will ask a Scheduler to schedule
	 * them.
	 * 
	 * @return A map from Task to Date where the Date is the Date the Task has
	 *         been scheduled at.
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidDurationException
	 * @throws QueueException
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 * @throws InvalidResourceException
	 * @throws InvalidHospitalDateArgument
	 */
	private void updateQueue() throws QueueException, InvalidDurationException,
			InvalidSchedulingRequestException,
			InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidResourceException, InvalidHospitalDateArgument {
		Queue<UnscheduledTask> newQueue = new LinkedList<UnscheduledTask>(
				this.taskQueue);
		for (UnscheduledTask curTask : this.taskQueue) {
			if (curTask.canBeScheduled()) {
				this.myScheduler().schedule(curTask);

			} else {
				newQueue.add(curTask);
			}
		}
		this.taskQueue = newQueue;

	}

	private Scheduler myScheduler() {
		return this.myScheduler;
	}

	/**
	 * This method will add a Task to this TaskManager's queue. Note that you
	 * should probably update the queue of this TaskManager after adding a Task.
	 * 
	 * @param t
	 *            The task to add.
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidDurationException
	 * @throws QueueException
	 */
	public void addTask(UnscheduledTask t) throws QueueException,
			InvalidDurationException, InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		if (!isValidTask(t))
			throw new IllegalArgumentException(
					"Task t in addTask of the TaskManager is not a valid queueable Task!");
		this.taskQueue.add(t);
	}

	/**
	 * @return True if t is a valid Task that can be queued in this TM.
	 */
	private boolean isValidTask(UnscheduledTask t) {
		return t != null;
	}

	@Basic
	public Collection<Task> getTaskQueue() {
		return new ArrayList<Task>(taskQueue);
	}

	/**
	 * This method should be called when any observer notices anything relevant
	 * to the TaskManager. The TaskManager will update its queue and schedule
	 * the things it can in this new situation.
	 * 
	 * @pre before calling this method, we assume that the warehouse observer
	 *      has already notified its warehouse about the changes. Otherwise a
	 *      correct update of the queue will not be guarrenteed!
	 */
	public void update() {
		try {
			this.updateQueue();
		} catch (Exception e) {
		}
	}
}
