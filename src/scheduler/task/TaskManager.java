package scheduler.task;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import exceptions.*;
import scheduler.*;

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
	private Collection<UnscheduledTask> taskQueue = new LinkedList<UnscheduledTask>();
	private Scheduler myScheduler;
	public TaskManager(Scheduler s)
	{
		this.myScheduler=s;
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
	 */
	public HashMap<ScheduledTask,HospitalDate> updateQueue() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException, InvalidResourceException {
		HashMap<ScheduledTask, HospitalDate> returnValue = new HashMap<ScheduledTask, HospitalDate>();
		Queue<UnscheduledTask> newQueue = new LinkedList<UnscheduledTask>(this.taskQueue);
		for (UnscheduledTask curTask : this.taskQueue) {
			if (curTask.canBeScheduled()) {
				ScheduledTask d = this.myScheduler().schedule(curTask);
				returnValue.put(d, d.getStartDate());
			} else {
				newQueue.add(curTask);
			}
		}//TODO: remove new scheduler hieruit!
		this.taskQueue = newQueue;
		return returnValue;
	}
	
	private Scheduler myScheduler() {
		return this.myScheduler;
	}

	/**
	 * This method will add a Task to this TaskManager's queue. Note that you should probably update the queue of this TaskManager after adding a Task.
	 * @param t
	 * The task to add.
	 * @throws InvalidTimeSlotException 
	 * @throws InvalidSchedulingRequestException 
	 * @throws InvalidDurationException 
	 * @throws QueueException 
	 */
	public void addTask(UnscheduledTask t) throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		if (!isValidTask(t))
			throw new IllegalArgumentException("Task t in addTask of the TaskManager is not a valid queueable Task!");
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
}
