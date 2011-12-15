package scheduler.task;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import exceptions.*;
import scheduler.HospitalDate;
import scheduler.Scheduler;

/**
 * This class serves as a form of waiting room for unscheduled tasks. If a Task
 * can't be scheduled at creation, it will be stored in here. Once there's
 * change to the state of the system, the usecase should notify this class to
 * update it's pool of "waiting tasks". It then, if needed can tell the
 * scheduler to schedule tasks if they suddenly do meet the requirements to be
 * scheduled.
 */
public class TaskManager
{
	private Collection<Task> taskQueue = new LinkedList<Task>();

	/**
	 * This method has to be called in order to update the Queue at the right
	 * times in the flow of the program. It will check if any of the Tasks in
	 * the Queue can be scheduled. If so, it will ask the scheduler to schedule
	 * them.
	 * 
	 * @return A map from Task to Date where the Date is the Date the Task has
	 *         been scheduled at.
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidDurationException
	 * @throws QueueException
	 * @throws InvalidSchedulingRequestException 
	 * @throws InvalidTimeSlotException 
	 */
	public HashMap<Task,HospitalDate> updateQueue() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		
		HashMap<Task, HospitalDate> returnValue = new HashMap<Task, HospitalDate>();
		Queue<Task> newQueue = new LinkedList<Task>(this.taskQueue);
		for (Task curTask : this.taskQueue) {
			if (curTask.canBeScheduled()) {
				HospitalDate d = (new Scheduler()).schedule(
						curTask.getDuration(), curTask.getResources());
				returnValue.put(curTask, d);
			} else {
				newQueue.add(curTask);
			}
		}
		this.taskQueue = newQueue;
		return returnValue;
	}
	
	/**
	 * This method will add a Task to this TaskManager's queue.
	 * @param t
	 * The task to add.
	 */
	public void addTask(Task t) {
		if (!isValidTask(t))
			throw new IllegalArgumentException("Task t in addTask of the TaskManager is not a valid queable Task!");
		this.taskQueue.add(t);
	}


	
	/**
	 * @return True if t is a valid Task that can be queued in this TM.
	 */
	private boolean isValidTask(Task t) {
		return t != null && !t.getResources().isEmpty();
	}
	
	@Basic
	public Collection<Task> getTaskQueue() {
		return new ArrayList<Task>(taskQueue);
	}
}
